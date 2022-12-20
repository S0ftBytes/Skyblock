package me.s0ftbytes.skyblock.Events.Handlers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerAttackEvent;

import java.util.concurrent.ThreadLocalRandom;

public class HandlePlayerStats {

    public HandlePlayerStats(){
        handleCriticalChance();
        handleCriticalDamage();
        handleBaseDamageStrength();
    }

    public void handleCriticalChance(){
        Events.subscribe(SkyblockPlayerAttackEvent.class)
                .filter(e -> e.isCancelled())
                .filter(e -> e.getPlayer().getStat("critical_chance").doubleValue() > 0)
                .handler(e -> {
                    if (ThreadLocalRandom.current().nextDouble() <= e.getPlayer().getStat("critical_chance").doubleValue() / 100) {
                        e.setCritical(true);
                    }
                });
    }

    public void handleCriticalDamage(){
        Events.subscribe(SkyblockPlayerAttackEvent.class)
                .filter(e -> e.isCancelled())
                .filter(e -> e.isCritical())
                .handler(e -> {
                    e.setDamage(e.getDamage() * (e.getPlayer().getStat("critical_damage").doubleValue() / 100));
                });
    }

    public void handleBaseDamageStrength(){
        Events.subscribe(SkyblockPlayerAttackEvent.class)
                .filter(e -> e.isCancelled())
                .filter(e -> e.getPlayer().getStat("damage").intValue() > 0 || e.getPlayer().getStat("strength").intValue() > 0)
                .handler(e -> {

                    int damageStat = e.getPlayer().getStat("damage").intValue();
                    int strengthStat = e.getPlayer().getStat("strength").intValue();

                    double damage = e.getDamage() * (1 + ((5 + damageStat) * (strengthStat / 100)));

                    e.setDamage(damage);
                });
    }
}
