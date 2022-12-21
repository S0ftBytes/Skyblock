package me.s0ftbytes.skyblock.Configuration;

import me.s0ftbytes.skyblock.SkyblockPlayer;

public class PlayerDataFile extends ConfigurationFile {

    public PlayerDataFile(SkyblockPlayer player) {
        super(player.getUUID().toString(), "player-data");
    }

}
