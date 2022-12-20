package me.s0ftbytes.skyblock.Stats;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.StatUtils;

public class StrengthStat implements Stat {
    @Override
    public String getID() {
        return "strength";
    }

    @Override
    public String getName() {
        return "Strength";
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
}
