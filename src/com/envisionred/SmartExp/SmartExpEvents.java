/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.envisionred.SmartExp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;

/**
 *
 * @author EnvisionRed
 */
public class SmartExpEvents implements Listener{
SmartExp plugin;
public SmartExpEvents(SmartExp instance) {
    plugin = instance;
}
public FileConfiguration config = plugin.getConfig();

@EventHandler
public void Death(EntityDeathEvent event) {
    if (!(event.getEntity() instanceof LivingEntity)) {
        return;
    }
    LivingEntity s = event.getEntity();
    Entity k = s.getKiller();
    if (!(k instanceof Player)) {
        return;
    }
    
}
public void GetType(Entity mob) {
    
}
}
