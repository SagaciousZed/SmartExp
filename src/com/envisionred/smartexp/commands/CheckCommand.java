package com.envisionred.smartexp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.envisionred.smartexp.SmartExp;

public class CheckCommand implements CommandExecutor {

    private SmartExp plugin;

    public CheckCommand(SmartExp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("SmartExp.check")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission for this command.");
        }
        if (sender.hasPermission("SmartExp.check.other")) {
            if (args.length > 1) {
                Player player = (plugin.getServer().getPlayer(args[1]));
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Invalid player: " + args[1]);
                    return true;
                }
                float xp = player.getExp();
                int level = player.getLevel();
                int nextlevel = level + 1;
                float xpNew = xp * (100);
                int xpPercent = Math.round(xpNew);
                String playerName = player.getName();
                sender.sendMessage(ChatColor.BLUE + playerName + ChatColor.GREEN + " is currently level "
                        + ChatColor.RED + level + ChatColor.GREEN
                        + " and " + ChatColor.AQUA + xpPercent + "%"
                        + ChatColor.GREEN + " of the way to level "
                        + ChatColor.RED + nextlevel);
                return true;
            }
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can not be sent from the console.");
            return true;
        }
        Player player = (Player) sender;
        float xp = player.getExp();
        int level = player.getLevel();
        int nextlevel = level + 1;
        float xpNew = xp * (100);
        int xpPercent = Math.round(xpNew);
        player.sendMessage(ChatColor.GREEN + "You are currently level "
                + ChatColor.RED + level + ChatColor.GREEN
                + " and " + ChatColor.AQUA + xpPercent + "%"
                + ChatColor.GREEN + " of the way to level "
                + ChatColor.RED + nextlevel);
        return true;

    }

}
