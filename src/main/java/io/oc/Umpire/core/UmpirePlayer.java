package io.oc.Umpire.core;

import io.oc.Umpire.core.UmpireMatch;
import io.oc.Umpire.core.UmpireTeam;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Bukkit.getPlayer;

public class UmpirePlayer {
    public UmpirePlayer(Player bukkitPlayer){
        this.bukkitPlayer = bukkitPlayer;
        this.inMatch = false;
    }

    public UmpireTeam team;
    public UmpireMatch match;
    public Player bukkitPlayer;
    public boolean inMatch;
    public void setColor(ChatColor color){
        bukkitPlayer.setDisplayName(color + bukkitPlayer.getName() + ChatColor.WHITE);
        bukkitPlayer.setPlayerListName(color + bukkitPlayer.getName() + ChatColor.WHITE);
    }
    
    public void wipePlayer() {
    	bukkitPlayer.setLevel(0);
    	bukkitPlayer.setExp(0);
    	bukkitPlayer.setHealth(bukkitPlayer.getAttribute(Attribute.MAX_HEALTH).getValue());
    	bukkitPlayer.setFoodLevel(20);
    	bukkitPlayer.setSaturation(5); //Default on world generation is 5
    	bukkitPlayer.getInventory().clear();
    	
    	for(PotionEffect pe : bukkitPlayer.getActivePotionEffects()) {
    		bukkitPlayer.removePotionEffect(pe.getType());
    	}
    	
    }
}
