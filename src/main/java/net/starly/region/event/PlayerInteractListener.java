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

import static net.starly.region.RegionMain.prefix;

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
                    player.sendMessage(prefix + "§f첫번째 구역을 지정하였습니다. " + "§7[" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + "]" + " §7(§c0§7)");
                } else {
                    RegionMapData.pos1Map.put(player, location);
                    player.sendMessage(prefix + "§f첫번째 구역을 지정하였습니다. " + "§7[" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + "]" + " §7(§c" + new Region(RegionMapData.pos1Map.get(player), RegionMapData.pos2Map.get(player)).getSize() + "§7)");
                }
            } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (!RegionMapData.pos1Map.containsKey(player)) {
                    RegionMapData.pos2Map.put(player, location);
                    player.sendMessage(prefix + "§f두번째 구역을 지정하였습니다. " + "§7[" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + "]" + " §7(§c0§7)");
                } else {
                    RegionMapData.pos2Map.put(player, location);
                    player.sendMessage(prefix + "§f두번째 구역을 지정하였습니다. " + "§7[" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + "]" + " §7(§c" + new Region(RegionMapData.pos1Map.get(player), RegionMapData.pos2Map.get(player)).getSize() + "§7)");
                }
            } else return;

            event.setCancelled(true);
        }
    }
}
