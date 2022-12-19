package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.entity.EntityDamageEvent;

public class SkyblockPlayerDamageEvent extends SkyblockPlayerEvent {

    private double damage;
    private double finalDamage;
    private DamageCause cause;
    private EntityDamageEvent bukkitEvent;

    public SkyblockPlayerDamageEvent(SkyblockPlayer player, double damage, double finalDamage, DamageCause cause, EntityDamageEvent event) {
        super(player);

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
        return getPlayer().getStat("health").intValue() - finalDamage;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        bukkitEvent.setCancelled(cancelled);
    }
}
