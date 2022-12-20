package me.s0ftbytes.skyblock.Stats;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerAttackEvent;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerDamageByEntityEvent;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.StatUtils;

import java.util.concurrent.ThreadLocalRandom;

public class CriticalChanceStat implements Stat {

    public CriticalChanceStat(){
        registerStatHandlers();
    }

    @Override
    public String getID() {
        return "critical_chance";
    }

    @Override
    public String getName() {
        return "Critical Chance";
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
        return 0;
    }

    @Override
    public Number getMaxValue() {
        return 100;
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
                .filter(e -> e.getPlayer().getStat(getID()).doubleValue() > 0)
                .handler(e -> {
                    if (ThreadLocalRandom.current().nextDouble() <= e.getPlayer().getStat(getID()).doubleValue() / 100) {
                        e.setCritical(true);
                    }
                });

        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> !e.isCancelled())
                .filter(e -> e.getEntity().getStat(getID()).doubleValue() > 0)
                .handler(e -> {
                    if (ThreadLocalRandom.current().nextDouble() <= e.getEntity().getStat(getID()).doubleValue() / 100) {
                        e.setCritical(true);
                    }
                });
    }
}
