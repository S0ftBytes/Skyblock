package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.Skills.Skill;
import me.s0ftbytes.skyblock.SkyblockPlayer;

public class SkyblockPlayerSkillLevelUpEvent extends SkyblockPlayerEvent {
    private Skill skill;
    private int level;

    public SkyblockPlayerSkillLevelUpEvent(SkyblockPlayer player, Skill skill, int level) {
        super(player);

        this.skill = skill;
        this.level = level;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }


}
