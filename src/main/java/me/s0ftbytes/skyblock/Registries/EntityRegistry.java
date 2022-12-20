package me.s0ftbytes.skyblock.Registries;

import me.s0ftbytes.skyblock.Entities.EntityDecloration;
import me.s0ftbytes.skyblock.Entities.SkyblockEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityRegistry {

    private HashMap<String, EntityDecloration > registeredEntities = new HashMap<>();
    private HashMap<Integer, SkyblockEntity> spawnedEntities = new HashMap<>();
    private static EntityRegistry instance;

    public static EntityRegistry getInstance(){
        if(instance == null)
            instance = new EntityRegistry();
        return instance;
    }

    public void registerEntities() {
        registerEntity(EntityDecloration.WEAK_ZOMBIE);
    }

    public void registerEntity(EntityDecloration entity){
        registeredEntities.put(entity.getId(), entity);
    }

    public void unregisterEntity(SkyblockEntity entity){
        registeredEntities.remove(entity.getID());
    }

    public SkyblockEntity createEntityInstance(String id) {
        return registeredEntities.get(id).createEntityInstance();
    }

    public SkyblockEntity getEntity(int id) {
        return spawnedEntities.get(id);
    }

    public List<String> getRegisteredEntities() {
        return new ArrayList<>(registeredEntities.keySet());
    }

    public void addEntity(SkyblockEntity entity) {
        spawnedEntities.put(entity.getLivingEntity().getEntityId(), entity);
    }

    public void removeEntity(int entityID) {
        SkyblockEntity instance = getEntity(entityID);
        if(instance != null) {
            instance.despawn();
            spawnedEntities.remove(entityID);
        }
    }


}
