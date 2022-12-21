package me.s0ftbytes.skyblock.Skills;

public interface Skill {
        String getID();
        String getName();
        int maxLevel();
        int getRequiredXP(int level);
        void registerHandlers();
}
