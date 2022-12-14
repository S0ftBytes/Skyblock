package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.Enums.DamageCause;
import me.s0ftbytes.skyblock.SkyblockPlayer;

public class SkyblockPlayerDamageEvent extends SkyblockPlayerEvent {

    private double damage;
    private DamageCause cause;
    private SkyblockPlayer player;

    public SkyblockPlayerDamageEvent(SkyblockPlayer player, double damage, DamageCause cause) {
        super(player);

        this.player = player;
        this.damage = damage;
        this.cause = cause;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public SkyblockPlayer getPlayer() {
        return player;
    }

    public DamageCause getCause() {
        return cause;
    }

    public void setCause(DamageCause cause) {
        this.cause = cause;
    }
}
