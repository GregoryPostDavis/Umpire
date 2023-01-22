package io.oc.Umpire.listeners;

import io.oc.Umpire.core.State;
import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.UmpirePlayer;
import io.oc.Umpire.core.VictoryCondition;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static org.bukkit.Bukkit.getLogger;

public class VictoryConditionListener implements Listener {
    @EventHandler
    public void onVictoryPlace(BlockPlaceEvent Event){
        UmpirePlayer up = Umpire.getPlayer(Event.getPlayer());
        if (up.match == null){
            return;
        }
        if (up.match.state == State.PLAYING){
            for (VictoryCondition vc : up.match.map.victoryConditions){
                if(Event.getBlock().getLocation().equals(vc.location)){
                    getLogger().info("A block was modified at the vm, checking victory conditions");
                    up.match.checkVictory();
                }
            }
        }
    }
}
