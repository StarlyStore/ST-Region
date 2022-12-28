package net.starly.region.api;

import net.starly.core.data.location.Region;
import net.starly.region.data.RegionMapData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static net.starly.region.data.RegionMapData.regionMap;

public class RegionAPI {
    public RegionAPI(@NotNull JavaPlugin plugin) {
        System.out.println("RegionAPI has been loaded by " + plugin.getName() + "!");
    }


    // -----------------------------[ REGION ]---------------------------- \\
    @NotNull public void createRegion(@NotNull String name, @NotNull Location pos1, @NotNull Location pos2) {
        regionMap.put(name, new Region(pos1, pos2));
    }
    @NotNull public Region getRegion(@NotNull String name) {
        return regionMap.getOrDefault(name, null);
    }
    @NotNull public List<Region> getRegions() {
        return regionMap.values().stream().toList();
    }
    @NotNull public Map<String, Region> getRegionMap() {
        return regionMap;
    }
    @NotNull public String getName(@NotNull Region region) {
        return regionMap.entrySet().stream().filter(entry -> entry.getValue().equals(region)).findFirst().get().getKey();
    }
    public void deleteRegion(@NotNull String name) {
        regionMap.remove(name);
    }
    public boolean contains(@NotNull String name, @NotNull Location location) {
        return regionMap.containsKey(name) && regionMap.get(name).contains(location);
    }



    // ------------------------------[ POS ]------------------------------ \\


    public void setPos1(@NotNull Player player, @NotNull Location location) {
        RegionMapData.pos1Map.put(player, location);
    }
    public void setPos2(@NotNull Player player, @NotNull Location location) {
        RegionMapData.pos2Map.put(player, location);
    }
    @NotNull public Location getPos1(@NotNull Player player) {
        return RegionMapData.pos1Map.getOrDefault(player, null);
    }
    @NotNull public Location getPos2(@NotNull Player player) {
        return RegionMapData.pos2Map.getOrDefault(player, null);
    }
}
