package me.s0ftbytes.skyblock.Registries;

public class RegistryManager {

    private static RegistryManager instance;
    private PlayerRegistry playerRegistry;
    private StatRegistry statRegistry;
    private EntityRegistry entityRegistry;
    private SkillRegistry skillRegistry;

    public static RegistryManager getInstance(){
        if(instance == null) instance = new RegistryManager();
        return instance;
    }

    public void registerRegistries(){
        playerRegistry = PlayerRegistry.getInstance();
        statRegistry = StatRegistry.getInstance();
        entityRegistry = EntityRegistry.getInstance();
        skillRegistry = SkillRegistry.getInstance();

        statRegistry.registerStats();
        entityRegistry.registerEntities();
        skillRegistry.registerSkills();
        skillRegistry.getSkill("combat").maxLevel();
    }

    public PlayerRegistry getPlayerRegistry() {
        return playerRegistry;
    }
    public StatRegistry getStatRegistry() {
        return statRegistry;
    }

    public EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }

    public SkillRegistry getSkillRegistry() {
        return skillRegistry;
    }

}
