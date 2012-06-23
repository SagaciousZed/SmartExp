package com.envisionred.SmartExp;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import MetricsDependencies.Metrics;
import com.envisionred.SmartExp.SmartExpEvents;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author EnvisionRed
 */
public class SmartExp extends JavaPlugin{
Logger log = Logger.getLogger("Minecraft");
@Override
public void onDisable() {
    log.info("[SmartExp] EnvisionRed's SmartExp disabled :(");
    
}
@Override
public void onEnable() {
    log.info("[SmartExp] EnvisionRed's SmartExp Enabled :D");
    Enableconfig();
    StartMetrics();
    getServer().getPluginManager().registerEvents(new SmartExpEvents(this), this);
    final FileConfiguration config = this.getConfig();
}



//Methods for OnEnable
//Most of this stuff gets handled through Eventhandler
public void Enableconfig() {
            File configFile = new File(this.getDataFolder() + "/config.yml");
if(!configFile.exists())
         {
         this.saveDefaultConfig();
        }
    }
public void StartMetrics() {
    boolean noMetrics = this.getConfig().getBoolean("opt-out-metrics", false);
        if (this == null) {
            log.warning("[SmartExp]Plugin is null so metrics failed to start");
        }
   else if (noMetrics == true) {
        log.info("[SmartExp] Metrics will not start because you have opted out.");
    } else {
        try {
               Metrics metrics = new Metrics(this);
           metrics.start();
           log.info("[SmartExp] Metrics started successfully for SmartExp");
        }catch (IOException x) {
            log.warning("[SmartExp] Metrics failed to start for SmartExp");
        }
    }
}




}
