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
	
	final ItemMeta lowToolMeta = testItem.getItemMeta();
	final ItemMeta mediumToolMeta = testItem.getItemMeta();
	final ItemMeta highToolMeta = testItem.getItemMeta();
	
	//Extra Sets
	final ItemStack arrows = new ItemStack(Material.ARROW,1);
	final ItemStack exp = new ItemStack(Material.EXPERIENCE_BOTTLE,1);
	final ItemStack tnt = new ItemStack(Material.TNT,1);
	
	final ItemMeta arrowMeta = testItem.getItemMeta();
	final ItemMeta expMeta = testItem.getItemMeta();
	final ItemMeta tntMeta = testItem.getItemMeta();
	
	//Inventory Management
	final ItemStack saveInventory = new ItemStack(Material.HONEY_BLOCK,1);
	final ItemStack loadInventory = new ItemStack(Material.HONEY_BOTTLE,1);
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
	
		//Items 1-9 (Row 1)
		
		survivalMeta.setDisplayName("Survival Mode");
		survivalMeta.setLore(Lists.newArrayList("Sets the player's gamemode to Survival"));
		survivalMode.setItemMeta(survivalMeta);
		
		lowArmorMeta.setDisplayName("Leather Armor");
		lowArmorMeta.setLore(Lists.newArrayList("Gives the player the leather kit"));
		lowArmor.setItemMeta(lowArmorMeta);
		
		lowToolMeta.setDisplayName("Basic Tools");
		lowToolMeta.setLore(Lists.newArrayList("Gives the player basic tools"));
		lowTools.setItemMeta(lowToolMeta);
		
		arrowMeta.setDisplayName("Give Arrows");
		arrowMeta.setLore(Lists.newArrayList("Gives the player another stack of Arrows"));
		arrows.setItemMeta(arrowMeta);
		
		saveInventoryMeta.setDisplayName("Save Inventory");
		saveInventoryMeta.setLore(Lists.newArrayList("Saves the player's inventory"));
		saveInventory.setItemMeta(saveInventoryMeta);
		
		dayMeta.setDisplayName("Set Time to Day");
		dayMeta.setLore(Lists.newArrayList("Sets the time to Day"));
		setDay.setItemMeta(dayMeta);
		
		nightVisMeta.setDisplayName("Toggle Night Vision");
		nightVisMeta.setLore(Lists.newArrayList("Toggles the Night Vision Potion Effect on the Player"));
		nightVision.setItemMeta(nightVisMeta);
		
		//Items 10-18 (Row 2)
		
		creativeMeta.setDisplayName("Creative Mode");
		creativeMeta.setLore(Lists.newArrayList("Sets the player's gamemode to Creative"));
		creativeMode.setItemMeta(creativeMeta);
		
		mediumArmorMeta.setDisplayName("Iron Armor");
		mediumArmorMeta.setLore(Lists.newArrayList("Gives the player the iron kit"));
		mediumArmor.setItemMeta(mediumArmorMeta);
		
		mediumToolMeta.setDisplayName("Standard Tools");
		mediumToolMeta.setLore(Lists.newArrayList("Gives the player standard tools"));
		mediumTools.setItemMeta(mediumToolMeta);
		
		expMeta.setDisplayName("Enchanting Tools");
		expMeta.setLore(Lists.newArrayList("Gives the players the tools to enchant their gear"));
		exp.setItemMeta(expMeta);
		
		loadInventoryMeta.setDisplayName("Load Inventory");
		loadInventoryMeta.setLore(Lists.newArrayList("Loads the Saved Inventory"));
		loadInventory.setItemMeta(loadInventoryMeta);
		
		
		nightMeta.setDisplayName("Night Time");
		nightMeta.setLore(Lists.newArrayList("Sets the time of day to Night"));
		setNight.setItemMeta(nightMeta);
		
		//Items 19-27 (Row 3)
		
		spectatorMeta.setDisplayName("Spectator Mode");
		spectatorMeta.setLore(Lists.newArrayList("Sets the player's gamemode to Spectator"));
		spectatorMode.setItemMeta(spectatorMeta);
		
		highArmorMeta.setDisplayName("Diamond Armor");
		highArmorMeta.setLore(Lists.newArrayList("Gives the player the diamond kit"));
		highArmor.setItemMeta(highArmorMeta);
		
		highToolMeta.setDisplayName("Improved Tools");
		highToolMeta.setLore(Lists.newArrayList("Gives the player Improved tools"));
		highTools.setItemMeta(highToolMeta);
		
		
		tntMeta.setDisplayName("TNT Kit");
		tntMeta.setLore(Lists.newArrayList("Gives the player the necessary supplies to cannon"));
		tnt.setItemMeta(tntMeta);
		
		clearSavedInventoryMeta.setDisplayName("Clear Saved Inventory");
		clearSavedInventoryMeta.setLore(Lists.newArrayList("Clears the Saved Inventory"));
		clearSavedInventory.setItemMeta(clearSavedInventoryMeta);
		
		freezeMeta.setDisplayName("Toggle Daylight Cycle");
		freezeMeta.setLore(Lists.newArrayList("Toggles the Day/Night Cycle"));
		freezeTime.setItemMeta(freezeMeta);
		
		restoreMeta.setDisplayName("Restore Player");
		restoreMeta.setLore(Lists.newArrayList("Heals and Feeds the player"));
		restore.setItemMeta(restoreMeta);
		
		
		//-----------------------------------------------------------------------
		
		//Add Items 0-8
		Umpire.practice.addItem(survivalMode);
		Umpire.practice.addItem(lowArmor);
		Umpire.practice.addItem(lowTools);
		Umpire.practice.addItem(arrows);
		Umpire.practice.addItem(saveInventory);
		Umpire.practice.addItem(setDay);
		
		for(int i = 0; i < 2; i++) {
			Umpire.practice.addItem(blank);
		}
		
		Umpire.practice.addItem(nightVision);
		
		//Add Items 9-17
		Umpire.practice.addItem(creativeMode);
		Umpire.practice.addItem(mediumArmor);
		Umpire.practice.addItem(mediumTools);
		Umpire.practice.addItem(exp);
		Umpire.practice.addItem(loadInventory);
		Umpire.practice.addItem(setNight);
		
		for(int i = 0; i < 3; i++) {
			Umpire.practice.addItem(blank);
		}
		
		//Add Items 18-26
		Umpire.practice.addItem(spectatorMode);
		Umpire.practice.addItem(highArmor);
		Umpire.practice.addItem(highTools);
		Umpire.practice.addItem(tnt);
		Umpire.practice.addItem(clearSavedInventory);
		Umpire.practice.addItem(freezeTime);
		
		for(int i = 0; i < 2; i++) {
			Umpire.practice.addItem(blank);
		}
		
		Umpire.practice.addItem(restore);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
