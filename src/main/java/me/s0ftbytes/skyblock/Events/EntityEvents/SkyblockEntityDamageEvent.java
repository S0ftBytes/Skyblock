package me.s0ftbytes.skyblock.Events.EntityEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Enums.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent;

public class SkyblockEntityDamageEvent extends SkyblockEntityEvent {

    private DamageCause cause;
    private SkyblockEntity entity;
    private EntityDamageEvent bukkitEvent;

    public SkyblockEntityDamageEvent(SkyblockEntity entity, DamageCause cause, EntityDamageEvent event) {
        super(entity);

        this.entity = entity;
        this.cause = cause;
        this.bukkitEvent = event;
    }

    public SkyblockEntity getEntity() {
        return entity;
    }

    public double getDamage() {
        return bukkitEvent.getDamage();
    }

    public void setDamage(double damage) {
        bukkitEvent.setDamage(damage);
    }

    public DamageCause getCause() {
        return cause;
    }

    public void setCause(DamageCause cause) {
        this.cause = cause;
    }

    public double getNewHealth() {
        return entity.getStat("health").intValue() - bukkitEvent.getFinalDamage();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        bukkitEvent.setCancelled(cancelled);
    }
}