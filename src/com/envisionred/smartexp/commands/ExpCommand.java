package com.envisionred.smartexp.commands;

import java.lang.reflect.Array;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.Java15Compat;

import com.envisionred.smartexp.SmartExp;

public class ExpCommand implements CommandExecutor {

    private SmartExp plugin;
    private HashMap<String, CommandExecutor> commandMap = new HashMap<String, CommandExecutor>();
    
    public ExpCommand(SmartExp plugin) {
        if (plugin == null) throw new IllegalArgumentException("plugin cannot be null");
        this.plugin = plugin;
        this.registerCommand("help", new HelpCommand(plugin));
        this.registerCommand("reload", new ReloadCommand(plugin));
        this.registerCommand("check", new CheckCommand(plugin));
    }
    
    public void registerCommand(String command, CommandExecutor commandExecutor) {
        this.commandMap.put(command.toLowerCase(), commandExecutor);
    }
    
    public void unregisterCommand(String command) {
        this.commandMap.remove(command.toLowerCase());
    }
    
    public CommandExecutor getCommandExecutor(String command) {
        return this.commandMap.get(command.toLowerCase());
    }
    
    public boolean hasCommand(String command) {
        return this.commandMap.containsKey(command.toLowerCase());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (args.length > 1 && hasCommand(args[0])) {
                CommandExecutor executor = this.getCommandExecutor(args[0]);
                return executor.onCommand(sender, cmd, label, popArray(args));
            } else {
                sender.sendMessage(ChatColor.GREEN + "SmartExp version " + plugin.getDescription().getVersion() + " by" + ChatColor.DARK_RED + " EnvisionRed");
                sender.sendMessage(ChatColor.GREEN + "Do " + ChatColor.AQUA + "/exp help " + ChatColor.GREEN + "to see help for the plugin.");
            }
        return false;
    }
   
    private <T> T[] popArray(T[] args) {
        return (args.length >= 2) ? Java15Compat.Arrays_copyOfRange(args, 1, args.length) : 
            (T[]) Array.newInstance(args.getClass().getComponentType(), 0);
    }
}
