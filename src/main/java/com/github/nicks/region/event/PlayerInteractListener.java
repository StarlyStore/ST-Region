package com.github.nicks.region.event;

import com.github.nicks.nicklib.data.location.Region;
import com.github.nicks.region.data.RegionObject;
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
        RegionObject regionObject = new RegionObject(player);
        Region region = new Region();



        if (player.isOp()) {

            if (player.getItemInHand().getType() == Material.STONE_AXE) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

                    if (regionObject.getRegion2().getPos1() == null || !regionObject.getRegion2().getPos1().equals(location)) {
                        regionObject.setPos1(event.getClickedBlock().getLocation());
                        if (regionObject.getRegion2().getPos2() == null) {
                            player.sendMessage("Pos1을 지정하였습니다. " + "(0)");
                        } else {
                            player.sendMessage("Pos1을 지정하였습니다. " + "(" + region.squareSize(regionObject.getRegion2().getPos1().toVector(), regionObject.getRegion2().getPos2().toVector()) + ")");
                        }
                    }

                } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                    if (regionObject.getRegion2().getPos2() == null || !regionObject.getRegion2().getPos2().equals(location)) {
                        regionObject.setPos2(event.getClickedBlock().getLocation());

                        if (regionObject.getRegion2().getPos1() == null) {
                            player.sendMessage("Pos2를 지정하였습니다. " + "(0)");
                        } else {
                            player.sendMessage("Pos2를 지정하였습니다. " + "(" + region.squareSize(regionObject.getRegion2().getPos1().toVector(), regionObject.getRegion2().getPos2().toVector()) + ")");
                        }
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}
