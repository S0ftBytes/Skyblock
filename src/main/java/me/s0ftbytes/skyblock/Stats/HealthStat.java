package me.s0ftbytes.skyblock.Stats;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.StatUtils;

public class HealthStat implements Stat {

    @Override
    public String getID() {
            return "health";
        }

    @Override
    public String getName() {
            return "Health";
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
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isHiddenStat() {
        return false;
    }

    @Override
    public void applyStat(SkyblockPlayer player, Number value) {
        if(value.doubleValue() < getMinValue().doubleValue()) value = getMinValue();
        if(value.doubleValue() > getMaxValue().doubleValue()) value = getMaxValue();

        player.getBukkitPlayer().setMaxHealth(value.doubleValue());
    }

    @Override
    public void applyStat(SkyblockEntity entity, Number value) {
        if(value.intValue() < getMinValue().intValue()) value = getMinValue();
        if(value.doubleValue() > getMaxValue().doubleValue()) value = getMaxValue();

        entity.getLivingEntity().setMaxHealth(value.doubleValue());
    }
}
