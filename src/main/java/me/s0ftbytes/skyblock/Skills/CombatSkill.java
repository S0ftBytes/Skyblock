package me.s0ftbytes.skyblock.Skills;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.SkillUtils;

public class CombatSkill implements Skill {

    @Override
    public String getID() {
        return "combat";
    }

    @Override
    public String getName() {
        return "Combat";
    }

    @Override
    public int maxLevel() {
        return SkillUtils.getSkillMaxLevel(this);
    }

    @Override
    public int getRequiredXP(int level) {
        return SkillUtils.getSkillRequiredXP(this, level);
    }

    @Override
    public int getXPToNextLevel(SkyblockPlayer player) {
        return 0;
    }

    @Override
    public void registerHandlers() {

    }

}
