package io.oc.Umpire;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import io.oc.Umpire.core.MapCleaner;
import io.oc.Umpire.core.UmpireMatch;
import io.oc.Umpire.core.UmpirePlayer;
import io.oc.Umpire.listeners.*;
import io.oc.Umpire.utils.MapUtils;

import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ArgumentEntity;
import net.minecraft.commands.arguments.selector.EntitySelector;

import org.bukkit.command.PluginCommand;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Umpire extends JavaPlugin{
    private static Umpire instance = null;
    public Set<UmpireMatch> matches = new HashSet<>();
    private static Set<UmpirePlayer> players = new HashSet<>();
    public static Scoreboard scoreboard;

    @Override
    public void onEnable() {
        instance = this;
        scoreboard = getServer().getScoreboardManager().getMainScoreboard();

        getLogger().info("Adding Listeners");
        getServer().getPluginManager().registerEvents(new SwimmingListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        getServer().getPluginManager().registerEvents(new LiquidListener(), this);
        getServer().getPluginManager().registerEvents(new VoidListener(), this);
        getServer().getPluginManager().registerEvents(new VictoryConditionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new ViewInventoryListener(), this);

        Commands commands = new Commands();
        CommandHandler handler = new CommandHandler();

        for (String command : commands.keySet()){
            getLogger().info("Adding handler for: " + command);
            PluginCommand pluginCommand = this.getCommand(command);
            if(pluginCommand != null){
                pluginCommand.setExecutor(handler);
            }
            else{
                getLogger().info("Command " + command + "has not been added to plugin.yml file, skipping it");
            }
        }

        int deleted = MapUtils.cleanOrphanedFolders();
        getLogger().info("Cleaned up " + deleted + " orphaned world folders");

        MapCleaner cleaner = new MapCleaner(this);
        cleaner.runTaskTimerAsynchronously(getInstance(), 0L, 30L*20L);

        /*try {
            .registerNewTeam("testing testing 123");
            .registerNewTeam("321 gnitset");
        } catch(Exception e){
            getLogger().info("Caught error + " + e.toString());
        }*/

        /*try {
            Class<?> es = Class.forName("net.minecraft.commands.arguments.selector.EntitySelector");
            for(Method m : es.getDeclaredMethods()){
                getLogger().info(m.getName() + " : " + m.getAnnotatedReturnType());
            }
        } catch (Exception e) {
            getLogger().info("Caught error while reflecting. Don't medidate so hard.");
            getLogger().info(e.toString());
        }*/


        wrapCommands();


    }

    private void wrapCommands() {
        CraftServer cs = (CraftServer)getServer();
        CommandDispatcher<CommandListenerWrapper> commandDispatcher = cs.getServer().vanillaCommandDispatcher.a();

        RootCommandNode<CommandListenerWrapper> rcn = commandDispatcher.getRoot();

        List<CommandNode> cnEdited = new ArrayList<CommandNode>();

        for (CommandNode<CommandListenerWrapper> cn : rcn.getChildren()){
            getLogger().info(cn.getName());
            List<ArgumentCommandNode> acnEditList = new ArrayList<ArgumentCommandNode>();
            for(CommandNode<CommandListenerWrapper> childnode : cn.getChildren()){
                //getLogger().info(childnode.getName() + ":" + childnode.toString() + ":" + childnode.getClass().getSimpleName() + ":" + childnode.getCommand());
                if(childnode instanceof ArgumentCommandNode) {
                    ArgumentCommandNode acn = (ArgumentCommandNode<?, ?>) childnode;
                    if (acn.getType() instanceof ArgumentEntity) {
                        acnEditList.add(acn);
                    }
                }
            }
            for(ArgumentCommandNode acnEdit : acnEditList){
                getLogger().info("found argument: " +  acnEdit.getType());
                getLogger().info(String.valueOf(acnEdit.getChildren().size()) + acnEdit.getCommand() + acnEdit.getName());

                //ArgumentEntity newAE = (ArgumentEntity) getArgumentEntityProxy((ArgumentEntity) acnEdit.getType());

                ArgumentCommandNode<CommandListenerWrapper, EntitySelector> newAcn = new ArgumentCommandNode<CommandListenerWrapper, EntitySelector>(
                        acnEdit.getName(),
                        (ArgumentEntity)getArgumentEntityProxy((ArgumentEntity) acnEdit.getType()),
                        acnEdit.getCommand(),
                        acnEdit.getRequirement(),
                        acnEdit.getRedirect(),
                        acnEdit.getRedirectModifier(),
                        acnEdit.isFork(),
                        acnEdit.getCustomSuggestions());


                cn.removeCommand(acnEdit.getName());
                cn.addChild(newAcn);
                cnEdited.add(cn);
            }
        }
        for(CommandNode cnEdit : cnEdited){
            rcn.addChild(cnEdit);
        }
    }

    public static Umpire getInstance(){
        return instance;
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

    public void addMatch(UmpireMatch match){
        matches.add(match);
    }

    public static UmpirePlayer getPlayer(Player player){
        for (UmpirePlayer umpirePlayer : players){
            if (umpirePlayer.bukkitPlayer.getUniqueId().equals(player.getUniqueId())){
                return umpirePlayer;
            }
        }
        UmpirePlayer newPlayer = new UmpirePlayer(player);
        players.add(newPlayer);
        return newPlayer;
    }



    public boolean isPlayer(Player player){
        for (UmpirePlayer umpirePlayer : players){
            if (umpirePlayer.bukkitPlayer.getUniqueId().equals(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public static UmpireMatch getMatch(String worldName){
        //Arrays.stream(worldFolders).map(file -> getMatch(file.getName()).map.worldName != null).reduce(true, (prev, next) -> (prev && next));
        for (UmpireMatch um : getInstance().matches){
            if (um.map.worldName.equalsIgnoreCase(worldName)){
                return um;
            }
        }
        return null;
    }

    public ArgumentEntity getArgumentEntityProxy(ArgumentEntity argumentEntity){
        UmpireArgumentEntity umpireArgumentEntity = new UmpireArgumentEntity(argumentEntity);
        ArgumentEntity proxy = (ArgumentEntity) Proxy.newProxyInstance(ArgumentEntity.class.getClassLoader(),
                new Class[] { ArgumentEntity.class },
                umpireArgumentEntity);
        return proxy;
    }
}
