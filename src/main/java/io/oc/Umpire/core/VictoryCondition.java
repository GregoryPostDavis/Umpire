package io.oc.Umpire.core;

import org.bukkit.Location;
import org.bukkit.Material;

import static org.bukkit.Bukkit.getLogger;

public class VictoryCondition {
    public Location location;
    Material material;
    public String teamName;
    VictoryCondition(Location loc, Material mat, String teamName){
        this.location = loc;
        this.material = mat;
        this.teamName = teamName;
    }
    public boolean check(){
        return (location.getBlock().getType().equals(material));
    }
}
