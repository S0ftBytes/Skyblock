package me.s0ftbytes.skyblock.Utils;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class PlayerUtils {
    public static void sendActionBar(SkyblockPlayer player, String message){
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(new ChatComponentText(message), (byte) 2);
        ((CraftPlayer) player.getBukkitPlayer()).getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }
}
