package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Stats.Stat;

public class SkyblockPlayerStatUpdateEvent extends SkyblockPlayerEvent {

    private Stat stat;
    private Number oldValue;
    private Number newValue;
    private SkyblockPlayer player;

    public SkyblockPlayerStatUpdateEvent(SkyblockPlayer player, Stat stat, Number oldValue, Number newValue) {
        super(player);

        this.player = player;
        this.stat = stat;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Stat stat() {
        return stat;
    }

    public Number getOldValue() {
        return oldValue;
    }

    public Number getNewValue() {
        return newValue;
    }

    public void setNewValue(Number newValue) {
        this.newValue = newValue;
    }

    public SkyblockPlayer getPlayer() {
        return player;
    }
}
