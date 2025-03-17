package io.oc.Umpire.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

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
			if(e.getClick().equals(ClickType.RIGHT)) {
				e.getWhoClicked().sendMessage("abracadabra");
			}
		}
	}
	
}
