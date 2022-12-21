package me.s0ftbytes.skyblock.Utils;

import me.s0ftbytes.skyblock.Configuration.ConfigurationDeclaration;
import me.s0ftbytes.skyblock.Configuration.ConfigurationFile;

public class StatUtils {

    public static Number getDefaultStatValue(String statID){
        ConfigurationFile configurationFile = ConfigurationDeclaration.STATS.getFile();

        return configurationFile.getConfig().getDouble(statID + ".default");
    }

    public static String getStatDisplay(String statID, Number value){
        ConfigurationFile configurationFile = ConfigurationDeclaration.STATS.getFile();

        return configurationFile.getConfig().getString(statID + ".display").replace("%value%", value.toString());
    }
}
