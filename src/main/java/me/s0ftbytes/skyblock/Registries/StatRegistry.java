package me.s0ftbytes.skyblock.Registries;

import me.s0ftbytes.skyblock.Stats.CriticalChanceStat;
import me.s0ftbytes.skyblock.Stats.HealthStat;
import me.s0ftbytes.skyblock.Stats.Stat;
import org.bukkit.Bukkit;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StatRegistry {

    private static StatRegistry instance;
    private HashMap<String, Stat> stats = new HashMap<>();

    public static StatRegistry getInstance(){
        if(instance == null) instance = new StatRegistry();
        return instance;
    }

    public void registerStats(){
        List<Stat> stats = new ArrayList<>();
        Collections.addAll(stats,
                new HealthStat(),
                new CriticalChanceStat()
        );

        for(Stat stat : stats){
            registerStat(stat);
        }
    }

    public void registerStat(Stat stat){
        stats.put(stat.getID(), stat);
    }

    public void unregisterStat(Stat stat){
        stats.remove(stat.getID());
    }

    public Stat getStat(String id){
        return stats.get(id);
    }

    public boolean isRegistered(String id){
        return stats.containsKey(id);
    }

    public HashMap<String, Stat> getStats(){
        return stats;
    }

    public HashMap<String, Number> getStatDefaults(){
        HashMap<String, Number> values = new HashMap<>();
        for(Stat stat : stats.values()){
            values.put(stat.getID(), stat.getDefaultValue());
        }
        return values;
    }
}
