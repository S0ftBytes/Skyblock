package me.s0ftbytes.skyblock.Utils;

import me.s0ftbytes.skyblock.Configuration.ConfigurationDeclaration;
import me.s0ftbytes.skyblock.Configuration.ConfigurationFile;
import me.s0ftbytes.skyblock.Skills.Skill;
import org.bukkit.configuration.file.FileConfiguration;

public class SkillUtils {

    public static void createInitialSkillConfigData(Skill skill) {
        int defaultMaxLevel = 50;

        ConfigurationFile skillsConfig = ConfigurationDeclaration.SKILLS.getFile();

        FileConfiguration config = skillsConfig.getConfig();
        if(config.get(skill.getID()) == null) {
            config.set(skill.getID() + ".name", skill.getName());
            config.set(skill.getID() + ".max-level", defaultMaxLevel);

            for (int i = 0; i < defaultMaxLevel; i++) {
                config.set(skill.getID() + ".levels." + (i + 1) + ".required-xp", 0);
            }

            skillsConfig.save();
        }

    }

    public static int getSkillMaxLevel(Skill skill) {
        ConfigurationFile skillsConfig = ConfigurationDeclaration.SKILLS.getFile();
        FileConfiguration config = skillsConfig.getConfig();

        createInitialSkillConfigData(skill);
        return config.getInt(skill.getID() + ".max-level");
    }

    public static int getSkillRequiredXP(Skill skill, int level) {
        ConfigurationFile skillsConfig = ConfigurationDeclaration.SKILLS.getFile();
        FileConfiguration config = skillsConfig.getConfig();

        createInitialSkillConfigData(skill);
        return config.getInt(skill.getID() + ".levels." + level + ".required-xp");
    }
}
