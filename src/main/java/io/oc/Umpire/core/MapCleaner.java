package io.oc.Umpire.core;

import io.oc.Umpire.Umpire;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class MapCleaner extends BukkitRunnable {
    /* Every 5 minutes this is ran by the plugin to detect wether any maps are currently unoccupied. When people join a
    match, tp in, or join the server the match should get removed from the list of inactive matches. If a match is still
    on the inactive list after 5 more minutes, the match should be deloaded. This means that no map should survive
    without players for more than 10 minutes.
     */
    Umpire umpire;
    public MapCleaner(Umpire umpire){
        this.umpire = umpire;
    }
    @Override
    public void run() {
        //getLogger().info("testing!");
        for(UmpireMatch match : umpire.matches){
            for(UmpirePlayer up: match.getPlayers()){
                /*if(!up.bukkitPlayer.isOnline()){
                    getInstance().emptyMatches.add();
                    //TODO add cleaner
                }*/
            }
        }
    }
}
