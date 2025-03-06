package io.oc.Umpire.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.UmpirePlayer;

public class PlayerResourceListener implements Listener{
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent Event) {
		if(Event.getEntity() instanceof Player) {
			Player p = (Player) Event.getEntity();
			UmpirePlayer up = Umpire.getPlayer(p);
			if(up.inMatch) {
				//take damage as normal
				Event.setCancelled(false);
			}else {
				Event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerHunger(FoodLevelChangeEvent Event) {
		if(Event.getEntity() instanceof Player) {
			Player p = (Player) Event.getEntity();
			UmpirePlayer up = Umpire.getPlayer(p);
			if(up.inMatch) {
				//take hunger as normal
				Event.setCancelled(false);
			}else {
				Event.setCancelled(true);
			}
		}
	}

}
