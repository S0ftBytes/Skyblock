package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class SkyblockPlayerAttackEvent extends SkyblockPlayerEvent {

    private SkyblockEntity entity;
    private Double damage;
    private final ItemStack weapon;
    private boolean critical = false;
    private EntityDamageByEntityEvent bukkitEvent;

    public SkyblockPlayerAttackEvent(SkyblockPlayer player, SkyblockEntity entity, double damage, ItemStack weapon, EntityDamageByEntityEvent event) {
        super(player);

        this.entity = entity;
        this.damage = damage;
        this.weapon = weapon;
        this.bukkitEvent = event;
    }

    public SkyblockEntity getEntity() {
        return entity;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        bukkitEvent.setDamage(damage);
        this.damage = damage;
    }

    public ItemStack getWeapon() {
        return weapon;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public EntityDamageByEntityEvent getBukkitEvent() {
        return bukkitEvent;
    }

}
