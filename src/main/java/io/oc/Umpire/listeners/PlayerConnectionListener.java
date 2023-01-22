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
    public void onPlayerJoin(PlayerJoinEvent Event){
        Player p = Event.getPlayer();
        if(!Umpire.getInstance().isPlayer(p)){
            getLogger().info("Could not find player, sending them to spawn");
            p.teleport(getServer().getWorlds().get(0).getSpawnLocation());
        }
        UmpirePlayer up = Umpire.getPlayer(p); //Getting a player that has not yet been registered registers them
        if (up.match != null && up.match.map.world == null){
            getLogger().info("Map is now null, sending player to lobby spawn");
            up.match = null;
            up.team = null;
            p.teleport(getServer().getWorlds().get(0).getSpawnLocation());
        }
    }
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent Event){
        if(Event.getTo()==null || Event.getTo().getWorld().equals(Event.getFrom().getWorld())){
            return;
        }
        UmpireMatch match = Umpire.getInstance().getMatch(Event.getTo().getWorld().getName());
        if(match == null){
            return;
        }
        Player p = (Player) Event.getPlayer();
        UmpirePlayer up = Umpire.getPlayer(p);
        p.getInventory().clear();
        p.setGameMode(GameMode.CREATIVE);
        match.addPlayer(up);
    }
}
