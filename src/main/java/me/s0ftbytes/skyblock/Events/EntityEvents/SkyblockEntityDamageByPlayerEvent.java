package me.s0ftbytes.skyblock.Events.EntityEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SkyblockEntityDamageByPlayerEvent extends SkyblockEntityEvent {

    private SkyblockPlayer player;
    private double damage;
    private double finalDamage;
    private DamageCause cause;
    private EntityDamageByEntityEvent bukkitEvent;

    public SkyblockEntityDamageByPlayerEvent(SkyblockEntity entity, SkyblockPlayer player, double damage, double finalDamage, DamageCause cause) {
        super(entity);

        this.player = player;
        this.damage = damage;
        this.finalDamage = finalDamage;
        this.cause = cause;
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
        return getEntity().getStat("health").intValue() - getFinalDamage();
    }

    public SkyblockPlayer getPlayer() {
        return player;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        bukkitEvent.setCancelled(cancelled);
    }


}
