package io.oc.Umpire.core;

import org.bukkit.Note;
import org.bukkit.scheduler.BukkitRunnable;


public class CountdownTimer extends BukkitRunnable{

    private int count = 5;
    private final UmpireMatch match;


    public CountdownTimer(UmpireMatch match) {
        this.match = match;
    }

    @Override
    public void run() {
        match.broadcastTitle(String.valueOf(count), "");
        if (1 <= count && count <= 3){
            match.broadcastNote(new Note(0, Note.Tone.F, true));
        }
        if (count <= 0){
            cancel();//FIXME run keeps getting ran every second, ever aver it finishes...
            match.startMatch();
        }
        count--;
    }
}
