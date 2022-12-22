package me.s0ftbytes.skyblock.Skills;

public enum SkillDecloration {
    COMBAT(new CombatSkill());

    private Skill skill;
    SkillDecloration(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    public String getID() {
        return skill.getID();
    }

    public String getName() {
        return skill.getName();
    }

    public int maxLevel() {
        return skill.maxLevel();
    }


}
