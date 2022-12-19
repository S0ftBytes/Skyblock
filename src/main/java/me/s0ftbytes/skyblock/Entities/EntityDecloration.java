package me.s0ftbytes.skyblock.Entities;

import me.s0ftbytes.skyblock.Configuration.ConfigurationDeclaration;
import me.s0ftbytes.skyblock.Configuration.ConfigurationFile;
import me.s0ftbytes.skyblock.Entities.Monsters.Zombie;
import me.s0ftbytes.skyblock.Utils.EntityUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public enum EntityDecloration {
    WEAK_ZOMBIE(Zombie.class, "weak_zombie", "Zombie");

    final Class<SkyblockEntity> entityClass;
    final String id;
    final String name;
    final int level;
    final HashMap<String, Number> stats;
    EntityDecloration(Class <? extends SkyblockEntity> entityClass, String id, String name, int level, HashMap<String, Number> stats) {
        this.entityClass = (Class<SkyblockEntity>) entityClass;
        this.id = id;
        this.name = name;
        this.level = level;
        this.stats = stats;

        EntityUtils.createEntityConfigSection(id, name, level, stats);
    }

    EntityDecloration(Class <? extends SkyblockEntity> entityClass, String id, String name){
        this.entityClass = (Class<SkyblockEntity>) entityClass;
        this.id = id;
        this.name = name;

        EntityUtils.createEntityConfigSection(id, name, 1, new HashMap<>());
        ConfigurationFile config = ConfigurationDeclaration.getEntitiesConfiguration();

        FileConfiguration confFile = config.getConfig();

        HashMap<String, Number> stats = new HashMap<>();
        for(String stat : confFile.getConfigurationSection(id + ".stats").getKeys(false)){
            stats.put(stat, confFile.getDouble(id + ".stats." + stat));
        }

        this.level = confFile.getInt(id + ".level");
        this.stats = stats;
    }

    public SkyblockEntity createEntityInstance() {
        SkyblockEntity entity;
        try {
            entity = entityClass.getConstructor(String.class, String.class, int.class, HashMap.class).newInstance(name, name, level, stats);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    public HashMap<String, Number> getStats(){
        return stats;
    }

    public Class<SkyblockEntity> getEntityClass(){
        return entityClass;
    }

}
