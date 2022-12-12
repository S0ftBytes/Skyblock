package me.s0ftbytes.skyblock;

import me.s0ftbytes.skyblock.Events.Firers.PlayerEventFirers;
import me.s0ftbytes.skyblock.Registries.RegistryManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyblock extends JavaPlugin {

    private static Skyblock instance;
    private RegistryManager registryManager;

    @Override
    public void onEnable() {
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
        new PlayerEventFirers();
    }

    public static Skyblock getSkyblockInstance(){
        return instance;
    }

    public RegistryManager getRegistryManager() {
        return registryManager;
    }
}
