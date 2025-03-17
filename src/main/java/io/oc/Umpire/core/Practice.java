package io.oc.Umpire.core;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;

import io.oc.Umpire.Umpire;

public class Practice {
	
	final ItemStack testItem = new ItemStack(Material.GLASS, 1);
	final ItemMeta testMeta = testItem.getItemMeta();
	
	//Game Mode Controls
	final ItemStack survivalMode = new ItemStack(Material.OAK_SAPLING,1);
	final ItemStack creativeMode = new ItemStack(Material.COMMAND_BLOCK,1);
	final ItemStack spectatorMode = new ItemStack(Material.SPYGLASS,1);
	
	final ItemMeta survivalMeta = testItem.getItemMeta();
	final ItemMeta creativeMeta = testItem.getItemMeta();
	final ItemMeta spectatorMeta = testItem.getItemMeta();
	
	//Armor Sets
	final ItemStack lowArmor = new ItemStack(Material.LEATHER_CHESTPLATE,1);
	final ItemStack mediumArmor = new ItemStack(Material.IRON_CHESTPLATE,1);
	final ItemStack highArmor = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
	
	final ItemMeta lowArmorMeta = testItem.getItemMeta();
	final ItemMeta mediumArmorMeta = testItem.getItemMeta();
	final ItemMeta highArmorMeta = testItem.getItemMeta();
	
	//Tool Sets
	final ItemStack lowTools = new ItemStack(Material.STONE_PICKAXE,1);
	final ItemStack mediumTools = new ItemStack(Material.IRON_PICKAXE,1);
	final ItemStack highTools = new ItemStack(Material.DIAMOND_PICKAXE,1);
	
	final ItemMeta lowToolsMeta = testItem.getItemMeta();
	final ItemMeta mediumToolsMeta = testItem.getItemMeta();
	final ItemMeta highToolsMeta = testItem.getItemMeta();
	
	//Extra Sets
	final ItemStack arrows = new ItemStack(Material.ARROW,1);
	final ItemStack exp = new ItemStack(Material.EXPERIENCE_BOTTLE,1);
	final ItemStack tnt = new ItemStack(Material.TNT,1);
	
	final ItemMeta arrowMeta = testItem.getItemMeta();
	final ItemMeta expMeta = testItem.getItemMeta();
	final ItemMeta tntMeta = testItem.getItemMeta();
	
	//Inventory Management
	final ItemStack saveInventory = new ItemStack(Material.HONEY_BLOCK,1);
	final ItemStack loadIventory = new ItemStack(Material.HONEY_BOTTLE,1);
	final ItemStack clearSavedInventory = new ItemStack(Material.GLASS_BOTTLE,1);
	
	final ItemMeta saveInventoryMeta = testItem.getItemMeta();
	final ItemMeta loadInventoryMeta = testItem.getItemMeta();
	final ItemMeta clearSavedInventoryMeta = testItem.getItemMeta();
	
	//Time Management
	final ItemStack setDay = new ItemStack(Material.WAXED_COPPER_BULB);
	final ItemStack setNight = new ItemStack(Material.WAXED_WEATHERED_COPPER_BULB);
	final ItemStack freezeTime = new ItemStack(Material.CLOCK);
	
	final ItemMeta dayMeta = testItem.getItemMeta();
	final ItemMeta nightMeta = testItem.getItemMeta();
	final ItemMeta freezeMeta = testItem.getItemMeta();
	
	//Misc
	final ItemStack restore = new ItemStack(Material.GOLDEN_APPLE);
	final ItemStack nightVision = new ItemStack(Material.GOLDEN_CARROT);
	final ItemStack blank = new ItemStack(Material.RED_STAINED_GLASS_PANE);
	
	final ItemMeta restoreMeta = testItem.getItemMeta();
	final ItemMeta nightVisMeta = testItem.getItemMeta();
	final ItemMeta blankMeta = testItem.getItemMeta();
	
	
	public void setInventory() {
		
		blankMeta.setDisplayName("");
		blankMeta.setLore(Lists.newArrayList("There is no command associated"));
		blankMeta.setMaxStackSize(1);
		blank.setItemMeta(blankMeta);
//		testMeta.setDisplayName("Test Item");
//		testMeta.setLore(Lists.newArrayList("Test Lore"));
//		testItem.setItemMeta(testMeta);
//		Umpire.practice.addItem(testItem);
	
		//Items 1-9 (Row 1)
		
		survivalMeta.setDisplayName("Survival Mode");
		survivalMeta.setLore(Lists.newArrayList("Sets the player's gamemode to Survival"));
		survivalMode.setItemMeta(survivalMeta);
		
		lowArmorMeta.setDisplayName("Leather Armor");
		lowArmorMeta.setLore(Lists.newArrayList("Gives the player the leather kit"));
		lowArmor.setItemMeta(lowArmorMeta);
		
		//Items 10-18 (Row 2)
		
		creativeMeta.setDisplayName("Creative Mode");
		creativeMeta.setLore(Lists.newArrayList("Sets the player's gamemode to Creative"));
		creativeMode.setItemMeta(creativeMeta);
		
		mediumArmorMeta.setDisplayName("Iron Armor");
		mediumArmorMeta.setLore(Lists.newArrayList("Gives the player the iron kit"));
		mediumArmor.setItemMeta(mediumArmorMeta);
		
		//Items 19-27 (Row 3)
		
		spectatorMeta.setDisplayName("Spectator Mode");
		spectatorMeta.setLore(Lists.newArrayList("Sets the player's gamemode to Spectator"));
		spectatorMode.setItemMeta(spectatorMeta);
		
		highArmorMeta.setDisplayName("Diamond Armor");
		highArmorMeta.setLore(Lists.newArrayList("Gives the player the diamond kit"));
		highArmor.setItemMeta(highArmorMeta);
		
		
		
		
		
		
		//-----------------------------------------------------------------------
		
		//Add Items 0-8
		Umpire.practice.addItem(survivalMode);
		Umpire.practice.addItem(lowArmor);
		
		
		for(int i = 0; i < 7; i++) {
			Umpire.practice.addItem(blank);
		}
		
		//Add Items 9-17
		Umpire.practice.addItem(creativeMode);
		Umpire.practice.addItem(mediumArmor);
		
		for(int i = 0; i < 7; i++) {
			Umpire.practice.addItem(blank);
		}
		
		//Add Items 18-26
		Umpire.practice.addItem(spectatorMode);
		Umpire.practice.addItem(highArmor);
		
		for(int i = 0; i < 7; i++) {
			Umpire.practice.addItem(blank);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
