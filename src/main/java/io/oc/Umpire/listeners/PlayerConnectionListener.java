package io.oc.Umpire.listeners;

import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.UmpireMatch;
import io.oc.Umpire.core.UmpirePlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class PlayerConnectionListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        UmpirePlayer up = Umpire.getPlayer(p);
        if(up == null){
            getLogger().info("Could not find player, sending them to spawn");
            p.teleport(getServer().getWorlds().get(0).getSpawnLocation());
            up = Umpire.addPlayer(p);
            up.wipePlayer();
        }

        if (up.match != null && up.match.map.world == null){
            getLogger().info("Map is now null, sending player to lobby spawn");
            up.match = null;
            up.team = null;
            p.teleport(getServer().getWorlds().get(0).getSpawnLocation());
        }
    }
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){
        if(event.getTo()==null || event.getTo().getWorld().equals(event.getFrom().getWorld())){
            return;
        }
        UmpireMatch match = Umpire.getInstance().getMatch(event.getTo().getWorld().getName());
        if(match == null){
            return;
        }
        Player p = event.getPlayer();
        UmpirePlayer up = Umpire.getPlayer(p);
        p.getInventory().clear();
        p.setGameMode(GameMode.CREATIVE);
        match.addPlayer(up);
    }
}
