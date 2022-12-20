package me.s0ftbytes.skyblock.Events.Handlers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerDamageByEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class HandleEntityStats {

    public HandleEntityStats(){
        handleCriticalChance();
        handleCriticalDamage();
        handleBaseDamageStrength();
    }

    public void handleCriticalChance(){
        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> !e.isCancelled())
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
                    e.setDamage(e.getDamage() * (1 + (e.getEntity().getStat("critical_damage").doubleValue() / 100)));
                });
    }

    public void handleBaseDamageStrength(){
        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> e.isCancelled())
                .filter(e -> e.getEntity().getStat("damage").intValue() > 0 || e.getEntity().getStat("strength").intValue() > 0)
                .handler(e -> {

                    int damageStat = e.getEntity().getStat("damage").intValue();
                    int strengthStat = e.getEntity().getStat("strength").intValue();

                    double damage = e.getDamage() * (1 + ((5 + damageStat) * (strengthStat / 100)));

                    e.setDamage(damage);
                });
    }
}
