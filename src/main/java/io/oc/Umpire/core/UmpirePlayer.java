package io.oc.Umpire.core;

import static org.bukkit.Bukkit.getLogger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class UmpirePlayer {
    public UmpirePlayer(Player bukkitPlayer){
        this.bukkitPlayer = bukkitPlayer;
    }

    public UmpireTeam team;
    public UmpireMatch match;
    public Player bukkitPlayer;
    
    public ItemStack[] playerInventory = new ItemStack[37];
    public ItemStack[] playerArmor = new ItemStack[4];

    
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
    }
    
    public void setArmor() {
    	bukkitPlayer.getInventory().setItem(36, playerArmor[0]);
    	bukkitPlayer.getInventory().setItem(37, playerArmor[1]);
    	bukkitPlayer.getInventory().setItem(38, playerArmor[2]);
    	bukkitPlayer.getInventory().setItem(39, playerArmor[3]);

    }
    
    public void saveArmor() {
    	playerArmor[0] = bukkitPlayer.getInventory().getItem(36);
    	playerArmor[1] = bukkitPlayer.getInventory().getItem(37);
    	playerArmor[2] = bukkitPlayer.getInventory().getItem(38);
    	playerArmor[3] = bukkitPlayer.getInventory().getItem(39);
    }
    
    public void setInventory() {
    	bukkitPlayer.getInventory().setItem(40, playerInventory[36]);
    	
    	for(int x = 0; x < 36; x++) {
    		bukkitPlayer.getInventory().setItem(x, playerInventory[x]);
    	}
    }
    
    public void saveInventory() {
    	playerInventory[36] = bukkitPlayer.getInventory().getItem(40);
    	
    	for(int x = 0; x < 36; x++) {
    		playerInventory[x] = bukkitPlayer.getInventory().getItem(x);
    	}
    }
    
    public void clearInventory() {
    	for(int x = 0; x < playerInventory.length; x++) {
    		playerInventory[x] = null;
    	}
    }
    
    public void clearArmor() {
    	for(int x = 0; x < playerArmor.length; x++) {
    		playerArmor[x] = null;
    	}
    }
    
}
