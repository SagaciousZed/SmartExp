package com.envisionred.smartexp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.envisionred.smartexp.commands.CheckCommand;
import com.envisionred.smartexp.commands.ExpCommand;
import com.envisionred.smartexp.commands.HelpCommand;
import com.envisionred.smartexp.commands.ReloadCommand;


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
        if (!(new File(getDataFolder(), "blocks.yml").exists())) {
            saveResource("blocks.yml", false);
        }
        
//        StartMetrics();
        
        getServer().getPluginManager().registerEvents(new EntityListner(this), this);
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);

        final CommandExecutor helpCommandExecutor = new CheckCommand(this);
        final CommandExecutor checkCommandExecutor = new HelpCommand(this);
        final CommandExecutor reloadCommandExecutor = new ReloadCommand(this);
        final ExpCommand expCommand = new ExpCommand(this);
        
        this.getCommand("exp-check").setExecutor(checkCommandExecutor);
        this.getCommand("exp-help").setExecutor(helpCommandExecutor);
        this.getCommand("exp-reload").setExecutor(reloadCommandExecutor);
        
        expCommand.registerCommand("check", checkCommandExecutor);
        expCommand.registerCommand("help", helpCommandExecutor);
        expCommand.registerCommand("reload", reloadCommandExecutor);
        this.getCommand("exp").setExecutor(expCommand);
        
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
            blocksFile = new File(this.getDataFolder(), "blocks.yml");
        }
        blockConfig = YamlConfiguration.loadConfiguration(blocksFile);
        InputStream blocksConfigStream = this.getResource("blocks.yml");
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
