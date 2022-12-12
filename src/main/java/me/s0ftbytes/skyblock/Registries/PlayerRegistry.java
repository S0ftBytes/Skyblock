package me.s0ftbytes.skyblock.Registries;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerRegistry {
    private static PlayerRegistry instance;
    private HashMap<UUID, SkyblockPlayer> players = new HashMap<>();

    public static PlayerRegistry getInstance(){
        if(instance == null) instance = new PlayerRegistry();
        return instance;
    }

    public SkyblockPlayer createSkyblockPlayer(Player player) {
        SkyblockPlayer skyblockPlayer = new SkyblockPlayer(player);
        registerPlayer(skyblockPlayer);

        return skyblockPlayer;
    }

    public void registerPlayer(SkyblockPlayer player){
        players.put(player.getUUID(), player);
    }

    public void unregisterPlayer(SkyblockPlayer player){
        players.remove(player.getUUID());
    }

    public SkyblockPlayer getPlayer(UUID uuid){
        return players.get(uuid);
    }

    public boolean isRegistered(UUID uuid){
        return players.containsKey(uuid);
    }

    public HashMap<UUID, SkyblockPlayer> getPlayers(){
        return players;
    }
}
