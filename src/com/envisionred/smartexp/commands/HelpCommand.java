package com.envisionred.smartexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.envisionred.smartexp.SmartExp;


public class HelpCommand implements CommandExecutor {
    
    private SmartExp plugin;

    public HelpCommand(SmartExp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player && sender.hasPermission("SmartExp.check")) {
            sender.sendMessage(ChatColor.AQUA + "/exp check:" + ChatColor.GREEN + " Shows you your Exp stats.");
        }
        if (sender.hasPermission("SmartExp.reload")) {
            sender.sendMessage(ChatColor.AQUA + "/exp reload:" + ChatColor.GREEN + " Reloads the configuration");
        }
        if (!sender.hasPermission("SmartExp.check") && !sender.hasPermission("SmartExp.reload")) {
            sender.sendMessage(ChatColor.RED + "Sorry, you don't have permission for any of the commands for SmartExp, so no use showing the help page to you.");
        }
        return true;
    }

}
