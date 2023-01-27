package net.starly.region.api;

import net.starly.core.data.location.Region;
import net.starly.region.data.RegionMapData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.starly.region.data.RegionMapData.regionMap;

public class RegionAPI {
    public RegionAPI(@NotNull JavaPlugin plugin) {
    }


    // -----------------------------[ REGION ]---------------------------- \\
    @NotNull public void createRegion(@NotNull String name, @NotNull Location pos1, @NotNull Location pos2) {
        regionMap.put(name, new Region(pos1, pos2));
    }
    @NotNull public Region getRegion(@NotNull String name) {
        return regionMap.getOrDefault(name, null);
    }
    @NotNull public List<Region> getRegions() {
        return new ArrayList<>(regionMap.values());
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
        return getRegion(name) != null && getRegion(name).contains(location);
    }
    public List<Player> getPlayersInRegion(@NotNull String name) {
        return getPlayersInRegion(getRegion(name));
    }
    public List<Player> getPlayersInRegion(@NotNull Region region) {
        List<Player> players = new ArrayList<>();

        region.getPos1().getWorld().getPlayers().forEach(player -> {
            if (region.contains(player.getLocation())) players.add(player);
        });

        return players;
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
