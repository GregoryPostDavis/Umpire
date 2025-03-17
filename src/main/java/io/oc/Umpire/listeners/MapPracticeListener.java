package io.oc.Umpire.listeners;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.oc.Umpire.Umpire;

public class MapPracticeListener implements Listener{

	@EventHandler
	public void onInventoryClick(InventoryDragEvent e) {
		if(e.getInventory().equals(Umpire.practice)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.getInventory().equals(Umpire.practice)) {
			e.setCancelled(true);
			if(e.getClick().equals(ClickType.RIGHT) || e.getClick().equals(ClickType.LEFT)) {
				e.getWhoClicked().sendMessage("Slot: " + e.getSlot());
				
				
				if(e.getInventory().equals(Umpire.practice)) {
					Player p = (Player) e.getWhoClicked();
					switch(e.getSlot()) {
					case 0:
						//Survival Mode
						e.getWhoClicked().setGameMode(GameMode.SURVIVAL);
						break;
					case 1:
						//Leather Armor Kit (Full Leather Armor, Stone Sword, Bow, 64+ Arrows)
						e.getWhoClicked().getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
						e.getWhoClicked().getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
						e.getWhoClicked().getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
						e.getWhoClicked().getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
											
						if(e.getWhoClicked().getInventory().contains(Material.STONE_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.STONE_SWORD);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.STONE_SWORD));
						
						if(e.getWhoClicked().getInventory().contains(Material.IRON_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.IRON_SWORD);
						}
						
						if(e.getWhoClicked().getInventory().contains(Material.DIAMOND_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.DIAMOND_SWORD);
						}
						
						if(e.getWhoClicked().getInventory().contains(Material.BOW)) {
							e.getWhoClicked().getInventory().remove(Material.BOW);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.BOW));
						
						
						if(e.getWhoClicked().getInventory().contains(Material.ARROW)) {
							e.getWhoClicked().getInventory().remove(Material.ARROW);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.ARROW,64));
						
						
						break;
					case 2:
						break;
					case 3:
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.ARROW,64));
						break;
					case 4:
						break;
					case 5:
						p.getWorld().setTime(6000);
						break;
					case 6:
			
						break;
					case 7:
						break;
					case 8:
						if(e.getWhoClicked().hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
							e.getWhoClicked().removePotionEffect(PotionEffectType.NIGHT_VISION);
						}else {
							e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999999, 1));
						}
						break;
					case 9:
						//Creative Mode
						e.getWhoClicked().setGameMode(GameMode.CREATIVE);
						break;
					case 10:
						e.getWhoClicked().getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
						e.getWhoClicked().getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
						e.getWhoClicked().getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
						e.getWhoClicked().getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
						
						if(e.getWhoClicked().getInventory().contains(Material.IRON_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.IRON_SWORD);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.IRON_SWORD));
						
						if(e.getWhoClicked().getInventory().contains(Material.STONE_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.STONE_SWORD);
						}
						
						if(e.getWhoClicked().getInventory().contains(Material.DIAMOND_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.DIAMOND_SWORD);
						}
						
						if(e.getWhoClicked().getInventory().contains(Material.BOW)) {
							e.getWhoClicked().getInventory().remove(Material.BOW);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.BOW));
						
						if(e.getWhoClicked().getInventory().contains(Material.ARROW)) {
							e.getWhoClicked().getInventory().remove(Material.ARROW);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.ARROW,64));
						
						break;
					case 11:
						break;
					case 12:
						p.setLevel(20);
						
						if(e.getWhoClicked().getInventory().contains(Material.ENCHANTING_TABLE)) {
							// nothing
						}else {
							e.getWhoClicked().getInventory().addItem(new ItemStack(Material.ENCHANTING_TABLE));
						}
						
						if(e.getWhoClicked().getInventory().contains(Material.LAPIS_LAZULI)) {
							e.getWhoClicked().getInventory().remove(Material.LAPIS_LAZULI);
						}
							e.getWhoClicked().getInventory().addItem(new ItemStack(Material.LAPIS_LAZULI, 32));
						
						break;
					case 13:
						break;
					case 14:
						p.getWorld().setTime(18000);
						break;
					case 15:
						break;
					case 16:
						break;
					case 17:
						break;
					case 18:
						//Spectator Mode
						e.getWhoClicked().setGameMode(GameMode.SPECTATOR);
						break;
					case 19:
						e.getWhoClicked().getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						e.getWhoClicked().getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						e.getWhoClicked().getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						e.getWhoClicked().getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						if(e.getWhoClicked().getInventory().contains(Material.DIAMOND_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.DIAMOND_SWORD);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
						
						if(e.getWhoClicked().getInventory().contains(Material.STONE_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.STONE_SWORD);
						}
						
						if(e.getWhoClicked().getInventory().contains(Material.IRON_SWORD)) {
							e.getWhoClicked().getInventory().remove(Material.IRON_SWORD);
						}
						
						if(e.getWhoClicked().getInventory().contains(Material.BOW)) {
							e.getWhoClicked().getInventory().remove(Material.BOW);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.BOW));
						
						if(e.getWhoClicked().getInventory().contains(Material.ARROW)) {
							e.getWhoClicked().getInventory().remove(Material.ARROW);
						}
						e.getWhoClicked().getInventory().addItem(new ItemStack(Material.ARROW,64));
						
						break;
					case 20:
						break;
					case 21:
						break;
					case 22:
						break;
					case 23:
						if(p.getWorld().getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)) {
							p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
						}else {
							p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
						}
						
						break;
					case 24:
						break;
					case 25:
						break;
					case 26:
						
						e.getWhoClicked().setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue());
						e.getWhoClicked().setFoodLevel(20);
						e.getWhoClicked().setSaturation(5);
						break;
					}
				}

				
				
				
				
				
				
				
				
			}
		}
	}
	
}
