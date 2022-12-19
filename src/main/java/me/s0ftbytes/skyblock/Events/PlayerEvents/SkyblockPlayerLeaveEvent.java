package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.player.PlayerQuitEvent;

public class SkyblockPlayerLeaveEvent extends SkyblockPlayerEvent {

    private PlayerQuitEvent bukkitEvent;
    public SkyblockPlayerLeaveEvent(SkyblockPlayer player, PlayerQuitEvent bukkitEvent) {
        super(player);

        this.bukkitEvent = bukkitEvent;
    }

    public void setLeaveMessage(String message){
        bukkitEvent.setQuitMessage(message);
    }

    public PlayerQuitEvent getBukkitEvent(){
        return bukkitEvent;
    }

}
