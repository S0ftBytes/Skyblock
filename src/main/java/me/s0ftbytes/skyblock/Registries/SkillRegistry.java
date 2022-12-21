package me.s0ftbytes.skyblock.Registries;

import me.s0ftbytes.skyblock.Skills.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SkillRegistry {

    private static SkillRegistry instance;
    private HashMap<String, Skill> skills = new HashMap<>();

    public static SkillRegistry getInstance(){
        if(instance == null) instance = new SkillRegistry();
        return instance;
    }

    public void registerSkills(){
        List<Skill> skills = new ArrayList<>();
        Collections.addAll(skills,
                new CombatSkill()
        );

        for(Skill skill : skills){
            registerSkill(skill);
        }
    }

    public void registerSkill(Skill skill){
        skills.put(skill.getID(), skill);
    }

    public void unregisterSkill(Skill skill){
        skills.remove(skill.getID());
    }

    public Skill getSkill(String id){
        return skills.get(id);
    }

    public boolean isRegistered(String id){
        return skills.containsKey(id);
    }

    public HashMap<String, Skill> getSkills(){
        return skills;
    }
}
