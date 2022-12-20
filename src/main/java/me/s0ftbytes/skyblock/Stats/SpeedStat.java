package me.s0ftbytes.skyblock.Stats;

import me.s0ftbytes.skyblock.Entities.SkyblockEntity;
import me.s0ftbytes.skyblock.SkyblockPlayer;
import me.s0ftbytes.skyblock.Utils.StatUtils;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;

public class SpeedStat implements Stat {
    @Override
    public String getID() {
        return "speed";
    }

    @Override
    public String getName() {
        return "Speed";
    }

    @Override
    public String getDisplay(Number value) {
        return StatUtils.getStatDisplay(getID(), value);
    }

    @Override
    public Number getDefaultValue() {
        return StatUtils.getDefaultStatValue(getID());
    }

    @Override
    public Number getMinValue() {
        return 1;
    }

    @Override
    public Number getMaxValue() {
        return 400;
    }

    @Override
    public boolean isHiddenStat() {
        return false;
    }

    @Override
    public void applyStat(SkyblockPlayer player, Number value) {
        if(value.intValue() < getMinValue().intValue()) value = getMinValue();
        if(value.intValue() > getMaxValue().intValue()) value = getMaxValue();

        float defaultAttr = 0.2f;
        player.getBukkitPlayer().setWalkSpeed(defaultAttr * (value.floatValue() / 100));
    }

    @Override
    public void applyStat(SkyblockEntity entity, Number value) {
        if(value.intValue() < getMinValue().intValue()) value = getMinValue();
        if(value.intValue() > getMaxValue().intValue()) value = getMaxValue();

        float defaultAttr = 0.2f;

        EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) entity.getLivingEntity()).getHandle();
        nmsEntity.getAttributeInstance(net.minecraft.server.v1_8_R3.GenericAttributes.MOVEMENT_SPEED).setValue(defaultAttr * (value.floatValue() / 100));
    }
}
