package io.oc.Umpire.listeners;

import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.UmpirePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ViewInventoryListener implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent Event){
        Player p = Event.getPlayer();
        UmpirePlayer up = Umpire.getPlayer(p);
        if(!(Event.getRightClicked() instanceof Player target)){
            return;
        }
        if(Umpire.getPlayer(target).team.isObs){
            return;
        }
        if(!up.team.isObs){
            return;
        }
        p.openInventory(target.getInventory());
    }
}
