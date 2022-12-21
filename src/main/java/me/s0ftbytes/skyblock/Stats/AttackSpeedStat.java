package me.s0ftbytes.skyblock.Stats;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerAttackEvent;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerDamageByEntityEvent;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.StatUtils;

import java.util.concurrent.ThreadLocalRandom;

public class AttackSpeedStat implements Stat {

    public AttackSpeedStat(){
        registerStatHandlers();
    }

    @Override
    public String getID() {
        return "attack_speed";
    }

    @Override
    public String getName() {
        return "Attack Speed";
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
        return 1;
    }

    @Override
    public Number getMaxValue() {
        return 200;
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
                .handler(e -> {
                    int noDamageTicks = calculateNoDamageTicks(e.getPlayer().getStat(getID()).doubleValue());

                    e.getEntity().getLivingEntity().setMaximumNoDamageTicks(noDamageTicks);
                    e.getEntity().getLivingEntity().setNoDamageTicks(noDamageTicks);
                });

        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> !e.isCancelled())
                .handler(e -> {
                    int noDamageTicks = calculateNoDamageTicks(e.getEntity().getStat(getID()).doubleValue());

                    e.getPlayer().getBukkitPlayer().setMaximumNoDamageTicks(noDamageTicks);
                    e.getPlayer().getBukkitPlayer().setNoDamageTicks(noDamageTicks);
                });
    }

    private int calculateNoDamageTicks(Number attackSpeed){
        int defaultSpeed = 9;

        int noDamageTicks;
        if(attackSpeed.doubleValue() < 100){
            noDamageTicks = defaultSpeed + (int) (defaultSpeed * (100 - attackSpeed.doubleValue()) / 100);
        } else {
            noDamageTicks = defaultSpeed - (int) (defaultSpeed * (attackSpeed.doubleValue() - 100) / 100);
        }

        if(noDamageTicks < 1) noDamageTicks = 1;


        return noDamageTicks;
    }
}
