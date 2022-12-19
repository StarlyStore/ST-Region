package com.github.nicks.region;

import com.github.nicks.nicklib.data.Config;
import com.github.nicks.region.command.RegionCmd;
import com.github.nicks.region.event.PlayerInteractListener;
import com.github.nicks.region.event.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Region extends JavaPlugin {


    @Override
    public void onEnable() {
        loadConfig();
        init();
    }


    /**
     * 이벤트 & 커맨드를 등록합니다.
     */
    public void init() {

        // 이벤트
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);

        // 커맨드
        Bukkit.getPluginCommand("nkregion").setExecutor(new RegionCmd());
    }


    /**
     * 콘피그를 로드합니다.
     */
    public void loadConfig() {
        Config config = new Config("config");
        config.loadDefaultConfig();
    }
}
