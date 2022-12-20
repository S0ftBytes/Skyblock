package me.s0ftbytes.skyblock.Stats;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerAttackEvent;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerDamageByEntityEvent;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.StatUtils;

public class CriticalDamageStat implements Stat {

    public CriticalDamageStat(){
        registerStatHandlers();
    }

    @Override
    public String getID() {
        return "critical_damage";
    }

    @Override
    public String getName() {
        return "Critical Damage";
    }

    @Override
    public String getDisplay(Number value) {
        return StatUtils.getStatDisplay(getID(), value);
    }

    @Override
    public Number getDefaultValue() {
        return StatUtils.getDefaultStatValue(getID());
    }

    @Override
    public Number getMinValue() {
        return Integer.MIN_VALUE;
    }

    @Override
    public Number getMaxValue() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isHiddenStat() {
        return false;
    }

    @Override
    public void applyStat(SkyblockPlayer player, Number value) {

    }

    @Override
    public void applyStat(SkyblockEntity entity, Number value) {

    }

    private void registerStatHandlers(){
        Events.subscribe(SkyblockPlayerAttackEvent.class)
                .filter(e -> !e.isCancelled())
                .filter(e -> e.isCritical())
                .handler(e -> {
                    e.setDamage(e.getDamage() * (e.getPlayer().getStat(getID()).doubleValue() / 100));
                });

        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> e.isCancelled())
                .filter(e -> e.isCritical())
                .handler(e -> {
                    e.setDamage(e.getDamage() * (1 + (e.getEntity().getStat(getID()).doubleValue() / 100)));
                });
    }
}
