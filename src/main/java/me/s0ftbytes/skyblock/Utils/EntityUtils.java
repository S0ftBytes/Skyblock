package me.s0ftbytes.skyblock.Utils;

import me.s0ftbytes.skyblock.Configuration.ConfigurationDeclaration;
import me.s0ftbytes.skyblock.Configuration.ConfigurationFile;
import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;

import java.text.DecimalFormat;
import java.util.HashMap;

public class EntityUtils {

    public static void updateEntityHealthBar(SkyblockEntity entity){
        LivingEntity livingEntity = entity.getLivingEntity();
        double healthPercentage = (entity.getCurrentHealth() / entity.getStat("health").doubleValue()) * 100;

        DecimalFormat df = new DecimalFormat("#.##");
        healthPercentage = Double.parseDouble(df.format(healthPercentage));

        String healthBar = String.format("&8[&7Lvl &c%s&8] &7%s &8[%s&7%% &l&c❤&8]", entity.getLevel(), entity.getName(), getEntityHealthColouration(healthPercentage));

        livingEntity.setCustomName(StringUtils.format(healthBar));
        livingEntity.setCustomNameVisible(true);
    }

    private static String getEntityHealthColouration(double healthPercentage){
        if(healthPercentage >= 75) return "&a" + healthPercentage;
        else if(healthPercentage >= 30) return "&e" + healthPercentage;

        return "&c" + healthPercentage;
    }

    public static double getHealthForEntityLevel(double baseHealth, int level) {
        return baseHealth * ((level / 100) + 1);
    }

    public static void createEntityConfigSection(String id, String name, int level, HashMap<String, Number> stats) {
        ConfigurationFile config = ConfigurationDeclaration.ENTITIES.getFile();
        FileConfiguration confFile = config.getConfig();

        if(!(stats.containsKey("health"))) stats.put("health", 10);

        if(confFile.getConfigurationSection(id) == null) {
            confFile.set(id + ".name", name);
            confFile.set(id + ".level", level);

            for(String stat : stats.keySet()){
                confFile.set(id + ".stats." + stat, stats.get(stat));
            }
            config.save();
        }

    }

}
