package io.oc.Umpire.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.State;
import io.oc.Umpire.core.UmpirePlayer;

public class PlayerResourceListener implements Listener{
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent Event) {
		if(Event.getEntity() instanceof Player) {
			Player p = (Player) Event.getEntity();
			UmpirePlayer up = Umpire.getPlayer(p);
			if(up.match != null) {
				if(up.match.state == State.PLAYING) {
					if (up.team.isObs) {
						Event.setCancelled(true);
					}else {
						Event.setCancelled(false);
					}
				}else {
					Event.setCancelled(true);
				}
				
			}else {
				//Match is null, not in active game, ignore damage
				Event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerHunger(FoodLevelChangeEvent Event) {
		if(Event.getEntity() instanceof Player) {
			Player p = (Player) Event.getEntity();
			UmpirePlayer up = Umpire.getPlayer(p);
			if(up.match != null) {
				if(up.match.state == State.PLAYING) {
					Event.setCancelled(false);
				}else {
					Event.setCancelled(true);
				}
				
			}else {
				//Match is null, not in active game, ignore damage
				Event.setCancelled(true);
			}
		}
	}

}
