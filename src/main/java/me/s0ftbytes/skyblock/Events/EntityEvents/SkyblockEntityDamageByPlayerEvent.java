package me.s0ftbytes.skyblock.Events.EntityEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SkyblockEntityDamageByPlayerEvent extends SkyblockEntityEvent {

    private SkyblockPlayer player;
    private DamageCause cause;
    private EntityDamageByEntityEvent bukkitEvent;

    public SkyblockEntityDamageByPlayerEvent(SkyblockEntity entity, SkyblockPlayer player, DamageCause cause) {
        super(entity);

        this.player = player;
        this.cause = cause;
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
        return getEntity().getStat("health").intValue() - bukkitEvent.getFinalDamage();
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
