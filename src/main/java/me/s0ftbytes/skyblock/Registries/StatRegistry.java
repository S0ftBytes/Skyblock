package me.s0ftbytes.playerstats.Registries;

import me.s0ftbytes.playerstats.Stats.Stat;

import java.io.File;
import java.util.HashMap;

public class StatRegistry {

    private static StatRegistry instance;
    private HashMap<String, Stat> stats = new HashMap<>();

    public static StatRegistry getInstance(){
        if(instance == null) instance = new StatRegistry();
        return instance;
    }

    public void registerStats(){
        //Get all the files that are in the Stats folder and if they are a class that implements Stat, register them into the map
        File statsFolder = new File("../Stats");

        try{
            for(File file : statsFolder.listFiles()){
                String className = file.getName().replace(".java", "");

                Class<?> clazz = Class.forName("me.s0ftbytes.playerstats.Stats." + className);

                if(Stat.class.isAssignableFrom(clazz)){
                    Stat stat = (Stat) clazz.newInstance();
                    stats.put(stat.getID(), stat);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
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
}
