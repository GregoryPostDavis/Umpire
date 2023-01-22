package io.oc.Umpire.listeners;

import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.UmpireMatch;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import static io.oc.Umpire.Umpire.getInstance;

public class LiquidListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLiquidFlow(BlockFromToEvent event) {
        Block block = event.getToBlock();
        UmpireMatch umpireMatch = null;
        for (UmpireMatch um : getInstance().matches){
            if (um.map.worldName.equals(block.getWorld().getName())){
                umpireMatch = um;
            }
        }
        if (umpireMatch == null){
            return;
        }
        if (umpireMatch.map.getRegion(block.getLocation().add(0.5, 0d, 0.5)) == null){
            //TODO should water be able to flow from a team's region into a spawn region? probably not, but right now they answer is yes
            event.setCancelled(true);
        }
    }
}
