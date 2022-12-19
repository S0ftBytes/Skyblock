package me.s0ftbytes.skyblock.Events.EntityEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerEvent;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Stats.Stat;

public class SkyblockEntityStatUpdateEvent extends SkyblockEntityEvent {

    private Stat stat;
    private Number oldValue;
    private Number newValue;

    public SkyblockEntityStatUpdateEvent(SkyblockEntity entity, Stat stat, Number oldValue, Number newValue) {
        super(entity);

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
}
