package me.s0ftbytes.skyblock.Events.Handlers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerJoinEvent;
import me.s0ftbytes.skyblock.Registries.StatRegistry;

import java.util.HashMap;

public class HandlePlayerJoinEvent {

    public HandlePlayerJoinEvent(){
        previsionDefaultStats();
    }

    public void previsionDefaultStats(){
        Events.subscribe(SkyblockPlayerJoinEvent.class)
                .handler(e -> {
                    HashMap<String, Number> defaults =  StatRegistry.getInstance().getStatDefaults();
                    e.getPlayer().setStats(defaults);

                    System.out.println("SkyblockPlayerJoinEvent fired for " + e.getPlayer().getStats());
                });
    }
}
