package net.starly.region.data;

import net.starly.core.data.location.Region;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RegionMapData {

    public static Map<String, Region> regionMap = new HashMap<>();
    public static Map<Player, Location> pos1Map = new HashMap<>();
    public static Map<Player, Location> pos2Map = new HashMap<>();
}