package me.s0ftbytes.skyblock.Events.Handlers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerDamageByEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class HandleEntityStats {

    public HandleEntityStats(){
        handleCriticalChance();
        handleCriticalDamage();
    }

    public void handleCriticalChance(){
        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> e.isCancelled())
                .filter(e -> e.getEntity().getStat("critical_chance").doubleValue() > 0)
                .handler(e -> {
                    if (ThreadLocalRandom.current().nextDouble() <= e.getEntity().getStat("critical_chance").doubleValue() / 100) {
                        e.setCritical(true);
                    }
                });
    }

    public void handleCriticalDamage(){
        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> e.isCancelled())
                .filter(e -> e.isCritical())
                .handler(e -> {
                    e.setDamage(e.getDamage() * (e.getEntity().getStat("critical_damage").doubleValue() / 100));
                });
    }
}
