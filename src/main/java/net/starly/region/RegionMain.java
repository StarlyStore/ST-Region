package net.starly.region;

import net.starly.core.bstats.Metrics;
import net.starly.core.data.Config;
import net.starly.core.data.location.Region;
import net.starly.region.api.RegionAPI;
import net.starly.region.command.RegionCmd;
import net.starly.region.command.RegionCmdTabComplete;
import net.starly.region.data.RegionMapData;
import net.starly.region.event.PlayerInteractListener;
import net.starly.region.event.PlayerMoveListener;
import net.starly.region.events.RegionEnterEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionMain extends JavaPlugin {
    private static JavaPlugin plugin;
    public static Config config;

    @Override
    public void onEnable() {
        // DEPENDENCY
        if (Bukkit.getPluginManager().getPlugin("ST-Core") == null) {
            Bukkit.getLogger().warning("[" + this.getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            Bukkit.getLogger().warning("[" + this.getName() + "] 다운로드 링크 : &fhttp://starly.kr/discord");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        plugin = this;
        new Metrics(plugin, 17293);

        // CONFIG
        config = new Config("config", plugin);
        config.loadDefaultConfig();
        config.setPrefix("prefix");

        RegionAPI regionAPI = new RegionAPI();
        Config dataDir = new Config("data/", plugin);
        dataDir.getFileNames().forEach(fileName -> {
            Config regionData = new Config("data/" + fileName, plugin);
            Location pos1 = regionData.getLocation("pos1");
            Location pos2 = regionData.getLocation("pos2");

            RegionMapData.regionMap.put(fileName, new Region(pos1, pos2));
            regionAPI.getPlayersInRegion(fileName).forEach(player -> Bukkit.getPluginManager().callEvent(new RegionEnterEvent(player, regionAPI.getRegion(fileName), fileName)));
        });

        // COMMAND
        Bukkit.getPluginCommand("stregion").setExecutor(new RegionCmd());
        Bukkit.getPluginCommand("stregion").setTabCompleter(new RegionCmdTabComplete());

        // EVENT
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), plugin);
    }

    @Override
    public void onDisable() {
        RegionMapData.regionMap.forEach((name, region) -> {
            Config regionData = new Config("data/" + name, plugin);
            regionData.setLocation("pos1", region.getPos1());
            regionData.setLocation("pos2", region.getPos2());
        });

        Config dataDir = new Config("data/", plugin);
        dataDir.getFileNames().forEach(fileName -> {
            if (!RegionMapData.regionMap.containsKey(fileName)) new Config("data/" + fileName, plugin).delete();
        });
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
