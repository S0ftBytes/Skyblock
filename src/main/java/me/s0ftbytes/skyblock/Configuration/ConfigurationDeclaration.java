package me.s0ftbytes.skyblock.Configuration;

import me.s0ftbytes.skyblock.Skyblock;

public class ConfigurationManager {

    private Skyblock instance;
    public ConfigurationManager(Skyblock skyblock){
        this.instance = skyblock;
    }

    public void createConfigurationFiles(){
        createStatsConfigurationFile();
    }

    public void createStatsConfigurationFile(){
        ConfigurationFile statsConfig = new ConfigurationFile("stats");

        statsConfig.create();
        statsConfig.load();

        instance.getRegistryManager().getStatRegistry().getStats();
    }
}
