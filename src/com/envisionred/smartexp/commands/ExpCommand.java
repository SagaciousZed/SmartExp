package com.envisionred.smartexp.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.envisionred.smartexp.SmartExp;

public class ExpCommand implements CommandExecutor {
    
    private SmartExp plugin;

    public ExpCommand(SmartExp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "SmartExp version " + plugin.getDescription().getVersion() + " by" + ChatColor.DARK_RED + " EnvisionRed");
        sender.sendMessage(ChatColor.GREEN + "Do " + ChatColor.AQUA + "/exp help " + ChatColor.GREEN + "to see help for the plugin.");
        return true;
    }

}
