package io.oc.Umpire.core;

import io.oc.Umpire.utils.AutorefColors;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.jdom2.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.oc.Umpire.utils.MapUtils.*;
import static org.bukkit.Bukkit.getLogger;


public class UmpireMap {
    //Spawn Region
    //Team Regions
    public World world;
    public String worldName;
    public MapDescriptor mapDescriptor;
    /*worldName is used to identify the match with the world before the world is loaded
    so that events that happen in a world right at startup are garanteed
    to still able to be associated with a match (eg. liquid flowing needs to be prevented from tick 1).*/
    List<UmpireRegion> regions = new ArrayList<>();

    List<Mechanism> mechanisms = new ArrayList<>();
    public List<VictoryCondition> victoryConditions = new ArrayList<>();
    public UmpireMap(){}
    public UmpireMap(MapDescriptor mapDescriptor){
        this.mapDescriptor = mapDescriptor;
        this.worldName = makeWorldFolder(mapDescriptor.mapName);//Might return null if the map file is invalid?
        getLogger().info("Creating world at: " + this.worldName);
    }
    public World getWorld(){
        return world;
    }

    public void loadMapFromXML(UmpireMatch match){
        //Team List and Spawn Points
        Element rootElement = this.mapDescriptor.xmlFile.getRootElement();
        List<Element> teamElements = rootElement.getChild("teams").getChildren("team");
        for (Element teamElement : teamElements){
            ChatColor color = AutorefColors.TEAMS_OPTION_COLOR.get(teamElement.getAttributeValue("color").toLowerCase());
            String teamName = teamElement.getChildText("name");
            UmpireTeam newTeam = new UmpireTeam(color, teamName, false);

            Element loc = teamElement.getChild("spawn").getChild("location");
            String pos = loc.getAttributeValue("pos");
            String yawString = loc.getAttributeValue("yaw");

            newTeam.spawnPoint = stringToLocation(pos+','+yawString, getWorld()).add(new Location(getWorld(),0.5,0.5,0.5));
            getLogger().info("Adding team: " + newTeam.teamname);
            match.teams.add(newTeam);
        }
        match.obsTeam.spawnPoint = stringToLocation(rootElement.getChild("startregion").getAttributeValue("spawn"), getWorld()).add(new Location(getWorld(),0.5,0.5,0.5));

        //Spawn Region
        Element spawnCuboid = rootElement.getChild("startregion").getChild("cuboid");
        Location corner1 = stringToLocation(spawnCuboid.getAttributeValue("max"),world).add(new Location(world, 1, 1, 1));
        Location corner2 = stringToLocation(spawnCuboid.getAttributeValue("min"),world);
        getLogger().info("Adding spawn region with corners: " + corner1 + corner2);
        regions.add(new UmpireRegion(corner1, corner2, RegionType.SPAWN, null));

        //Team Regions
        List<Element> regionElements = rootElement.getChild("regions").getChildren("cuboid");
        for (Element regionElement : regionElements){
            corner1 = stringToLocation(regionElement.getAttributeValue("max"),world).add(new Location(world, 1, 1, 1));
            corner2 = stringToLocation(regionElement.getAttributeValue("min"),world);

            getLogger().info("Adding region with corners: " + corner1 + corner2);

            List<Element> owners = regionElement.getChildren("owner");
            Set<UmpireTeam> teams = new HashSet<>();
            for (Element owner : owners){
                String teamName = owner.getText();
                UmpireTeam team = match.getTeam(teamName);
                teams.add(team);
                getLogger().info("Adding team " + teamName + " to region");
            }
            regions.add(new UmpireRegion(corner1, corner2, RegionType.TEAM, teams));
        }

        //Start Mechanisms
        Location loc;
        boolean powered;
        List<Element> mechanismElements = rootElement.getChild("mechanisms").getChildren("lever");
        for (Element mechanismElement : mechanismElements){
            loc = stringToLocation(mechanismElement.getAttributeValue("pos"), world);
            powered = Boolean.parseBoolean(mechanismElement.getText());
            getLogger().info("Adding start mechanism at position: " + loc);
            mechanisms.add(new Mechanism(loc, powered));
        }

        //Victory Conditions
        Material mat;
        String team;
        List<Element> teamGoalElements = rootElement.getChild("goals").getChildren("teamgoals");
        for (Element teamGoalElement : teamGoalElements){
            team = teamGoalElement.getAttributeValue("team");
            List<Element> victoryElements = teamGoalElement.getChildren("block");
            for (Element victoryElement : victoryElements){
                loc = stringToLocation(victoryElement.getAttributeValue("pos"), world);
                mat = stringToMaterial(victoryElement.getAttributeValue("id"));
                getLogger().info("Adding victory condition with material: " + (mat != null ? mat.toString() : "null") + "at position: " + loc);
                victoryConditions.add(new VictoryCondition(loc, mat, team));
            }
        }
    }

    public void startWorld() {
        this.world = loadWorld(worldName);
        this.world.setDifficulty(Difficulty.HARD);
        this.world.setGameRule(GameRule.DO_INSOMNIA, false);
        this.world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        this.world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        //TODO remove worldfolder if load fails
    }

    public UmpireRegion getRegion(Location loc){
        for (UmpireRegion region : regions){
            if (region.isIn(loc)){
                return region;
            }
        }
        return null;
    }

    public void deload() {
        getLogger().info("Attempting to unload map");
        boolean response = Bukkit.getServer().unloadWorld(world, false);
        if(!response){
            getLogger().info("failed to deload (or was not loaded)");
        }
        File worldFolder = new File(worldName);
        getLogger().info("worldname: " + worldName);
        if(!worldFolder.isFile()){
            getLogger().info("file deleted");
        }
        try {
            FileUtils.deleteDirectory(worldFolder);
        } catch(Exception e){
            getLogger().info("caught exception while deleting world folder: " + e.toString());
        }
    }

    public void startMechanisms() {
        for (Mechanism mechanism : mechanisms){
            mechanism.start();
        }
    }
}
