package io.oc.Umpire.listeners;

import io.oc.Umpire.Umpire;
import io.oc.Umpire.core.*;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static org.bukkit.Bukkit.getLogger;

public class VoidListener implements Listener {
    @EventHandler
    public void onPlayerMoveVoid(PlayerMoveEvent Event){
        Player p = Event.getPlayer();
        UmpirePlayer up = Umpire.getPlayer(p);
        if(up.match == null){return;}
        UmpireRegion region = up.match.map.getRegion(Event.getTo());

        GameMode gm = p.getGameMode();
        //gm1 and gm3 players are free to go wherever they please
        if(gm.equals(GameMode.CREATIVE)){return;}
        if(gm.equals(GameMode.SPECTATOR)){return;}

        //pregame, players should get tped back to spawn if they try to leave
        if(up.match.state == State.PREGAME) {
            if(region == null || region.regionType != RegionType.SPAWN){
                p.teleport(up.team.spawnPoint);
            }
        }
        //during the game, non-obs should get killed for entering any region that is not theirs
        //TODO should players be prevented from reentering spawn? right now they are not
        else if(up.match.state == State.PLAYING) {
            if (!up.team.isObs) {
                if (region == null || (region.regionType != RegionType.SPAWN && !region.teams.contains(up.team))){
                    //TODO maybe save inventory here?
                    getLogger().info("Player " + p.getName() + " entered the void");
                    p.getInventory().clear();
                    p.setHealth(0);
                }
            }
        }
        //postgame, everybody can go everywhere
    }

    @EventHandler
    public void onVoidBreakBlockEvent(BlockBreakEvent Event){
        onVoidBlockHandler(Event, Event.getBlock(), Event.getPlayer());
    }

    @EventHandler
    public void onVoidPlaceBlockEvent(BlockPlaceEvent Event){
        onVoidBlockHandler(Event, Event.getBlock(), Event.getPlayer());
    }

    //onVoidBlockHandler decides when to prevent breaking and placing blocks in void and spawn regions
    //TODO this works, and is fairly clear, but is not very OOP
    private void onVoidBlockHandler(Cancellable CancelEvent, Block block, Player player){
        UmpirePlayer up = Umpire.getPlayer(player);
        UmpireMatch match = up.match;
        if(match == null) {return;}
        UmpireMap map = match.map;
        UmpireRegion region = map.getRegion(block.getLocation().add(0.5,0,0.5));
        //Void regions (regions that are not labeled in the xml) should not be in any way editable
        if(region == null){
            CancelEvent.setCancelled(true);
            return;
        }
        //For every other region, creative players should be able to edit
        if(player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        //Spawn regions should not be editable
        if(region.regionType == RegionType.SPAWN){
            CancelEvent.setCancelled(true);
            return;
        }
        //Before and after the match, lanes should not be editable
        if(match.state != State.PLAYING){
            CancelEvent.setCancelled(true);
        }
        //During the game, players should not be able to edit lanes that they do not own
        else{
            if(!up.team.isObs){
                if (region.regionType == RegionType.TEAM){
                    if (!region.teams.contains(up.team)){
                        CancelEvent.setCancelled(true);
                    }
                }
            }
        }
    }
}
