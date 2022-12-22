package me.s0ftbytes.skyblock.Skills;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Entities.EntityDecloration;
import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Events.EntityEvents.SkyblockEntityKilledEvent;
import me.s0ftbytes.skyblock.Registries.EntityRegistry;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.SkillUtils;

public class CombatSkill implements Skill {

    public CombatSkill() {
        registerHandlers();
    }

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
        return SkillUtils.getRemainingXPToLevelUp(this, player);
    }

    @Override
    public void addXP(SkyblockPlayer player, int xp) {
        SkillUtils.addSkillXP(this, player, xp);
    }

    @Override
    public int getXP(SkyblockPlayer player) {
        return SkillUtils.getSkillXP(this, player);
    }

    @Override
    public void registerHandlers() {
        Events.subscribe(SkyblockEntityKilledEvent.class)
                .handler(e -> {
                    SkyblockPlayer player = e.getKiller();
                    SkyblockEntity entity = e.getEntity();

                    EntityDecloration entityDecloration = EntityRegistry.getInstance().getEntityDecloration(entity.getID());
                    int xp = entityDecloration.getKillXP();

                    this.addXP(player, xp);
                });
    }

}
