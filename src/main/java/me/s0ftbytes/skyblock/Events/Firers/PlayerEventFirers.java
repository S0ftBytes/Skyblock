package me.s0ftbytes.skyblock.Events.Firers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.Events.PlayerEvents.*;
import me.s0ftbytes.skyblock.Registries.EntityRegistry;
import me.s0ftbytes.skyblock.Registries.PlayerRegistry;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventFirers implements Listener {

    private PlayerRegistry playerRegistry;

    public PlayerEventFirers(){
        playerRegistry = PlayerRegistry.getInstance();

        registerPlayerJoinFirer();
        registerPlayerLeaveFirer();
        registerPlayerChatFirer();
        registerPlayerDeathFirer();
        registerPlayerDamageFirer();
    }

    public void registerPlayerJoinFirer(){
        Events.subscribe(PlayerJoinEvent.class)
                .handler(e -> {
                    Player player = e.getPlayer();

                    SkyblockPlayer skyblockPlayer = playerRegistry.createSkyblockPlayer(player);
                    SkyblockPlayerJoinEvent sbPlayerJoinEvt = new SkyblockPlayerJoinEvent(skyblockPlayer, e);

                    sbPlayerJoinEvt.call();
                });
    }

    public void registerPlayerLeaveFirer(){
        Events.subscribe(PlayerQuitEvent.class)
                .handler(e -> {
                    Player player = e.getPlayer();

                    SkyblockPlayer skyblockPlayer = playerRegistry.getPlayer(player.getUniqueId());
                    SkyblockPlayerLeaveEvent sbPlayerLeaveEvt = new SkyblockPlayerLeaveEvent(skyblockPlayer, e);

                    sbPlayerLeaveEvt.call();
                });
    }

    public void registerPlayerChatFirer(){
        Events.subscribe(AsyncPlayerChatEvent.class)
                .handler(e -> {
                    Player player = e.getPlayer();

                    if(!e.isCancelled()){
                        SkyblockPlayer skyblockPlayer = playerRegistry.getPlayer(player.getUniqueId());
                        SkyblockPlayerChatEvent sbPlayerChatEvt = new SkyblockPlayerChatEvent(skyblockPlayer, e, e.getMessage());

                        sbPlayerChatEvt.call();
                    }
                });
    }

    public void registerPlayerDamageFirer(){
        Events.subscribe(EntityDamageEvent.class)
                .filter(e -> e.getEntity() instanceof Player)
                .handler(e -> {
                    Player player = (Player) e.getEntity();

                    SkyblockPlayer skyblockPlayer = playerRegistry.getPlayer(player.getUniqueId());
                    SkyblockPlayerDamageEvent sbPlayerDamageEvt = new SkyblockPlayerDamageEvent(skyblockPlayer, e.getDamage(), e.getFinalDamage(), DamageCause.getDamageCause(e.getCause()), e);

                    sbPlayerDamageEvt.call();
                });

        Events.subscribe(EntityDamageByEntityEvent.class)
                .filter(e -> e.getEntity() instanceof Player)
                .handler(e -> {
                    Player player = (Player) e.getEntity();

                    SkyblockPlayer skyblockPlayer = playerRegistry.getPlayer(player.getUniqueId());

                    SkyblockPlayerDamageByEntityEvent sbPlayerDamageByEntityEvt = new SkyblockPlayerDamageByEntityEvent(skyblockPlayer, EntityRegistry.getInstance().getEntity(e.getEntity().getEntityId()), e.getDamage(), e.getFinalDamage(), DamageCause.getDamageCause(e.getCause()), e);
                    SkyblockPlayerDamageEvent sbPlayerDamageEvt = new SkyblockPlayerDamageEvent(skyblockPlayer, e.getDamage(), e.getFinalDamage(), DamageCause.getDamageCause(e.getCause()), e);

                    sbPlayerDamageByEntityEvt.call();
                    sbPlayerDamageEvt.call();
                });
    }

    public void registerPlayerDeathFirer(){
        Events.subscribe(SkyblockPlayerDamageEvent.class)
                .handler(e -> {
                    SkyblockPlayer player = e.getPlayer();

                    if(e.getNewHealth() < 0.5){

                        e.setCancelled(true);
                        SkyblockPlayerDeathEvent deathEvent = new SkyblockPlayerDeathEvent(player, player.location());
                        deathEvent.call();
                    }

                });

        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .handler(e -> {
                    SkyblockPlayer player = e.getPlayer();

                    if(e.getNewHealth() < 0.5){
                        e.setCancelled(true);

                        SkyblockPlayerDeathEvent deathEvent = new SkyblockPlayerDeathEvent(player, player.location());
                        deathEvent.call();
                    }

                });

    }


}
