package net.starly.region.event;

import net.starly.core.data.location.Region;
import net.starly.region.data.RegionMapData;
import net.starly.region.events.RegionEnterEvent;
import net.starly.region.events.RegionLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;


public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        new BukkitRunnable() {
            @Override
            public void run() {
                RegionMapData.regionMap.keySet().forEach(key -> {
                    Region region = RegionMapData.regionMap.get(key);

                    if (!region.contains(from) && region.contains(to)) Bukkit.getPluginManager().callEvent(new RegionEnterEvent(p, region, key));
                    if (region.contains(from) && !region.contains(to)) Bukkit.getPluginManager().callEvent(new RegionLeaveEvent(p, region, key));
                });
            }
        }.run();
    }


}
