/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.envisionred.SmartExp;

import java.io.File;

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
File mobsFile;
FileConfiguration mobConfig;
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
    boolean notify = plugin.getConfig().getBoolean("mob-notifications", true);
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
	mobsFile = new File(plugin.getDataFolder() + "/Mobs.yml");
	mobConfig = plugin.getMobsConfig();
int Zombie = mobConfig.getInt("Zombie");
int Ghast = mobConfig.getInt("Ghast");
int Creeper = mobConfig.getInt("Creeper");
int Pig = mobConfig.getInt("Pig");
int Chicken = mobConfig.getInt("Chicken");
int Sheep = mobConfig.getInt("Sheep");
int Cow = mobConfig.getInt("Cow");
int Blaze = mobConfig.getInt("Blaze");
int Slime = mobConfig.getInt("Slime");
int Ocelot = mobConfig.getInt("Ocelot");
int Villager = mobConfig.getInt("Villager");
int Mooshroom = mobConfig.getInt("Mooshroom");
int Squid = mobConfig.getInt("Squid");
int Enderman = mobConfig.getInt("Enderman");
int Wolf = mobConfig.getInt("Wolf");
int ZombiePigman = mobConfig.getInt("ZombiePigman");
int Spider = mobConfig.getInt("Spider");
int Skeleton = mobConfig.getInt("Skeleton");
int CaveSpider = mobConfig.getInt("CaveSpider");
int Silverfish = mobConfig.getInt("Silverfish");
int MagmaCube = mobConfig.getInt("MagmaCube");
int SnowGolem = mobConfig.getInt("SnowGolem");
int IronGolem = mobConfig.getInt("IronGolem");
int EnderDragon = mobConfig.getInt("EnderDragon");
int Giant = mobConfig.getInt("Giant");
int BabyAnimal = mobConfig.getInt("BabyAnimal");
int player = mobConfig.getInt("Player");
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

