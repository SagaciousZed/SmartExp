package com.envisionred.smartexp.commands;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Java15Compat;

import com.envisionred.smartexp.SmartExp;


public class BaseCommand implements CommandExecutor {

    private SmartExp plugin;
    
    public final CommandExecutor expHelpCommand;
    public final CommandExecutor expCommand;
    public final CommandExecutor reloadCommand;
    public final CommandExecutor checkCommand;
    
    public BaseCommand(SmartExp plugin) {
        this.plugin = plugin;
        expHelpCommand = new HelpCommand(plugin);
        expCommand = new ExpCommand(plugin);
        reloadCommand = new ReloadCommand(plugin);
        checkCommand = new CheckCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("exp")) {
            if (args.length < 1) {
                expCommand.onCommand(sender, cmd, label, popArray(args));
            }
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    expHelpCommand.onCommand(sender, cmd, label, popArray(args));
                }
                if (args[0].equalsIgnoreCase("check")) {
                    checkCommand.onCommand(sender, cmd, label, popArray(args));
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    reloadCommand.onCommand(sender, cmd, label, popArray(args));
                }
                return true;
            }
        }
        return false;
    }
   
    private <T> T[] popArray(T[] args) {
        return (args.length >= 2) ? Java15Compat.Arrays_copyOfRange(args, 1, args.length) : 
            (T[]) Array.newInstance(args.getClass().getComponentType(), 0);
    }

}
