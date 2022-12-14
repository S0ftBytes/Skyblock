package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.player.PlayerJoinEvent;

public class SkyblockPlayerJoinEvent extends SkyblockPlayerEvent {

    private boolean firstJoin;
    private PlayerJoinEvent bukkitEvent;
    private SkyblockPlayer skyblockPlayer;
    public SkyblockPlayerJoinEvent(SkyblockPlayer player, PlayerJoinEvent bukkitEvent) {
        super(player);

        this.firstJoin = player.getBukkitPlayer().hasPlayedBefore();
        this.skyblockPlayer = player;
        this.bukkitEvent = bukkitEvent;
    }

    public boolean isFirstJoin(){
        return firstJoin;
    }

    public void setJoinMessage(String message){
        bukkitEvent.setJoinMessage(message);
    }

    public PlayerJoinEvent getBukkitEvent(){
        return bukkitEvent;
    }

}
