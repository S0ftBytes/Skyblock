package me.s0ftbytes.skyblock.Registries;

import me.s0ftbytes.skyblock.Skills.CombatSkill;
import me.s0ftbytes.skyblock.Skills.Skill;
import me.s0ftbytes.skyblock.Skills.SkillDecloration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SkillRegistry {

    private HashMap<String, SkillDecloration> registeredSkills = new HashMap<>();
    private static SkillRegistry instance;

    public static SkillRegistry getInstance(){
        if(instance == null)
            instance = new SkillRegistry();
        return instance;
    }

    public void registerSkills() {
        List<SkillDecloration> skills = new ArrayList<>();
        Collections.addAll(skills,
                SkillDecloration.COMBAT
        );

        for(SkillDecloration skill : skills){
            registerSkill(skill);
        }
    }

    public void registerSkill(SkillDecloration skill){
        registeredSkills.put(skill.getID(), skill);
    }

    public void unregisterEntity(Skill skill){
        registeredSkills.remove(skill.getID());
    }

    public List<String> getRegisteredSkills() {
        return new ArrayList<>(registeredSkills.keySet());
    }

    public SkillDecloration getSkillDecloration(String id) {
        return registeredSkills.get(id);
    }


}
