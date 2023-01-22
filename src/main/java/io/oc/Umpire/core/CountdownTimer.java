package io.oc.Umpire.core;

import io.oc.Umpire.core.UmpireMatch;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getLogger;

public class CountdownTimer extends BukkitRunnable{

    private int count = 5;
    private final UmpireMatch match;


    public CountdownTimer(UmpireMatch match) {
        this.match = match;
    }

    @Override
    public void run() {
        match.broadcastTitle(String.valueOf(count), "");
        if (count <= 0){
            cancel();//FIXME run keeps getting ran every second, ever aver it finishes...
            match.startMatch();
        }
        count--;
    }
}
