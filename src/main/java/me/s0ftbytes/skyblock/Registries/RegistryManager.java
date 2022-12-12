package me.s0ftbytes.skyblock.Registries;

public class RegistryManager {

    private static RegistryManager instance;
    private PlayerRegistry playerRegistry;

    public static RegistryManager getInstance(){
        if(instance == null) instance = new RegistryManager();
        return instance;
    }

    public void registerRegistries(){
        playerRegistry = PlayerRegistry.getInstance();
    }

    public PlayerRegistry getPlayerRegistry() {
        return playerRegistry;
    }

}
