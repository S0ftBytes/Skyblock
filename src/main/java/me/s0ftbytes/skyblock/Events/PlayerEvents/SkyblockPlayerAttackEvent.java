package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class SkyblockPlayerAttackEvent extends SkyblockPlayerEvent {

    private SkyblockEntity entity;
    private Double damage;
    private ItemStack weapon;

    public SkyblockPlayerAttackEvent(SkyblockPlayer player, SkyblockEntity entity, double damage, ItemStack weapon) {
        super(player);

        this.entity = entity;
        this.damage = damage;
        this.weapon = weapon;
    }

    public SkyblockEntity getEntity() {
        return entity;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public ItemStack getWeapon() {
        return weapon;
    }

}
