package me.s0ftbytes.playerstats.Stats;

import me.s0ftbytes.core.SkyblockPlayer;

public interface Stat {
        String getID();
        String getName();
        String getDisplay(Number value);
        void applyStat(SkyblockPlayer player, Number value);
}
