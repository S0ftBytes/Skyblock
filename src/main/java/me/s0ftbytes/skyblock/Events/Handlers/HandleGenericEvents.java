package me.s0ftbytes.skyblock.Events.Handlers;

import me.lucko.helper.Events;
import me.lucko.helper.Schedulers;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerAttackEvent;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerDamageByEntityEvent;
import me.s0ftbytes.skyblock.Utils.StringUtils;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class HandleGenericEvents {

    public HandleGenericEvents(){
        handleDamageDisplay();
    }

    public void handleDamageDisplay(){
        Events.subscribe(SkyblockPlayerDamageByEntityEvent.class)
                .filter(e -> !e.isCancelled())
                .handler(e -> {
                    double damage = e.getDamage();

                    Location location = e.getPlayer().getLocation().add(0, 2, 0);

                    spawnDamageDisplay(location, StringUtils.createDamageString(damage, e.isCritical()));
                });

        Events.subscribe(SkyblockPlayerAttackEvent.class)
                .filter(e -> !e.isCancelled())
                .handler(e -> {
                    double damage = e.getDamage();

                    double entityHeight = e.getEntity().getLivingEntity().getEyeHeight();

                    Location location = e.getEntity().getLivingEntity().getLocation().add(0, entityHeight, 0);

                    spawnDamageDisplay(location, StringUtils.createDamageString(damage, e.isCritical()));
                });
    }

    private void spawnDamageDisplay(Location location, String damageDisplay){
        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);

        armorStand.setCustomName(damageDisplay);
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setMarker(true);
        armorStand.setSmall(true);

        Schedulers.sync().runLater(() -> {
            armorStand.remove();
        }, 20L);
    }
}
