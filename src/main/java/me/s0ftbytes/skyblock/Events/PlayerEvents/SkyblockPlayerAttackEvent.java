package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class SkyblockPlayerAttackEvent extends SkyblockPlayerEvent {

    private SkyblockPlayer player;
    private Entity entity;
    private Double damage;
    private ItemStack weapon;

    public SkyblockPlayerAttackEvent(SkyblockPlayer player, Entity entity, double damage, ItemStack weapon) {
        super(player);

        this.player = player;
        this.entity = entity;
        this.damage = damage;
        this.weapon = weapon;
    }

    public SkyblockPlayer getPlayer() {
        return player;
    }

    public Entity getEntity() {
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
