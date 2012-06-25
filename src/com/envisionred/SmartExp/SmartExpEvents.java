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
public class SmartExpEvents implements Listener{
SmartExp plugin;
FileConfiguration config;
public SmartExpEvents(SmartExp instance) {
    plugin = instance;
    config = plugin.getConfig();
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
    boolean direct = config.getBoolean("give-xp-direct", false); //whether to drop xp or not
    if (direct == true) {
        event.setDroppedExp(0);
        player.giveExp(xp);
        player.sendMessage(ChatColor.GREEN + "You have been awarded " 
                + ChatColor.RED + xp + ChatColor.GREEN + " experience for killing a "
                + ChatColor.RED + type);
    } else {
       event.setDroppedExp(xp);
       player.sendMessage(ChatColor.GREEN + "You have been awarded " 
       + ChatColor.RED + xp + ChatColor.GREEN + " experience for killing a "
       + ChatColor.RED + type);
    } 
    
}
public void GetTypeAndAct(EntityDeathEvent event, Player p, Entity s){
    int Zombie = config.getInt("Zombie");
int Ghast = config.getInt("Ghast");
int Creeper = config.getInt("Creeper");
int Pig = config.getInt("Pig");
int Chicken = config.getInt("Chicken");
int Sheep = config.getInt("Sheep");
int Cow = config.getInt("Cow");
int Blaze = config.getInt("Blaze");
int Slime = config.getInt("Slime");
int Ocelot = config.getInt("Ocelot");
int Villager = config.getInt("Villager");
int Mooshroom = config.getInt("Mooshroom");
int Squid = config.getInt("Squid");
int Enderman = config.getInt("Enderman");
int Wolf = config.getInt("Wolf");
int ZombiePigman = config.getInt("ZombiePigman");
int Spider = config.getInt("Spider");
int Skeleton = config.getInt("Skeleton");
int CaveSpider = config.getInt("CaveSpider");
int Silverfish = config.getInt("Silverfish");
int MagmaCube = config.getInt("MagmaCube");
int SnowGolem = config.getInt("SnowGolem");
int IronGolem = config.getInt("IronGolem");
int EnderDragon = config.getInt("EnderDragon");
int Giant = config.getInt("Giant");
int BabyAnimal = config.getInt("BabyAnimal");
int player = config.getInt("Player");
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

