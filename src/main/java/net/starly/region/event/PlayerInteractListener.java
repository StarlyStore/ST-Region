package net.starly.region.event;

import net.starly.core.data.location.Region;
import net.starly.region.data.RegionMapData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        Location location = event.getClickedBlock().getLocation();
        if (player.isOp() && player.getItemInHand().getType() == Material.STONE_AXE) {
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (!RegionMapData.pos2Map.containsKey(player)) {
                    RegionMapData.pos1Map.put(player, location);
                    player.sendMessage("Pos1을 지정하였습니다. " + "(0)");
                } else {
                    RegionMapData.pos1Map.put(player, location);
                    player.sendMessage("Pos1을 지정하였습니다. " + "(" + new Region(RegionMapData.pos1Map.get(player), RegionMapData.pos2Map.get(player)).getSize() + ")");
                }
            } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (!RegionMapData.pos1Map.containsKey(player)) {
                    RegionMapData.pos2Map.put(player, location);
                    player.sendMessage("Pos2를 지정하였습니다. " + "(0)");
                } else {
                    RegionMapData.pos2Map.put(player, location);
                    player.sendMessage("Pos2를 지정하였습니다. " + "(" + new Region(RegionMapData.pos1Map.get(player), RegionMapData.pos2Map.get(player)).getSize() + ")");
                }
            } else return;

            event.setCancelled(true);
        }
    }
}
