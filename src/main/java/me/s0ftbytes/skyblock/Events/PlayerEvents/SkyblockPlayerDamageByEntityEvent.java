package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SkyblockPlayerDamageByEntityEvent extends SkyblockPlayerEvent {

    private SkyblockEntity entity;
    private double damage;
    private double finalDamage;
    private DamageCause cause;
    private EntityDamageByEntityEvent bukkitEvent;
    private boolean critical = false;

    public SkyblockPlayerDamageByEntityEvent(SkyblockPlayer player, SkyblockEntity entity, double damage, double finalDamage, DamageCause cause, EntityDamageByEntityEvent event) {
        super(player);

        this.entity = entity;
        this.damage = damage;
        this.finalDamage = finalDamage;
        this.cause = cause;
        this.bukkitEvent = event;
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
        return getPlayer().getStat("health").intValue() - getFinalDamage();
    }

    public SkyblockEntity getEntity() {
        return entity;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        bukkitEvent.setCancelled(cancelled);
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }


}
