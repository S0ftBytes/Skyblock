package me.s0ftbytes.skyblock.Stats;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.SkyblockPlayer;

public interface Stat {
        String getID();
        String getName();
        String getDisplay(Number value);
        Number getDefaultValue();
        void applyStat(SkyblockPlayer player, Number value);
        void applyStat(SkyblockEntity entity, Number value);
}
