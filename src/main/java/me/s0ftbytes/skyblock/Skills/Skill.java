package me.s0ftbytes.skyblock.Skills;

import me.s0ftbytes.skyblock.SkyblockPlayer;

public interface Skill {
        String getID();
        String getName();

        int getLevel(SkyblockPlayer player);
        String getBoostedStatID();
        Number getBoostedStatAmount(SkyblockPlayer player);
        String getNonPermanentBoostName();
        Number getNonPermanentBoostAmount(SkyblockPlayer player);
        int getMaxLevel();
        int getRequiredXP(int level);
        int getXPToNextLevel(SkyblockPlayer player);
        void addXP(SkyblockPlayer player, int xp);
        int getXP(SkyblockPlayer player);
        void registerHandlers();
}
