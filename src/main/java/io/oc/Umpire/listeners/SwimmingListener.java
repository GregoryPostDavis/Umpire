package io.oc.Umpire.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import static org.bukkit.Bukkit.getLogger;

public class SwimmingListener implements Listener {
    /*@EventHandler(priority = EventPriority.HIGHEST)
    public void onSwim(EntityToggleSwimEvent event) {
        Player p = (Player) event.getEntity();

        p.setSwimming(false);
        p.setSprinting(false);
        event.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnterWater(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        //getLogger().info(p.getLocation().getBlock().toString());
        if(p.getLocation().getBlock().getType().equals(Material.WATER)){
            getLogger().info("in water");
            p.setSprinting(false);
            p.setSwimming(false);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSprint(PlayerToggleSprintEvent event){
        Player p = event.getPlayer();
        Entity vh = p.getVehicle();
        getLogger().info(vh.toString());
        int food = p.getFoodLevel();
        p.setFoodLevel(1);
        getLogger().info("toggled sprinting");
        event.getPlayer().setSprinting(false);
        event.setCancelled(true);
    }
    */
}
