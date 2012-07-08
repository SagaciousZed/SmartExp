/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.envisionred.smartexp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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

    Map<Integer, Integer> expMap = new HashMap<Integer, Integer>();
    private SmartExp plugin;

    public BlockListener(SmartExp instance) {
        this.plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreak(BlockBreakEvent event) {
        PutBlocks();
        final int typeInt = event.getBlock().getTypeId();
        if (expMap.containsKey(typeInt)) {
            GiveExp(event.getPlayer(),
                    expMap.get(typeInt),
                    event.getBlock().getType());
        }
    }

    public void PutBlocks() {
        final FileConfiguration blockConfig = plugin.getBlocksConfig();
        final Set<String> keys = blockConfig.getConfigurationSection("Blocks").getKeys(false);
        for (String block : keys) {
            try {
                Integer blockID = Integer.valueOf(block);
                Integer expAmount = Integer.valueOf(blockConfig.getString("Blocks." + block + ".exp"));
                expMap.put(blockID, expAmount);
            } catch (NumberFormatException x) {
                final Logger log = plugin.getLogger();
                log.warning("Invalid blockID or exp amount specified in SmartExp config");
                log.warning("blockID as defined in config: " + block);
                log.warning("Exp amount for " + block + " as defined in config: "
                        + blockConfig.getString("Blocks." + block + ".exp"));
            }
        }
    }

    public void GiveExp(Player player, int exp, Material material) {
        player.giveExp(exp);
        boolean notify = plugin.getConfig().getBoolean("notifications");
        if (notify) {
            player.sendMessage(ChatColor.GREEN + "You have been awarded "
                    + ChatColor.RED + exp + ChatColor.GREEN + " exp for breaking a "
                    + ChatColor.RED + material + ChatColor.GREEN + " block.");
            if (player.hasPermission("SmartExp.check")) {
                int nextlevel = player.getLevel() + 1;
                float xpNewer = player.getExp() * (100);
                int xpPercent = Math.round(xpNewer);
                player.sendMessage(ChatColor.GREEN + "You are now " + ChatColor.AQUA
                        + xpPercent + "%" + ChatColor.GREEN + " of the way to level "
                        + ChatColor.RED + nextlevel);
            }
        }
    }
}
