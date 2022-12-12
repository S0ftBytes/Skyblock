package me.s0ftbytes.skyblock.Events;

import me.s0ftbytes.skyblock.SkyblockPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.sql.Timestamp;

public abstract class SkyblockPlayerEvent extends Event implements Cancellable {
        private static final HandlerList HANDLER_LIST = new HandlerList();
        private boolean cancelled;
        private SkyblockPlayer player;
        private Timestamp eventTimestamp;

        public SkyblockPlayerEvent(SkyblockPlayer player) {
            cancelled = false;
            this.player = player;

            this.eventTimestamp = new Timestamp(System.currentTimeMillis());
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean cancelled) {
            this.cancelled = cancelled;
        }

        @Override
        public HandlerList getHandlers() {
            return HANDLER_LIST;
        }

        public static HandlerList getHandlerList() {
            return HANDLER_LIST;
        }

        public Timestamp getEventTimestamp() {
            return eventTimestamp;
        }

}
