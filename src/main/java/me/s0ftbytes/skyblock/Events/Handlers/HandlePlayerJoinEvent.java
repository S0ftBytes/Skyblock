package me.s0ftbytes.skyblock.Events.Handlers;

import me.lucko.helper.Events;
import me.lucko.helper.Schedulers;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerJoinEvent;
import me.s0ftbytes.skyblock.Registries.EntityRegistry;
import me.s0ftbytes.skyblock.Registries.SkillRegistry;
import me.s0ftbytes.skyblock.Registries.StatRegistry;
import me.s0ftbytes.skyblock.Skills.Skill;

import java.util.HashMap;

public class HandlePlayerJoinEvent {

    public HandlePlayerJoinEvent(){
        previsionDefaultStats();
        applySkillStatBuffs();
    }

    public void previsionDefaultStats(){
        Events.subscribe(SkyblockPlayerJoinEvent.class)
                .handler(e -> {
                    HashMap<String, Number> defaults = StatRegistry.getInstance().getStatDefaults();
                    e.getPlayer().setStats(defaults);

                    Schedulers.sync().runLater(() -> {
                        EntityRegistry.getInstance().createEntityInstance("weak_zombie").spawn(e.getPlayer().getLocation());
                    }, 20L);

                });
    }

    public void applySkillStatBuffs(){
        Events.subscribe(SkyblockPlayerJoinEvent.class)
                .handler(e -> {
                    SkillRegistry.getInstance().getRegisteredSkillDeclarations().forEach(skillDecloration -> {
                        Skill skill = skillDecloration.getSkill();

                        e.getPlayer().increaseStat(skill.getBoostedStatID(), skill.getBoostedStatAmount(e.getPlayer()));
                    });
                });
    }
}
