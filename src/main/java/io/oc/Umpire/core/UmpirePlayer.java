package io.oc.Umpire.core;

import io.oc.Umpire.core.UmpireMatch;
import io.oc.Umpire.core.UmpireTeam;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class UmpirePlayer {
    public UmpirePlayer(Player bukkitPlayer){
        this.bukkitPlayer = bukkitPlayer;
    }

    public UmpireTeam team;
    public UmpireMatch match;
    private ChatColor nameColor;
    public Player bukkitPlayer;
    public void setColor(ChatColor color){
        /*nameColor = color;
        bukkitPlayer.setDisplayName(nameColor + bukkitPlayer.getName() + ChatColor.WHITE);
        bukkitPlayer.setPlayerListName(nameColor + bukkitPlayer.getName() + ChatColor.WHITE);*/
    }
}
