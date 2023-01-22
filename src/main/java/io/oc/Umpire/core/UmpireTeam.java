package io.oc.Umpire.core;

import io.oc.Umpire.Umpire;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;
import java.util.Set;

import static org.bukkit.Bukkit.getLogger;

public class UmpireTeam {
    public Set<UmpirePlayer> players = new HashSet<UmpirePlayer>();
    UmpireRegion region;
    public UmpireMatch match;
    public ChatColor color;
    public String teamname;
    public Location spawnPoint;
    public boolean readyState;
    public boolean isObs;
    Team scoreboardTeam;
    public UmpireTeam(ChatColor color, String teamname, boolean isObs){
        this.color = color;
        this.teamname = teamname;
        this.isObs = isObs;
        scoreboardTeam = Umpire.scoreboard.getTeam(teamname);
        if(scoreboardTeam == null){
            scoreboardTeam = Umpire.scoreboard.registerNewTeam(teamname);
        }
        scoreboardTeam.setColor(this.color);
    }
    public void addPlayer(UmpirePlayer player) {
        getLogger().info("Running addplayer");
        players.add(player);
        player.team = this;
        player.setColor(this.color);
        scoreboardTeam.addEntry(player.bukkitPlayer.getName());
    }
    public void removePlayer(UmpirePlayer player) {
        players.remove(player);
        player.team = null;
        player.setColor(ChatColor.WHITE);
        scoreboardTeam.removeEntry(player.bukkitPlayer.getName());
    }
}
