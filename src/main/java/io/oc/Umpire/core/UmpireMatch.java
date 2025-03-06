package io.oc.Umpire.core;

import io.oc.Umpire.*;
import io.oc.Umpire.utils.AutorefColors;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.oc.Umpire.utils.MapUtils.stringToLocation;
import static org.bukkit.Bukkit.getLogger;

public class UmpireMatch {

    public UmpireTeam obsTeam = new UmpireTeam(ChatColor.GRAY, "Observers", true);
    public ArrayList<UmpireTeam> teams = new ArrayList<>();
    public UmpireMap map;
    public State state;
    CountdownTimer startTimer;
    public boolean isEmpty;

    public UmpireMatch(UmpireMap map){
        //UmpireMap map = new UmpireMap(worldFolder, this, mapName);
        this.map = map;
        map.startWorld();

        loadFromXML(this.map.xmlFile);

        teams.add(obsTeam);
        this.state = State.PREGAME;
    }

    public void broadcast(String message){
        getPlayers().forEach(p -> p.bukkitPlayer.sendMessage(message));
        //TODO test
    }

    public void broadcastTitle(String title, String subtitle){
        Set<UmpirePlayer> players = getPlayers();
        for(UmpirePlayer player : players){
            player.bukkitPlayer.sendTitle(title, subtitle, 5, 60, 20);
            getLogger().info("Broadcasting to " + player.bukkitPlayer.getName());
        }
        //getPlayers().forEach(p -> p.bukkitPlayer.sendTitle(title, subtitle, 5, 60, 20));
        //TODO test
    }
    public void addPlayer(UmpirePlayer player) {
        obsTeam.addPlayer(player);
        player.wipePlayer();
        player.match = this;
    }

    public Set<UmpirePlayer> getPlayers(){
        return teams.stream().flatMap(team -> (
            team.players.stream()
        )).collect(Collectors.toSet());
    }

    public void checkIfStart() {
        for (UmpireTeam team : teams){
            //Don't start the game if there is a legitamate (not obs or empty) team that is not ready
            if (!team.isObs && !team.readyState && team.players.size() != 0){
                return;
            }
        }
        if(state == State.PREGAME) {
            this.startCountdown();
        }
    }

    private void startCountdown(){
        broadcast("Match will begin in...");
        getLogger().info("Starting countdown");
        startTimer = new CountdownTimer(this);
        startTimer.runTaskTimer(Umpire.getInstance(), 0L, 20L);
    }

    public void cancelStart(){
        if(startTimer != null && !startTimer.isCancelled()) {
            startTimer.cancel();
        }
    }

    public void startMatch(){
        this.state = State.PLAYING;
        //full heal, full food, clear inventory, gamemode
        for(UmpirePlayer up: getPlayers()){
            Player p = up.bukkitPlayer;
            up.wipePlayer();
            p.setGameMode(GameMode.SURVIVAL);
        }

        map.startMechanisms();
        getLogger().info("Starting match!");
        broadcastTitle("Go!", "");
        broadcast("Go!");
    }

    private void loadFromXML(Document doc){
        Element rootElement = doc.getRootElement();
        List<Element> teamElements = rootElement.getChild("teams").getChildren("team");
        for (Element teamElement : teamElements){
            ChatColor color = AutorefColors.TEAMS_OPTION_COLOR.get(teamElement.getAttributeValue("color").toLowerCase());
            String teamName = teamElement.getChildText("name");
            UmpireTeam newTeam = new UmpireTeam(color, teamName, false);

            Element loc = teamElement.getChild("spawn").getChild("location");
            String pos = loc.getAttributeValue("pos");
            String yawString = loc.getAttributeValue("yaw");

            newTeam.spawnPoint = stringToLocation(pos+','+yawString, map.getWorld()).add(new Location(map.getWorld(),0.5,0.5,0.5));
            getLogger().info("Adding team: " + newTeam.teamname);
            teams.add(newTeam);
        }

        this.obsTeam.spawnPoint = stringToLocation(rootElement.getChild("startregion").getAttributeValue("spawn"), map.getWorld()).add(new Location(map.getWorld(),0.5,0.5,0.5));
        map.loadMapFromXML(doc, this);
    }

    public UmpireTeam getTeam(String teamName){
        for (UmpireTeam team : teams){
            if (team.teamname.equalsIgnoreCase(teamName)){
                return team;
            }
        }
        return null;
    }

    public void deload() {
        map.deload();
    }

    public void checkUnused() {
        for (UmpireTeam team : teams){
            if (team.players.size() != 0){
                return;
            }
        }
        map.deload();
    }

    public void checkVictory() {
        boolean completed;
        for (UmpireTeam team : teams){
            completed = true;
            for (VictoryCondition vc : map.victoryConditions){
                if(team.isObs){
                    completed = false;
                }
                if (vc.teamName.equalsIgnoreCase(team.teamname)){
                    completed &= vc.check();
                }
            }
            if (completed){
                endMatch(team);
            }
        }
    }

    private void endMatch(UmpireTeam team) {
        getLogger().info("Ending match with winner: " + team.teamname);
        broadcast("Game Over! " + team.color + team.teamname + ChatColor.WHITE + " wins the match!");
        for(UmpirePlayer up : getPlayers()){
            up.bukkitPlayer.setGameMode(GameMode.SPECTATOR);
        }
    }
}
