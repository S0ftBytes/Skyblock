package me.s0ftbytes.skyblock.Entities;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Events.EntityEvents.SkyblockEntityDamageByPlayerEvent;
import me.s0ftbytes.skyblock.Events.EntityEvents.SkyblockEntityDamageEvent;
import me.s0ftbytes.skyblock.Events.EntityEvents.SkyblockEntityStatUpdateEvent;
import me.s0ftbytes.skyblock.Registries.EntityRegistry;
import me.s0ftbytes.skyblock.Registries.StatRegistry;
import me.s0ftbytes.skyblock.Skyblock;
import me.s0ftbytes.skyblock.Stats.Stat;
import me.s0ftbytes.skyblock.Utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import java.util.HashMap;

public abstract class SkyblockEntity {

    private Class<LivingEntity> entityClass;
    private LivingEntity entity;
    private String id;
    private String name;
    private int level;
    private boolean spawned;

    private HashMap<String, Number> stats;
    public SkyblockEntity(String id, String name, int level, HashMap<String, Number> stats, Class<? extends LivingEntity> entityClass) {
        this.id = id;
        this.name = name;
        this.level = level;


        this.spawned = false;
        this.entityClass = (Class<LivingEntity>) entityClass;
        this.stats = stats;

        if(stats.get("health") != null) this.stats.put("health", EntityUtils.getHealthForEntityLevel(stats.get("health").doubleValue(), level));

        listenForDamage();
    }

    public String getID() {
        return id;
    }

    public String getName(){
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(isSpawned()) {
            this.level = level;
            EntityUtils.updateEntityHealthBar(this);
        }
    }

    public double getCurrentHealth() {
        return entity == null ? 0 : entity.getHealth();
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void spawn(Location location) {
        if(location != null && !isSpawned()) {
            World world = location.getWorld();

            entity = world.spawn(location, entityClass);
            entity.setMetadata("skyblock_entity_id", new FixedMetadataValue(Skyblock.getSkyblockInstance(), getID()));
            EntityRegistry.getInstance().addEntity(this);

            setStats(stats);
            entity.setHealth(getStat("health").doubleValue());

            EntityUtils.updateEntityHealthBar(this);
            spawned = true;
        }

    }

    public LivingEntity getLivingEntity() {
        return entity;
    }

    public Class<? extends LivingEntity> getEntityClass() {
        return entityClass;
    }

    public void despawn() {
        if(isSpawned()) {
            entity.remove();
            spawned = false;
        }
    }

    public void setName(String name) {
        if(isSpawned()) {
            this.name = name;
            EntityUtils.updateEntityHealthBar(this);
        }
    }

    public void damage(double damage) {
        if(isSpawned()) {
            entity.damage(damage);
            EntityUtils.updateEntityHealthBar(this);
        }
    }

    public void setStat(String statID, Number value){
        StatRegistry statRegistry = StatRegistry.getInstance();

        Stat stat = statRegistry.getStat(statID);
        if(stat != null){
            SkyblockEntityStatUpdateEvent statUpdateEvent = new SkyblockEntityStatUpdateEvent(this, stat, getStat(statID), value);
            statUpdateEvent.call();

            if(!statUpdateEvent.isCancelled()){
                value = statUpdateEvent.getNewValue();

                stats.put(statID, value);
                stat.applyStat(this, value);
            }
        }
    }

    public void setStats(HashMap<String, Number> stats){
        for(String statID : stats.keySet()){
            setStat(statID, stats.get(statID));
        }
    }

    public Number getStat(String statID){
        if(hasStat(statID)) return stats.get(statID);
        
        return 0;
    }

    public void removeStat(String statID){
        setStat(statID, 0);
    }

    public boolean hasStat(String statID){
        return stats.containsKey(statID);
    }

    public HashMap<String, Number> getStats(){
        return stats;
    }

    public void listenForDamage() {
        Events.subscribe(SkyblockEntityDamageEvent.class)
                .filter(e -> !e.isCancelled())
                .filter(e -> e.getEntity().equals(this))
                .handler(e -> {
                    if(isSpawned()) {
                        EntityUtils.updateEntityHealthBar(this);
                    }
                });

        Events.subscribe(SkyblockEntityDamageByPlayerEvent.class)
                .filter(e -> !e.isCancelled())
                .filter(e -> e.getEntity().equals(this))
                .handler(e -> {
                    if(isSpawned()) {
                        EntityUtils.updateEntityHealthBar(this);
                    }
                });
    }

}
