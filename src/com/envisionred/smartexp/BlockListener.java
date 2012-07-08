package com.envisionred.smartexp;

import java.text.MessageFormat;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * 
 * @author EnvisionRed
 */
public class BlockListener implements Listener {

    private final static String AWARD = ChatColor.GREEN + "You have been awarded " + ChatColor.RED + "{0}" + ChatColor.GREEN + " exp for breaking a " + ChatColor.RED + "{1}" + ChatColor.GREEN + " block.";
    private final static String PROGRESS = ChatColor.GREEN + "You are now " + ChatColor.AQUA + "{0}%" + ChatColor.GREEN + " of the way to level " + ChatColor.RED + "{1}";
    
    private SmartExp plugin;

    public BlockListener(SmartExp instance) {
        this.plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreak(BlockBreakEvent event) {
        final ConfigurationSection blocksConfSection = plugin.getBlocksConfig().getConfigurationSection("Blocks");
        final String typeID = String.valueOf(event.getBlock().getTypeId());
        if (blocksConfSection.contains(typeID)) {
            this.giveExp(event.getPlayer(), 
                    blocksConfSection.getConfigurationSection(typeID).getInt("exp"),
                    event.getBlock().getType());
        }
    }

    private void giveExp(Player player, int exp, Material material) {
        player.giveExp(exp);
        if (plugin.getConfig().getBoolean("notifications")) {
            player.sendMessage(MessageFormat.format(AWARD, exp, material));
            if (player.hasPermission("SmartExp.check")) {
                final int nextlevel = player.getLevel() + 1;
                final int xpPercent = Math.round(player.getExp() * (100));
                player.sendMessage(MessageFormat.format(PROGRESS, xpPercent, nextlevel));
            }
        }
    }
}
