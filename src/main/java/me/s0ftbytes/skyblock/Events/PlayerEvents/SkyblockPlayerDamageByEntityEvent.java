package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SkyblockPlayerDamageByEntityEvent extends SkyblockPlayerEvent {

    private SkyblockEntity entity;
    private DamageCause cause;
    private EntityDamageByEntityEvent bukkitEvent;
    private boolean critical = false;

    public SkyblockPlayerDamageByEntityEvent(SkyblockPlayer player, SkyblockEntity entity, DamageCause cause, EntityDamageByEntityEvent event) {
        super(player);

        this.entity = entity;
        this.cause = cause;
        this.bukkitEvent = event;
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
        return getPlayer().getStat("health").intValue() - bukkitEvent.getFinalDamage();
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
