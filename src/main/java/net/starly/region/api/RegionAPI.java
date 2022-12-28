package net.starly.region.api;

import net.starly.core.data.location.Region;
import net.starly.region.data.RegionMapData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class RegionAPI {
    public RegionAPI(JavaPlugin plugin) {
        System.out.println("RegionAPI has been loaded by " + plugin.getName() + "!");
    }

    public void createRegion(String name, Location pos1, Location pos2) {
        RegionMapData.regionMap.put(name, new Region(pos1, pos2));
    }

    public void deleteRegion(String name) {
        RegionMapData.regionMap.remove(name);
    }

    public Region getRegion(String name) {
        return RegionMapData.regionMap.get(name);
    }

    public List<Region> getRegions() {
        return RegionMapData.regionMap.values().stream().toList();
    }

    public boolean contains(String name, Location location) {
        return RegionMapData.regionMap.get(name).contains(location);
    }

    public Location getPos1(Player player) {
        return RegionMapData.pos1Map.getOrDefault(player, null);
    }

    public Location getPos2(Player player) {
        return RegionMapData.pos2Map.getOrDefault(player, null);
    }

    public void setPos1(Player player, Location location) {
        RegionMapData.pos1Map.put(player, location);
    }

    public void setPos2(Player player, Location location) {
        RegionMapData.pos2Map.put(player, location);
    }
}
