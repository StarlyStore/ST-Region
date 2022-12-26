package net.starly.region.events;

import net.starly.core.data.location.Region;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class RegionLeaveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Region region;
    private final String name;

    public RegionLeaveEvent(Player player, Region region, String name) {
        this.player = player;
        this.region = region;
        this.name = name;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Region getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }
}
