package me.s0ftbytes.skyblock.Events.Handlers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerDamageByEntityEvent;

public class HandleEntityStats {

    public HandleEntityStats(){
        handleBaseDamageStrength();
    }

    public void handleBaseDamageStrength(){
        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> !e.isCancelled())
                .filter(e -> e.getEntity().getStat("damage").intValue() > 0 || e.getEntity().getStat("strength").intValue() > 0)
                .handler(e -> {

                    int damageStat = e.getEntity().getStat("damage").intValue();
                    int strengthStat = e.getEntity().getStat("strength").intValue();

                    double damage = e.getDamage() * (1 + ((5 + damageStat) * (strengthStat / 100)));

                    e.setDamage(damage);
                });
    }
}
