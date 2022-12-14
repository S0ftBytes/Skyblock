package me.s0ftbytes.skyblock.Enums;

public enum DamageCause {
    MELEE_ATTACK,
    RANGED_ATTACK,
    SUFFOCATION,
    FALL,
    FIRE,
    LAVA,
    DROWNING,
    BLOCK_EXPLOSION,
    ENTITY_EXPLOSION,
    VOID,
    LIGHTNING,
    MAGIC;

    public static DamageCause getDamageCause(org.bukkit.event.entity.EntityDamageEvent.DamageCause cause) {
        switch (cause) {
            case ENTITY_ATTACK:
                return MELEE_ATTACK;
            case PROJECTILE:
                return RANGED_ATTACK;
            case SUFFOCATION:
                return SUFFOCATION;
            case FALL:
                return FALL;
            case FIRE:
            case FIRE_TICK:
                return FIRE;
            case LAVA:
                return LAVA;
            case DROWNING:
                return DROWNING;
            case BLOCK_EXPLOSION:
                return BLOCK_EXPLOSION;
            case ENTITY_EXPLOSION:
                return ENTITY_EXPLOSION;
            case VOID:
                return VOID;
            case LIGHTNING:
                return LIGHTNING;
            case MAGIC:
                return MAGIC;
            default:
                return null;
        }
    }
}
