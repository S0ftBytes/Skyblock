package me.s0ftbytes.skyblock.Events;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SkyblockPlayerLeaveEvent extends SkyblockPlayerEvent {

    private PlayerQuitEvent bukkitEvent;
    private SkyblockPlayer skyblockPlayer;
    public SkyblockPlayerLeaveEvent(SkyblockPlayer player, PlayerQuitEvent bukkitEvent) {
        super(player);

        this.skyblockPlayer = player;
        this.bukkitEvent = bukkitEvent;
    }

    public void setLeaveMessage(String message){
        bukkitEvent.setQuitMessage(message);
    }

    public PlayerQuitEvent getBukkitEvent(){
        return bukkitEvent;
    }

    public SkyblockPlayer getSkyblockPlayer(){
        return skyblockPlayer;
    }

}
