package me.s0ftbytes.skyblock.Events.Handlers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerAttackEvent;

public class HandlePlayerStats {

    public HandlePlayerStats(){
        handleBaseDamageStrength();
    }

    public void handleBaseDamageStrength(){
        Events.subscribe(SkyblockPlayerAttackEvent.class)
                .filter(e -> !e.isCancelled())
                .filter(e -> e.getPlayer().getStat("damage").intValue() > 0 || e.getPlayer().getStat("strength").intValue() > 0)
                .handler(e -> {

                    int damageStat = e.getPlayer().getStat("damage").intValue();
                    int strengthStat = e.getPlayer().getStat("strength").intValue();

                    double damage = e.getDamage() * (1 + ((5 + damageStat) * (strengthStat / 100)));

                    e.setDamage(damage);
                });
    }
}
