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

import static net.starly.region.RegionMain.config;
import static net.starly.region.data.RegionMapData.pos1Map;
import static net.starly.region.data.RegionMapData.pos2Map;

public class PlayerInteractListener implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        if (!player.isOp() || player.getInventory().getItemInMainHand().getType() != Material.STONE_AXE) return;

        Location location = event.getClickedBlock().getLocation();
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            pos1Map.put(player, location);
            event.setCancelled(true);

            if (pos2Map.containsKey(player)) {
                player.sendMessage(config.getMessage("messages.pos.pos1_with_pos2")
                        .replace("{x}", location.getBlockX() + "")
                        .replace("{y}", location.getBlockY() + "")
                        .replace("{z}", location.getBlockZ() + "")
                        .replace("{2x}", pos2Map.get(player).getBlockX() + "")
                        .replace("{2y}", pos2Map.get(player).getBlockY() + "")
                        .replace("{2z}", pos2Map.get(player).getBlockZ() + "")
                        .replace("{size}", new Region(pos1Map.get(player), pos2Map.get(player)).getSize() + ""));
                return;
            }

            player.sendMessage(config.getMessage("messages.pos.pos1_without_pos2")
                    .replace("{x}", location.getBlockX() + "")
                    .replace("{y}", location.getBlockY() + "")
                    .replace("{z}", location.getBlockZ() + ""));
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            pos2Map.put(player, location);
            event.setCancelled(true);

            if (pos1Map.containsKey(player)) {
                player.sendMessage(config.getMessage("messages.pos.pos2_with_pos1")
                        .replace("{2x}", location.getBlockX() + "")
                        .replace("{2y}", location.getBlockY() + "")
                        .replace("{2z}", location.getBlockZ() + "")
                        .replace("{x}", pos1Map.get(player).getBlockX() + "")
                        .replace("{y}", pos1Map.get(player).getBlockY() + "")
                        .replace("{z}", pos1Map.get(player).getBlockZ() + "")
                        .replace("{size}", new Region(pos1Map.get(player), pos2Map.get(player)).getSize() + ""));
                return;
            }

            player.sendMessage(config.getMessage("messages.pos.pos2_without_pos1")
                    .replace("{2x}", location.getBlockX() + "")
                    .replace("{2y}", location.getBlockY() + "")
                    .replace("{2z}", location.getBlockZ() + ""));
        }
    }
}
