package me.s0ftbytes.skyblock;

import me.s0ftbytes.skyblock.Events.Firers.EntityEventFirers;
import me.s0ftbytes.skyblock.Events.Firers.PlayerEventFirers;
import me.s0ftbytes.skyblock.Events.Handlers.HandleEntityStats;
import me.s0ftbytes.skyblock.Events.Handlers.HandleGenericEvents;
import me.s0ftbytes.skyblock.Events.Handlers.HandlePlayerJoinEvent;
import me.s0ftbytes.skyblock.Events.Handlers.HandlePlayerStats;
import me.s0ftbytes.skyblock.Registries.RegistryManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyblock extends JavaPlugin {

    private static Skyblock instance;
    private RegistryManager registryManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        registryManager = RegistryManager.getInstance();
        registryManager.registerRegistries();

        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents(){
        //Fire events
        new PlayerEventFirers();
        new EntityEventFirers();

        //Handle Player events
        new HandlePlayerJoinEvent();
        new HandlePlayerStats();


        //Handle Entity events
        new HandleEntityStats();

        //Handle Misc
        new HandleGenericEvents();
    }

    public static Skyblock getSkyblockInstance(){
        return instance;
    }

    public RegistryManager getRegistryManager() {
        return registryManager;
    }
}
