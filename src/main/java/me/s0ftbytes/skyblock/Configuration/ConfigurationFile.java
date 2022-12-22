package me.s0ftbytes.skyblock.Configuration;

import me.s0ftbytes.skyblock.Skyblock;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigurationFile {

    private String fileName;
    private File file;
    private FileConfiguration config;
    private File dataFolder = new File(Skyblock.getSkyblockInstance().getDataFolder().getAbsolutePath() + "/configuration");

    public ConfigurationFile(String fileName){
        this.fileName = fileName + ".yml";

        if(!dataFolder.exists()) dataFolder.mkdirs();
    }

    public ConfigurationFile(String fileName, String folder){
        this.fileName = fileName + ".yml";

        dataFolder = new File(dataFolder.getAbsolutePath().substring(0, dataFolder.getAbsolutePath().lastIndexOf("/")) + "/" + folder);
        if(!dataFolder.exists()) dataFolder.mkdirs();
    }

    public void create(){
        file = new File(dataFolder, fileName);
            try {
                if(!file.exists()) {
                    file.createNewFile();
                    Bukkit.getLogger().info("Created configuration file " + fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void load(){
        if(file == null) create();
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig(){
        if(config == null) load();

        return config;
    }

    public void save(){
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
