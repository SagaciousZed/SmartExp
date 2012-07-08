package com.envisionred.smartexp.commands;

import java.text.MessageFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.envisionred.smartexp.SmartExp;

public class CheckCommand implements CommandExecutor {

    private static final String INSUFFICIANT_PERMISSIONS =
            ChatColor.RED + "You do not have permission for this command.";
    private static final String PLAYER_NOT_FOUND =
            ChatColor.RED + "Player {0} was not found.";
    private static final String CURRENT =
            "{0} currently level " + ChatColor.RED + "{1}" + ChatColor.GREEN + " and " + ChatColor.AQUA + "{2}% " + ChatColor.GREEN + " of the way to level " + ChatColor.RED + "{3}";
    private static final String SALUTATION =
            ChatColor.RED + "{0}" + ChatColor.GREEN + " is";

    private SmartExp plugin;

    public CheckCommand(SmartExp plugin) {
        if (plugin == null)
            throw new IllegalArgumentException("plugin cannot be null");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        boolean checkingOther;
        // Resolve Player
        if (args.length == 0 && sender.hasPermission("SmartExp.check") && sender instanceof Player) {
            player = (Player) sender;
            checkingOther = false;
        } else if (args.length > 0 && sender.hasPermission("SmartExp.check.other")) {
            player = plugin.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(MessageFormat.format(PLAYER_NOT_FOUND, args[0]));
                return true;
            }
            checkingOther = true;
        } else {
            if (!sender.hasPermission("SmartExp.check") || !sender.hasPermission("SmartExp.check.other")) {
                // Lacks Permission
                sender.sendMessage(INSUFFICIANT_PERMISSIONS);
            } else {
                sender.sendMessage(ChatColor.RED + "Must specify a player");
            }
            return true;
        }
        final int level = player.getLevel();
        final int nextlevel = level + 1;
        final int xpPercent = Math.round(player.getExp() * 100f);
        final String salutation = checkingOther ? MessageFormat.format(SALUTATION, player.getName())
                : ChatColor.GREEN + "You are";
        sender.sendMessage(MessageFormat.format(CURRENT, salutation, level, xpPercent, nextlevel));
        return true;
    }

}
