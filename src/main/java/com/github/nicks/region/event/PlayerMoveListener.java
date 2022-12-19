package com.github.nicks.region.event;

import com.github.nicks.nicklib.data.Config;
import com.github.nicks.nicklib.utils.Tuple;
import com.github.nicks.region.Region;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayerMoveListener implements Listener {


    private Map<Player, Tuple<String, Region>> regionMap = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();



    }
}
