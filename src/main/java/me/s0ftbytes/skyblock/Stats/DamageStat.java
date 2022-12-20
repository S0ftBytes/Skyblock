package me.s0ftbytes.skyblock.Stats;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.StatUtils;

public class DamageStat implements Stat {
    @Override
    public String getID() {
        return "damage";
    }

    @Override
    public String getName() {
        return "Damage";
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
    public boolean isHiddenStat() {
        return true;
    }

    @Override
    public void applyStat(SkyblockPlayer player, Number value) {

    }

    @Override
    public void applyStat(SkyblockEntity entity, Number value) {

    }
}
