package me.s0ftbytes.skyblock.Stats;

import me.s0ftbytes.skyblock.Configuration.ConfigurationDeclaration;
import me.s0ftbytes.skyblock.Configuration.ConfigurationFile;

public class StatUtils {

    public static Number getDefaultStatValue(String statID){
        ConfigurationFile configurationFile = ConfigurationDeclaration.getStatsConfiguration();

        return configurationFile.getConfig().getDouble("stats." + statID + ".default");
    }

    public static String getStatDisplay(String statID, Number value){
        ConfigurationFile configurationFile = ConfigurationDeclaration.getStatsConfiguration();

        return configurationFile.getConfig().getString("stats." + statID + ".display").replace("%value%", value.toString());
    }
}
