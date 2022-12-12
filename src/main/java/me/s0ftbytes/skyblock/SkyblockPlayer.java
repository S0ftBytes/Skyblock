package me.s0ftbytes.skyblock;

import me.s0ftbytes.skyblock.Utils.PlayerUtils;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SkyblockPlayer {
    private Player player;

    public SkyblockPlayer(Player player){
        this.player = player;
    }

    public Player getBukkitPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
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


}
