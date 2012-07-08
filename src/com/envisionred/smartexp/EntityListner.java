package com.envisionred.smartexp;

import java.text.MessageFormat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * 
 * @author EnvisionRed
 */
public class EntityListner implements Listener {

    private final static String PROGRESS = ChatColor.GREEN + "You are now " + ChatColor.AQUA + "{0}%" + ChatColor.GREEN + " of the way to level " + ChatColor.RED + "{1}";
    private final static String AWARD= ChatColor.GREEN + "You have been awarded " + ChatColor.RED + "{0}" + ChatColor.GREEN + " experience for killing a " + ChatColor.RED + "{1}";
    private final static String DROP = ChatColor.GREEN + "You killed a " + ChatColor.RED + "{0}" + ChatColor.GREEN + " and it dropped " + ChatColor.RED + "{1}" + ChatColor.GREEN + " experience.";
    
    private SmartExp plugin;

    public EntityListner(SmartExp instance) {
        plugin = instance;
    }

    @EventHandler
    public void Death(EntityDeathEvent event) {
        // whether to drop experience or not
        final boolean direct = this.plugin.getConfig().getBoolean("give-xp-direct");
        final boolean notify = this.plugin.getConfig().getBoolean("notifications");
        
        final Player player = event.getEntity().getKiller();
        final int xp = this.plugin.getConfig().getInt(event.getEntityType().getName());
        
        if (direct) {
            event.setDroppedExp(0);
            player.giveExp(xp);
        } else {
            event.setDroppedExp(xp);
        }
        
        if (notify) {
            player.sendMessage(MessageFormat.format((direct) ? AWARD : DROP, event.getEntityType(), xp));
            if (player.hasPermission("SmartExp.check")) {
                final int xpPercent = Math.round(player.getExp() * (100));
                final int nextLevel = player.getLevel() + 1;
                player.sendMessage(MessageFormat.format(PROGRESS, xpPercent, nextLevel));
            }
        }
    }
}
