package net.starly.region.event;

import net.starly.core.data.location.Region;
import net.starly.region.data.RegionMapData;
import net.starly.region.events.RegionEnterEvent;
import net.starly.region.events.RegionLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (p.getVehicle() != null && p.getVehicle().getType() == EntityType.BOAT) return;

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

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
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

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();

        new BukkitRunnable() {
            @Override
            public void run() {
                RegionMapData.regionMap.keySet().forEach(key -> {
                    Region region = RegionMapData.regionMap.get(key);

                    if (region.contains(loc)) Bukkit.getPluginManager().callEvent(new RegionEnterEvent(p, region, key));
                });
            }
        }.run();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();

        new BukkitRunnable() {
            @Override
            public void run() {
                RegionMapData.regionMap.keySet().forEach(key -> {
                    Region region = RegionMapData.regionMap.get(key);

                    if (region.contains(loc)) Bukkit.getPluginManager().callEvent(new RegionLeaveEvent(p, region, key));
                });
            }
        }.run();
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        if (!(event.getVehicle().getPassenger() instanceof Player p)) return;
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
