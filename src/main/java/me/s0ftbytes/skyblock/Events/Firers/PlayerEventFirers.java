package me.s0ftbytes.skyblock.Events.Firers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Events.SkyblockPlayerChatEvent;
import me.s0ftbytes.skyblock.Events.SkyblockPlayerJoinEvent;
import me.s0ftbytes.skyblock.Events.SkyblockPlayerLeaveEvent;
import me.s0ftbytes.skyblock.Registries.PlayerRegistry;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventFirers implements Listener {

    public PlayerEventFirers(){
        registerPlayerJoinFirer();
        registerPlayerLeaveFirer();
        registerPlayerChatFirer();
    }

    public void registerPlayerJoinFirer(){
        Events.subscribe(PlayerJoinEvent.class)
                .handler(e -> {
                    Player player = e.getPlayer();

                    SkyblockPlayer skyblockPlayer = PlayerRegistry.getInstance().createSkyblockPlayer(player);
                    SkyblockPlayerJoinEvent sbPlayerJoinEvt = new SkyblockPlayerJoinEvent(skyblockPlayer, e);

                    Bukkit.getPluginManager().callEvent(sbPlayerJoinEvt);
                });

    }

    public void registerPlayerLeaveFirer(){
        Events.subscribe(PlayerQuitEvent.class)
                .handler(e -> {
                    Player player = e.getPlayer();

                    SkyblockPlayer skyblockPlayer = PlayerRegistry.getInstance().getPlayer(player.getUniqueId());
                    SkyblockPlayerLeaveEvent sbPlayerLeaveEvt = new SkyblockPlayerLeaveEvent(skyblockPlayer, e);

                    Bukkit.getPluginManager().callEvent(sbPlayerLeaveEvt);
                });
    }

    public void registerPlayerChatFirer(){
        Events.subscribe(AsyncPlayerChatEvent.class)
                .handler(e -> {
                    Player player = e.getPlayer();

                    SkyblockPlayer skyblockPlayer = PlayerRegistry.getInstance().getPlayer(player.getUniqueId());
                    SkyblockPlayerChatEvent sbPlayerChatEvt = new SkyblockPlayerChatEvent(skyblockPlayer, e, e.getMessage());

                    Bukkit.getPluginManager().callEvent(sbPlayerChatEvt);
                });
    }
}
