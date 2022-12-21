package me.s0ftbytes.skyblock.Skills;

import me.s0ftbytes.skyblock.SkyblockPlayer;

public interface Skill {
        String getID();
        String getName();
        int maxLevel();
        int getRequiredXP(int level);
        int getXPToNextLevel(SkyblockPlayer player);
        void registerHandlers();
}
