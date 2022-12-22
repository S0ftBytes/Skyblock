package me.s0ftbytes.skyblock;

import me.s0ftbytes.skyblock.Configuration.PlayerDataFile;
import me.s0ftbytes.skyblock.Events.PlayerEvents.SkyblockPlayerStatUpdateEvent;
import me.s0ftbytes.skyblock.Registries.StatRegistry;
import me.s0ftbytes.skyblock.Skills.Skill;
import me.s0ftbytes.skyblock.Skills.SkillDecloration;
import me.s0ftbytes.skyblock.Stats.Stat;
import me.s0ftbytes.skyblock.Utils.PlayerUtils;
import me.s0ftbytes.skyblock.Utils.StringUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SkyblockPlayer {
    private Player player;
    private HashMap<String, Number> stats;
    private PlayerDataFile dataFile;

    public SkyblockPlayer(Player player){
        this.player = player;
        this.stats = new HashMap<>();

        this.dataFile = new PlayerDataFile(this);
        this.dataFile.load();
    }

    public Player getBukkitPlayer(){
        return player;
    }

    public String getName(){
        return player.getName();
    }

    public String getDisplayName(){
        return player.getDisplayName();
    }

    public UUID getUUID(){
        return player.getUniqueId();
    }

    public void setDisplayName(String displayName){
        player.setDisplayName(displayName);
    }

    public void sendMessage(String message){
        player.sendMessage(StringUtils.format(message));
    }

    public void sendTitle(String title, String subtitle){
        player.sendTitle(title, subtitle);
    }

    public void sendActionBar(String message){
        PlayerUtils.sendActionBar(this, message);
    }

    public void setStat(String statID, Number value){
        StatRegistry statRegistry = StatRegistry.getInstance();

        Stat stat = statRegistry.getStat(statID);
        if(stat != null){
            SkyblockPlayerStatUpdateEvent statUpdateEvent = new SkyblockPlayerStatUpdateEvent(this, stat, getStat(statID), value);
            statUpdateEvent.call();

            if(!statUpdateEvent.isCancelled()){
                value = statUpdateEvent.getNewValue();

                stats.put(statID, value);
                stat.applyStat(this, value);
            }
        }
    }

    public void setStats(HashMap<String, Number> stats){
        for(String statID : stats.keySet()){
            setStat(statID, stats.get(statID));
        }
    }

    public Number getStat(String statID){
        if(stats.containsKey(statID)) return stats.get(statID);
        return 0;
    }

    public void removeStat(String statID){
        setStat(statID, 0);
    }

    public boolean hasStat(String statID){
        return stats.containsKey(statID);
    }

    public HashMap<String, Number> getStats(){
        return stats;
    }

    public Location getLocation() {
        return getBukkitPlayer().getLocation();
    }

    public PlayerDataFile getDataFile() {
        return dataFile;
    }

    public Skill getSkill(SkillDecloration skillDecloration) {
        return skillDecloration.getSkill();
    }

}
