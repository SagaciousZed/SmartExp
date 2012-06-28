/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.envisionred.SmartExp;

import java.util.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Player;

/**
 *
 * @author EnvisionRed
 */
public class BlockEvents implements Listener{
    Map<Integer, Integer> ExpMap = new HashMap<Integer, Integer>();
SmartExp plugin;
public BlockEvents(SmartExp instance) {
    plugin = instance;
}
    
    @EventHandler (priority = EventPriority.HIGHEST)
    public void BlockBreak(BlockBreakEvent event) {   
        PutBlocks();
        Player player = event.getPlayer();
        int typeInt = event.getBlock().getTypeId();
        Integer type = Integer.valueOf(typeInt);
        Material material = event.getBlock().getType();
        if (ExpMap.containsKey(type)) {
            int exp = (int) ExpMap.get(type);
            GiveExp(player, exp, material);
        }
        }
    public void PutBlocks(){       
    Set<String> keys = plugin.getConfig().getConfigurationSection("Blocks").getKeys(false);
     for (String block : keys) {
         try{
         Integer blockID = Integer.valueOf(block);
         Integer expAmount = Integer.valueOf(plugin.getConfig().getString("Blocks." +block+".exp"));       
         ExpMap.put(blockID, expAmount);
         } catch (NumberFormatException x) {
             return;
         }
     }       
    }
   
    public void GiveExp(Player player, int exp, Material material) {
       player.giveExp(exp);
       boolean notify = plugin.getConfig().getBoolean("notifications", true);
       if (notify == true) {
           player.sendMessage(ChatColor.GREEN + "You have been awarded "
                   + ChatColor.RED + exp + ChatColor.GREEN + " exp for breaking a "
                   + ChatColor.RED + material + ChatColor.GREEN + " block.");
           if (player.hasPermission("SmartExp.check")) {
                                   float xpNew = player.getExp();
            int level = player.getLevel();
            int nextlevel = level + 1;
            float xpNewer = xpNew * (100);
            int xpPercent = Math.round(xpNewer);
            player.sendMessage(ChatColor.GREEN + "You are now " + ChatColor.AQUA 
                + xpPercent + "%" + ChatColor.GREEN + " of the way to level "
                + ChatColor.RED + nextlevel);
           }
       }
    }
    }

