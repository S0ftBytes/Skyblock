package me.s0ftbytes.skyblock.Events.EntityEvents;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import org.bukkit.Location;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SkyblockEntitySpawnEvent extends SkyblockEntityEvent {

    private Location location;
    private CreatureSpawnEvent bukkitEvent;
    public SkyblockEntitySpawnEvent(SkyblockEntity entity, Location location, CreatureSpawnEvent bukkitEvent) {
        super(entity);

        this.bukkitEvent = bukkitEvent;
    }

    public Location getSpawnLocation() {
        return location;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        bukkitEvent.setCancelled(cancelled);
    }

}
