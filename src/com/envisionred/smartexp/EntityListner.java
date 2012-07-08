/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.envisionred.smartexp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * 
 * @author EnvisionRed
 */
public class EntityListner implements Listener {

    private SmartExp plugin;

    public EntityListner(SmartExp instance) {
        plugin = instance;
    }

    @EventHandler
    public void Death(EntityDeathEvent event) {
        final Entity entity = event.getEntity();
        final Player player = event.getEntity().getKiller();
        
        // Now to check what kind of mob and act
        final int expiranceAmmount = plugin.getConfig().getInt(entity.getType().getName());
        this.giveExperiance(event, player, expiranceAmmount);
        
    }

    public void giveExperiance(EntityDeathEvent event, Player player, int xp) {
        final Entity mob = event.getEntity();
        final EntityType type = mob.getType();
        
        final boolean direct = plugin.getConfig().getBoolean("give-xp-direct", false);
        // whether to drop xp or not
        
        final boolean notify = plugin.getConfig().getBoolean("notifications", true);
        if (direct) {
            event.setDroppedExp(0);

            player.giveExp(xp);
            float xpNew = player.getExp();
            int level = player.getLevel();
            int nextlevel = level + 1;
            float xpNewer = xpNew * (100);
            int xpPercent = Math.round(xpNewer);
            if (notify) {
                player.sendMessage(ChatColor.GREEN + "You have been awarded "
                        + ChatColor.RED + xp + ChatColor.GREEN + " experience for killing a "
                        + ChatColor.RED + type);
                if (player.hasPermission("SmartExp.check")) {
                    player.sendMessage(ChatColor.GREEN + "You are now " + ChatColor.AQUA
                            + xpPercent + "%" + ChatColor.GREEN + " of the way to level "
                            + ChatColor.RED + nextlevel);
                }
            }
        } else {
            event.setDroppedExp(xp);
            float xpNew = player.getExp();
            int level = player.getLevel();
            int nextlevel = level + 1;
            float xpNewer = xpNew * (100);
            int xpPercent = Math.round(xpNewer);
            if (notify) {
                player.sendMessage(ChatColor.GREEN + "You killed a " + ChatColor.RED
                        + type + ChatColor.GREEN + " and it dropped "
                        + ChatColor.RED + xp + ChatColor.GREEN + " experience.");
                if (player.hasPermission("SmartExp.check")) {
                    player.sendMessage(ChatColor.GREEN + "You are now " + ChatColor.AQUA
                            + xpPercent + "%" + ChatColor.GREEN + " of the way to level "
                            + ChatColor.RED + nextlevel);
                }
            }
        }

    }

    public void GetTypeAndAct(EntityDeathEvent event, Player player, Entity entity) {
        
    }
}
