package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.entity.EntityDamageEvent;

public class SkyblockPlayerDamageEvent extends SkyblockPlayerEvent {

    private DamageCause cause;
    private EntityDamageEvent bukkitEvent;

    public SkyblockPlayerDamageEvent(SkyblockPlayer player, DamageCause cause, EntityDamageEvent event) {
        super(player);

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

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        bukkitEvent.setCancelled(cancelled);
    }
}
