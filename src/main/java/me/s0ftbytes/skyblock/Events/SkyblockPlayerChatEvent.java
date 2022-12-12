package me.s0ftbytes.skyblock.Events;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SkyblockPlayerChatEvent extends SkyblockPlayerEvent {

    private String message;
    private AsyncPlayerChatEvent bukkitEvent;

    public SkyblockPlayerChatEvent(SkyblockPlayer player, AsyncPlayerChatEvent bukkitEvent, String message) {
        super(player);
        this.message = message;
        this.bukkitEvent = bukkitEvent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AsyncPlayerChatEvent getBukkitEvent() {
        return bukkitEvent;
    }
}
