package io.oc.Umpire.core;

import static org.bukkit.Bukkit.getLogger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;

public class UmpirePlayer {
    public UmpirePlayer(Player bukkitPlayer){
        this.bukkitPlayer = bukkitPlayer;
    }

    public UmpireTeam team;
    public UmpireMatch match;
    public Player bukkitPlayer;
    
//    public Inventory playerInventory = Bukkit.createInventory(bukkitPlayer, 9 * 4);
//    public Inventory offhand = Bukkit.createInventory(null, 1);
//    public Inventory playerHelmet = Bukkit.createInventory(null, 1);
//    public Inventory playerChestplate = Bukkit.createInventory(null, 1);
//    public Inventory playerLeggings = Bukkit.createInventory(null, 1);
//    public Inventory playerBoots = Bukkit.createInventory(null, 1);
    
    public void setColor(ChatColor color){
        bukkitPlayer.setDisplayName(color + bukkitPlayer.getName() + ChatColor.WHITE);
        bukkitPlayer.setPlayerListName(color + bukkitPlayer.getName() + ChatColor.WHITE);
    }
    
    

    public void clearColor(){
        setColor(ChatColor.WHITE);
    }
    
    public void wipePlayer() {
        getLogger().info("Wiping player " + bukkitPlayer.getName());
    	bukkitPlayer.setLevel(0);
    	bukkitPlayer.setExp(0);
    	bukkitPlayer.setHealth(bukkitPlayer.getAttribute(Attribute.MAX_HEALTH).getValue());
    	bukkitPlayer.setFoodLevel(20);
    	bukkitPlayer.setSaturation(5); //Default on world generation is 5
    	bukkitPlayer.getInventory().clear();
    	
    	for(PotionEffect pe : bukkitPlayer.getActivePotionEffects()) {
    		bukkitPlayer.removePotionEffect(pe.getType());
    	}
    	
//    	playerInventory.clear();
    	
    }
}
