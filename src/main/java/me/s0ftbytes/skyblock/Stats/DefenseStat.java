package me.s0ftbytes.skyblock.Stats;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Events.EntityEvents.SkyblockEntityDamageEvent;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerDamageEvent;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.StatUtils;

public class DefenseStat implements Stat {

    public DefenseStat(){
        registerStatHandlers();
    }

    @Override
    public String getID() {
            return "defense";
        }

    @Override
    public String getName() {
            return "Defense";
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
        Events.subscribe(SkyblockPlayerDamageEvent.class)
                .filter(e -> !e.isCancelled())
                .handler(e -> {
                    int defense = e.getPlayer().getStat(getID()).intValue();
                    double health = e.getPlayer().getStat(getID()).doubleValue();

                    double rawDamage = e.getDamage();

                    double effectiveHealth = health * (1 + (defense / 100));
                    double effectiveDamage = (rawDamage / (effectiveHealth * 100)) * (health * 100);

                    e.setDamage(effectiveDamage);
                });

        Events.subscribe(SkyblockEntityDamageEvent.class)
                .filter(e -> !e.isCancelled())
                .handler(e -> {
                    int defense = e.getEntity().getStat(getID()).intValue();
                    double health = e.getEntity().getStat(getID()).doubleValue();

                    double rawDamage = e.getDamage();

                    double effectiveHealth = health * (1 + (defense / 100));
                    double effectiveDamage = (rawDamage / (effectiveHealth * 100)) * (health * 100);

                    e.setDamage(effectiveDamage);
                });
    }
}
