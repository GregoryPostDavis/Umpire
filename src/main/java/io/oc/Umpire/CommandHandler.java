package io.oc.Umpire;

import io.oc.Umpire.core.UmpirePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class CommandHandler implements CommandExecutor{
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        getLogger().info("Attempting to run command: " + command.getName());

        Commands commands = new Commands();
        UmpireCommand umpireCommand = commands.get(command.getName());

        if (umpireCommand != null){
            getLogger().info("Found command: " + umpireCommand);
        }
        else{
            getLogger().info("Did not find command");
        }

        try {
            return umpireCommand.run(args, commandSender);
        } catch (Exception e){
            getLogger().info(e.toString());
        }

        return false;
    }
}
