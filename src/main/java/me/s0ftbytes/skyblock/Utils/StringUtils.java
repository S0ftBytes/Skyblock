package me.s0ftbytes.skyblock.Utils;

import java.text.DecimalFormat;

public class StringUtils {

        public static String format(String string){
            return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);
        }

        public static String createCriticalString(double damage){
            DecimalFormat df = new DecimalFormat("#,###");
            return format(String.format("&c☣ %s &c☣", df.format(damage)));
        }

        public static String createDamageString(double damage, boolean critical){
            if(critical) return createCriticalString(damage);

            DecimalFormat df = new DecimalFormat("#,###");
            return format(String.format("&7%s", df.format(damage)));
        }

}
