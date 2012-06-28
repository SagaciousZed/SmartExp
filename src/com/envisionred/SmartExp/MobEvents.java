/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.envisionred.SmartExp;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;

/**
 *
 * @author EnvisionRed
 */
public class MobEvents implements Listener{
SmartExp plugin;
public MobEvents(SmartExp instance) {
    plugin = instance;
}
//List of config stuff here


//end list
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
    Player p = (Player) s.getKiller();
    //Now to check what kind of mob and act  
        GetTypeAndAct(event, p, s);
}
public void GiveExp(EntityDeathEvent event, Player player, int xp) {
    Entity mob = event.getEntity();
    EntityType type = mob.getType();
    boolean direct = plugin.getConfig().getBoolean("give-xp-direct", false); //whether to drop xp or not
    boolean notify = plugin.getConfig().getBoolean("notifications", true);
    if (direct == true) {
        event.setDroppedExp(0);
        
        player.giveExp(xp);
                    float xpNew = player.getExp();
            int level = player.getLevel();
            int nextlevel = level + 1;
            float xpNewer = xpNew * (100);
            int xpPercent = Math.round(xpNewer);
            if (notify == true) {
        player.sendMessage(ChatColor.GREEN + "You have been awarded " 
                + ChatColor.RED + xp + ChatColor.GREEN + " experience for killing a "
                + ChatColor.RED + type);
        if (player.hasPermission("SmartExp.check")) {
        player.sendMessage(ChatColor.GREEN + "You are now " + ChatColor.AQUA 
                + xpPercent + "%" + ChatColor.GREEN + " of the way to level "
                + ChatColor.RED + nextlevel);
            }
        }
    } else if (direct == false) {
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
       if(player.hasPermission("SmartExp.check")) {
       player.sendMessage(ChatColor.GREEN + "You are now " + ChatColor.AQUA 
                + xpPercent + "%" + ChatColor.GREEN + " of the way to level "
                + ChatColor.RED + nextlevel);    
       }
            }
            } 
    
}
public void GetTypeAndAct(EntityDeathEvent event, Player p, Entity s){
    int Zombie = plugin.getConfig().getInt("Zombie");
int Ghast = plugin.getConfig().getInt("Ghast");
int Creeper = plugin.getConfig().getInt("Creeper");
int Pig = plugin.getConfig().getInt("Pig");
int Chicken = plugin.getConfig().getInt("Chicken");
int Sheep = plugin.getConfig().getInt("Sheep");
int Cow = plugin.getConfig().getInt("Cow");
int Blaze = plugin.getConfig().getInt("Blaze");
int Slime = plugin.getConfig().getInt("Slime");
int Ocelot = plugin.getConfig().getInt("Ocelot");
int Villager = plugin.getConfig().getInt("Villager");
int Mooshroom = plugin.getConfig().getInt("Mooshroom");
int Squid = plugin.getConfig().getInt("Squid");
int Enderman = plugin.getConfig().getInt("Enderman");
int Wolf = plugin.getConfig().getInt("Wolf");
int ZombiePigman = plugin.getConfig().getInt("ZombiePigman");
int Spider = plugin.getConfig().getInt("Spider");
int Skeleton = plugin.getConfig().getInt("Skeleton");
int CaveSpider = plugin.getConfig().getInt("CaveSpider");
int Silverfish = plugin.getConfig().getInt("Silverfish");
int MagmaCube = plugin.getConfig().getInt("MagmaCube");
int SnowGolem = plugin.getConfig().getInt("SnowGolem");
int IronGolem = plugin.getConfig().getInt("IronGolem");
int EnderDragon = plugin.getConfig().getInt("EnderDragon");
int Giant = plugin.getConfig().getInt("Giant");
int BabyAnimal = plugin.getConfig().getInt("BabyAnimal");
int player = plugin.getConfig().getInt("Player");
    if (s instanceof Zombie) {
     GiveExp(event, p, Zombie);
 }
else if (s instanceof Ghast) {
     GiveExp(event, p, Ghast);
 }
else if (s instanceof Creeper) {
     GiveExp(event, p, Creeper);
 }
else if (s instanceof Pig) {
        Ageable j = (Ageable) event.getEntity();
if (!j.isAdult()) {
   GiveExp(event, p, BabyAnimal);
} else {
     GiveExp(event, p, Pig); }
 }
else if (s instanceof Chicken) {
            Ageable j = (Ageable) event.getEntity();
if (!j.isAdult()) {
   GiveExp(event, p, BabyAnimal);
} else {
     GiveExp(event, p, Chicken); }
}
else if (s instanceof Sheep) {
            Ageable j = (Ageable) event.getEntity();
if (!j.isAdult()) {
   GiveExp(event, p, BabyAnimal);
} else {
     GiveExp(event, p, Sheep); }
}
else if (s instanceof Cow) {
            Ageable j = (Ageable) event.getEntity();
if (!j.isAdult()) {
   GiveExp(event, p, BabyAnimal);
} else {
     GiveExp(event, p, Cow); }
}
else if (s instanceof Blaze) {
    GiveExp(event, p, Blaze);
} 
else if (s instanceof Slime) {
    GiveExp(event, p, Slime);
} 
else if (s instanceof Ocelot) {
            Ageable j = (Ageable) event.getEntity();
if (!j.isAdult()) {
   GiveExp(event, p, BabyAnimal);
} else {
     GiveExp(event, p, Ocelot); }
}
else if (s instanceof Villager) {
            Ageable j = (Ageable) event.getEntity();
if (!j.isAdult()) {
   GiveExp(event, p, BabyAnimal);
} else {
     GiveExp(event, p, Villager); }
} 
else if (s instanceof MushroomCow) {
            Ageable j = (Ageable) event.getEntity();
if (!j.isAdult()) {
   GiveExp(event, p, BabyAnimal);
} else {
     GiveExp(event, p, Mooshroom); }
} 
else if (s instanceof Squid) {
    GiveExp (event, p, Squid);
} 
else if (s instanceof Wolf) {
            Ageable j = (Ageable) event.getEntity();
if (!j.isAdult()) {
   GiveExp(event, p, BabyAnimal);
} else {
     GiveExp(event, p,Wolf); }
} 
else if (s instanceof Enderman) {
    GiveExp (event, p, Enderman);
}
else if (s instanceof PigZombie) {
    GiveExp (event, p, ZombiePigman);
} 
else if (s instanceof Spider) {
    GiveExp (event, p, Spider);
} 
else if (s instanceof Skeleton) {
    GiveExp (event, p, Skeleton);
}
else if (s instanceof CaveSpider) {
    GiveExp(event,p, CaveSpider);
}
else if (s instanceof Silverfish) {
    GiveExp(event, p, Silverfish);
}
else if (s instanceof Snowman) {
    GiveExp(event, p , SnowGolem);
} 
else if (s instanceof IronGolem) {
    GiveExp(event, p , IronGolem);
} 
else if (s instanceof EnderDragon) {
    GiveExp(event, p, EnderDragon);
}
else if (s instanceof Giant) {
    GiveExp(event, p, Giant);
}
else if (s instanceof MagmaCube) {
    GiveExp(event, p, MagmaCube);
}
else if (s instanceof Player) {
    GiveExp(event, p, player);

        }
    }
}

