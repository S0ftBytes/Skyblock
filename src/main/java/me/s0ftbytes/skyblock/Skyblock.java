package me.s0ftbytes.skyblock;

import me.s0ftbytes.skyblock.Configuration.ConfigurationDeclaration;
import me.s0ftbytes.skyblock.Events.Firers.PlayerEventFirers;
import me.s0ftbytes.skyblock.Events.Handlers.HandlePlayerJoinEvent;
import me.s0ftbytes.skyblock.Registries.RegistryManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyblock extends JavaPlugin {

    private static Skyblock instance;
    private RegistryManager registryManager;
    private ConfigurationDeclaration configurationDeclaration;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        registryManager = RegistryManager.getInstance();
        registryManager.registerRegistries();

        configurationDeclaration = new ConfigurationDeclaration(this);

        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents(){
        //Fire events
        new PlayerEventFirers();

        //Handle events
        new HandlePlayerJoinEvent();
    }

    public static Skyblock getSkyblockInstance(){
        return instance;
    }

    public RegistryManager getRegistryManager() {
        return registryManager;
    }
}
