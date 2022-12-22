package me.s0ftbytes.skyblock.Utils;

import me.s0ftbytes.skyblock.Configuration.ConfigurationDeclaration;
import me.s0ftbytes.skyblock.Configuration.ConfigurationFile;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerSkillLevelUpEvent;
import me.s0ftbytes.skyblock.Skills.Skill;
import me.s0ftbytes.skyblock.SkyblockPlayer;
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
                config.set(skill.getID() + ".levels." + (i + 1) + ".boost", 0);
                config.set(skill.getID() + ".levels." + (i + 1) + ".stat-boost", 0);
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

    public static void addSkillXP(Skill skill, SkyblockPlayer player, int xp) {
        if(xp <= 0) return;

        FileConfiguration playerConfig = player.getDataFile().getConfig();

        int currentLevel = playerConfig.getInt("skills." + skill.getID() + ".level");
        int maxLevel = skill.getMaxLevel();

        if(currentLevel >= maxLevel) return;

        int currentXP = playerConfig.getInt("skills." + skill.getID() + ".xp");
        int requiredXP = getSkillRequiredXP(skill, currentLevel + 1);

        int newXP = currentXP + xp;
        if(newXP >= requiredXP) {
            levelUpSkill(skill, player, newXP - requiredXP);
        } else {
            playerConfig.set("skills." + skill.getID() + ".xp", newXP);
            player.getDataFile().save();
        }

    }

    public static int getSkillXP(Skill skill, SkyblockPlayer player){
        FileConfiguration playerConfig = player.getDataFile().getConfig();

        return playerConfig.getInt("skills." + skill.getID() + ".xp");
    }

    public static int getSkillLevel(Skill skill, SkyblockPlayer player){
        FileConfiguration playerConfig = player.getDataFile().getConfig();

        return playerConfig.getInt("skills." + skill.getID() + ".level");
    }

    public static void levelUpSkill(Skill skill, SkyblockPlayer player, int remainingXP) {
        FileConfiguration playerConfig = player.getDataFile().getConfig();

        int currentLevel = getSkillLevel(skill, player);
        int maxLevel = skill.getMaxLevel();

        if(currentLevel >= maxLevel) return;

        playerConfig.set("skills." + skill.getID() + ".level", currentLevel + 1);
        if(remainingXP > 0) addSkillXP(skill, player, remainingXP);

        SkyblockPlayerSkillLevelUpEvent levelUpEvent = new SkyblockPlayerSkillLevelUpEvent(player, skill, currentLevel + 1);
        levelUpEvent.call();

        player.getDataFile().save();
    }

    public static int getRemainingXPToLevelUp(Skill skill, SkyblockPlayer player) {
        int currentLevel = getSkillLevel(skill, player);
        int maxLevel = skill.getMaxLevel();

        if(currentLevel >= maxLevel) return 0;

        int currentXP = getSkillXP(skill, player);
        int requiredXP = getSkillRequiredXP(skill, currentLevel + 1);

        return requiredXP - currentXP;
    }

    public static double getBoostForLevel(Skill skill, int level) {
        ConfigurationFile skillsConfig = ConfigurationDeclaration.SKILLS.getFile();
        FileConfiguration config = skillsConfig.getConfig();

        createInitialSkillConfigData(skill);
        return config.getDouble(skill.getID() + ".levels." + level + ".boost");
    }


    public static double getStatBoostForLevel(Skill skill, int level) {
        ConfigurationFile skillsConfig = ConfigurationDeclaration.SKILLS.getFile();
        FileConfiguration config = skillsConfig.getConfig();

        createInitialSkillConfigData(skill);
        return config.getDouble(skill.getID() + ".levels." + level + ".stat-boost");
    }

}
