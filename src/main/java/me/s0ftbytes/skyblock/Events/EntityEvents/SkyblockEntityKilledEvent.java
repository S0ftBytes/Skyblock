package me.s0ftbytes.skyblock.Events.EntityEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.Location;

public class SkyblockEntityKilledEvent extends SkyblockEntityEvent {

    private SkyblockPlayer killer;
    private Location location;
    public SkyblockEntityKilledEvent(SkyblockEntity entity, SkyblockPlayer killer, Location location) {
        super(entity);

        this.killer = killer;
        this.location = location;
    }

    public SkyblockPlayer getKiller() {
        return killer;
    }

    public Location getLocation() {
        return location;
    }

}
