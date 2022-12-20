package me.s0ftbytes.skyblock.Events.Firers;

import me.lucko.helper.Events;
import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.Events.EntityEvents.SkyblockEntityDamageByPlayerEvent;
import me.s0ftbytes.skyblock.Events.EntityEvents.SkyblockEntityDamageEvent;
import me.s0ftbytes.skyblock.Events.EntityEvents.SkyblockEntitySpawnEvent;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerAttackEvent;
import me.s0ftbytes.skyblock.Registries.EntityRegistry;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityEventFirers {

    private EntityRegistry entityRegistry;
    public EntityEventFirers(){
        entityRegistry = EntityRegistry.getInstance();

        fireEntitySpawnEvent();
        registerEntityDamageEventFirers();
    }

    public void fireEntitySpawnEvent(){
        Events.subscribe(CreatureSpawnEvent.class)
                .handler(e -> {
                    if(e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM){
                        e.setCancelled(true);
                        return;
                    }

                    SkyblockEntitySpawnEvent event = new SkyblockEntitySpawnEvent(null, e.getLocation(), e);

                    Events.call(event);
                });
    }

    public void registerEntityDamageEventFirers(){
        Events.subscribe(EntityDamageEvent.class)
                .filter(e -> !(e.getEntity() instanceof Player))
                .filter(e -> e.getEntity().hasMetadata("skyblock_entity_id"))
                .handler(e -> {
                    SkyblockEntity entity = entityRegistry.getEntity(e.getEntity().getEntityId());

                    SkyblockEntityDamageEvent skyblockEntityDamageEvent = new SkyblockEntityDamageEvent(entity, DamageCause.getDamageCause(e.getCause()), e);
                    skyblockEntityDamageEvent.call();
                });

        Events.subscribe(SkyblockPlayerAttackEvent.class)
                .filter(e -> !e.isCancelled())
                .handler(e -> {
                    SkyblockEntity entity = e.getEntity();
                    SkyblockPlayer player = e.getPlayer();

                    SkyblockEntityDamageByPlayerEvent skyblockEntityAttackedEvent = new SkyblockEntityDamageByPlayerEvent(entity, player, DamageCause.getDamageCause(e.getBukkitEvent().getCause()));
                    SkyblockEntityDamageEvent skyblockEntityDamageEvent = new SkyblockEntityDamageEvent(entity, DamageCause.getDamageCause(e.getBukkitEvent().getCause()), e.getBukkitEvent());

                    skyblockEntityAttackedEvent.call();
                    skyblockEntityDamageEvent.call();
                });
    }


}
