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
        Location newLoc = location.clone();

        for(int x = location.getBlockX()-2; x <= location.getBlockX()+2; x++){
            for(int y = location.getBlockY()-2; y <= location.getBlockY()+2; y++){
                for(int z = location.getBlockZ()-2; z <= location.getBlockZ()+2; z++){
                    newLoc.setX(x);
                    newLoc.setY(y);
                    newLoc.setZ(z);

                    if(location.distanceSquared(newLoc) <= 4){
                        if(newLoc.getBlock().getType().equals(material)){
                            getLogger().info("Succesfully found block on monument at distance squared " + location.distanceSquared(newLoc));
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
