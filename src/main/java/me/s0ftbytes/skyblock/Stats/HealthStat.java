package me.s0ftbytes.skyblock.Stats;

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
        public void applyStat(SkyblockPlayer player, Number value) {
            if(value.intValue() <= 0) value = 1;

            player.getBukkitPlayer().setMaxHealth(value.doubleValue());
        }
}
