package io.oc.Umpire.listeners;

import io.oc.Umpire.core.State;
import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.UmpirePlayer;
import io.oc.Umpire.core.VictoryCondition;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class VictoryConditionListener implements Listener {
    @EventHandler
    public void onVictoryPlace(BlockPlaceEvent Event){
        UmpirePlayer up = Umpire.getPlayer(Event.getPlayer());
        if (up.match == null){
            return;
        }
        if (up.match.state == State.PLAYING){
            for (VictoryCondition vc : up.match.map.victoryConditions){
                if(Event.getBlock().getLocation().distanceSquared(vc.location) <= 4){
                    up.match.checkVictory();
                }
            }
        }
    }
}
