package io.oc.Umpire.core;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;

import java.util.Set;

import static org.bukkit.Bukkit.getLogger;

public class UmpireRegion{
    public RegionType regionType;
    public boolean isIn(Location loc) {
        return boundingBox.contains(loc.toVector());
    }


    public Set<UmpireTeam> teams;
    BoundingBox boundingBox;

    UmpireRegion(Location corner1, Location corner2, RegionType regionType, Set<UmpireTeam> teams){
        boundingBox = BoundingBox.of(corner1, corner2).expand(0.3, 0, 0.3);
        this.regionType = regionType;
        this.teams = teams;
    }
}

