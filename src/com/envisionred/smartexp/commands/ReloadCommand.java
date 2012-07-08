package com.envisionred.smartexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.envisionred.smartexp.SmartExp;

public class ReloadCommand implements CommandExecutor {

    private SmartExp plugin;

    public ReloadCommand(SmartExp plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("SmartExp.reload")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission for this command.");
        } else {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "SmartExp config reloaded.");
        }
        return true;
    }

}
