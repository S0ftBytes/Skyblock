package me.s0ftbytes.skyblock.Entities.Monsters;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;

import java.util.HashMap;

public class Zombie extends SkyblockEntity {
    public Zombie(String id, String name, int level, HashMap<String, Number> stats) {
        super(id, name, level, stats, org.bukkit.entity.Zombie.class);
    }

}
