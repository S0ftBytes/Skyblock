package me.s0ftbytes.skyblock.Configuration;

import me.s0ftbytes.skyblock.Skyblock;

public class ConfigurationDeclaration {

    private static ConfigurationFile statsConfig;

    private Skyblock instance;
    public ConfigurationDeclaration(Skyblock skyblock){
        this.instance = skyblock;
    }

    public static ConfigurationFile getStatsConfiguration(){
        if(statsConfig == null) statsConfig = new ConfigurationFile("stats");

        return statsConfig;
    }
}
