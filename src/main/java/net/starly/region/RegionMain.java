package net.starly.region;

import net.starly.core.bstats.Metrics;
import net.starly.core.data.Config;
import net.starly.core.data.MessageConfig;
import net.starly.core.data.location.Region;
import net.starly.region.command.RegionCmd;
import net.starly.region.command.RegionCmdTabComplete;
import net.starly.region.data.RegionMapData;
import net.starly.region.event.PlayerInteractListener;
import net.starly.region.event.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class RegionMain extends JavaPlugin {
    public static String prefix = "§6[구역] ";

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("ST-Core") == null) {
            Bukkit.getLogger().warning("[" + this.getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            Bukkit.getLogger().warning("[" + this.getName() + "] 다운로드 링크 : &fhttps://discord.gg/TF8jqSJjCG");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        new Metrics(this, 17293);


        // CONFIG
        Config config = new Config("config", this);
        config.loadDefaultPluginConfig();

        File dataFolder = new File(this.getDataFolder(), "data");
        if (!dataFolder.exists()) dataFolder.mkdirs();

        List.of(dataFolder.listFiles()).forEach(file -> {
            Config regionData = new Config("data/" + file.getName().replace(".yml", ""), this);
            regionData.loadDefaultConfig();

            Location pos1 = regionData.getLocation("pos1");
            Location pos2 = regionData.getLocation("pos2");
            Region region = new Region(pos1, pos2);
            RegionMapData.regionMap.put(file.getName().replace(".yml", ""), region);
        });


        // EVENT LISTENER
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);


        // COMMAND
        Bukkit.getPluginCommand("stregion").setExecutor(new RegionCmd());
        Bukkit.getPluginCommand("stregion").setTabCompleter(new RegionCmdTabComplete());
    }

    @Override
    public void onDisable() {
        RegionMapData.regionMap.forEach((name, region) -> {
            Config regionData = new Config("data/" + name, this);
            regionData.loadDefaultConfig();

            regionData.setLocation("pos1", region.getPos1());
            regionData.setLocation("pos2", region.getPos2());
        });

        List.of(new File(this.getDataFolder(), "data").listFiles()).forEach(file -> {
            if (!RegionMapData.regionMap.containsKey(file.getName().replace(".yml", ""))) file.delete();
        });
    }
}
