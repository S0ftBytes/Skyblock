package me.s0ftbytes.skyblock.Events.EntityEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Enums.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent;

public class SkyblockEntityDamageEvent extends SkyblockEntityEvent {

    private double damage;
    private double finalDamage;
    private DamageCause cause;
    private SkyblockEntity entity;
    private EntityDamageEvent bukkitEvent;

    public SkyblockEntityDamageEvent(SkyblockEntity entity, double damage, double finalDamage, DamageCause cause, EntityDamageEvent event) {
        super(entity);

        this.entity = entity;
        this.damage = damage;
        this.finalDamage = finalDamage;
        this.cause = cause;
        this.bukkitEvent = event;
    }

    public SkyblockEntity getEntity() {
        return entity;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
        bukkitEvent.setDamage(damage);
    }

    public double getFinalDamage() {
        return finalDamage;
    }

    public DamageCause getCause() {
        return cause;
    }

    public void setCause(DamageCause cause) {
        this.cause = cause;
    }

    public double getNewHealth() {
        return entity.getStat("health").intValue() - finalDamage;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        bukkitEvent.setCancelled(cancelled);
    }
}