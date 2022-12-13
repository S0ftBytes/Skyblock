package me.s0ftbytes.skyblock;

import me.s0ftbytes.skyblock.Utils.PlayerUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SkyblockPlayer {
    private Player player;
    private HashMap<String, Number> stats;

    public SkyblockPlayer(Player player){
        this.player = player;
        this.stats = new HashMap<>();
    }

    public Player getBukkitPlayer(){
        return player;
    }

    public String getName(){
        return player.getName();
    }

    public String getDisplayName(){
        return player.getDisplayName();
    }

    public UUID getUUID(){
        return player.getUniqueId();
    }

    public void setDisplayName(String displayName){
        player.setDisplayName(displayName);
    }

    public void sendMessage(String message){
        player.sendMessage(message);
    }

    public void sendTitle(String title, String subtitle){
        player.sendTitle(title, subtitle);
    }

    public void sendActionBar(String message){
        PlayerUtils.sendActionBar(this, message);
    }

    public void setStat(String statID, Number value){
        stats.put(statID, value);
    }

    public Number getStat(String statID){
        return stats.get(statID);
    }

    public void removeStat(String statID){
        stats.remove(statID);
    }

    public boolean hasStat(String statID){
        return stats.containsKey(statID);
    }

    public HashMap<String, Number> getStats(){
        return stats;
    }

}
