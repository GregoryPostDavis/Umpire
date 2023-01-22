package io.oc.Umpire.listeners;

import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.UmpirePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player p = event.getPlayer();
        if(!event.isBedSpawn() || event.getRespawnLocation().getWorld() != p.getWorld()){
            UmpirePlayer up = Umpire.getPlayer(p);
            if (up.match != null && up.team != null){
                if(up.team.spawnPoint != null) {
                    event.setRespawnLocation(up.team.spawnPoint);
                }
                else{
                    event.setRespawnLocation(up.match.obsTeam.spawnPoint);
                }
            }
        }
    }
}
