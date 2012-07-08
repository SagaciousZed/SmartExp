package com.envisionred.smartexp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.envisionred.smartexp.commands.ExpCommand;


import MetricsDependencies.Metrics;

/**
 * @author EnvisionRed
 */
public class SmartExp extends JavaPlugin {
    
    private FileConfiguration blockConfig;
    private File blocksFile;
    
    @Override
    public void onEnable() {
        replaceOutdatedConfig();
        if (!(new File(getDataFolder(), "Blocks.yml").exists())) {
            saveResource("Blocks.yml", false);
        }
//        StartMetrics();
        getServer().getPluginManager().registerEvents(new EntityListner(this), this);
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
        
        final ExpCommand expCommand = new ExpCommand(this);
        getCommand("exp").setExecutor(expCommand);
        
        this.getLogger().info("EnvisionRed's SmartExp Enabled :D");
    }
    
    public void replaceOutdatedConfig() {
        if (!new File(this.getDataFolder(), "config.yml").exists()) {
            this.saveDefaultConfig();
        } else {
            if (getConfig().getInt("seriously-do-not-change-this") != 3) {
                this.saveResource("config.yml", true);
            }
        }
    }

    private void StartMetrics() {
        final Logger log = this.getLogger();
        final boolean noMetrics = this.getConfig().getBoolean("opt-out-metrics");

        if (noMetrics) {
            log.info("Metrics will not start because you have opted out.");
        } else {
            try {
                Metrics metrics = new Metrics(this);
                metrics.start();
                log.info("Metrics started successfully for SmartExp");
            } catch (IOException x) {
                log.warning("Metrics failed to start for SmartExp");
            }
        }
    }

    /*
     * Blocks.yml handling
     */
    
    public void reloadBlocksConfig() {
        if (blocksFile == null) {
            blocksFile = new File(this.getDataFolder(), "Blocks.yml");
        }
        blockConfig = YamlConfiguration.loadConfiguration(blocksFile);
        InputStream blocksConfigStream = this.getResource("Blocks.yml");
        if (blocksConfigStream != null) {
            YamlConfiguration blocksDefault = YamlConfiguration.loadConfiguration(blocksConfigStream);
            blockConfig.setDefaults(blocksDefault);
        }
    }

    public FileConfiguration getBlocksConfig() {
        if (blockConfig == null) {
            this.reloadBlocksConfig();
        }
        return blockConfig;
    }

    public void saveBlocksConfig() {
        if (blockConfig == null || blocksFile == null) {
            return;
        }
        try {
            getBlocksConfig().save(blocksFile);
        } catch (IOException x) {
            this.getLogger().severe("Could not save the blocks config file.");
        }
    }

}
