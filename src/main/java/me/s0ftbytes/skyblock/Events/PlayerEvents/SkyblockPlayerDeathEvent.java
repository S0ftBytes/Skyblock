package me.s0ftbytes.skyblock.Events.PlayerEvents;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.Location;

public class SkyblockPlayerDeathEvent extends SkyblockPlayerEvent {
    private Location deathLocation;

    public SkyblockPlayerDeathEvent(SkyblockPlayer player, Location deathLocation) {
        super(player);

        this.deathLocation = deathLocation;
    }

    public Location getDeathLocation() {
        return deathLocation;
    }

}
