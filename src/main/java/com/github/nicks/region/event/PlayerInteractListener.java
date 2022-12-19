package com.github.nicks.region.event;

import com.github.nicks.region.data.RegionObject;
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
        Player player = event.getPlayer();

        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        if (event.getClickedBlock() == null) return;

        if (player.isOp()) {

            if (player.getItemInHand().getType() == Material.STONE_AXE) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    RegionObject regionObject = new RegionObject(player);
                    regionObject.setPos1(event.getClickedBlock().getLocation());
                    player.sendMessage("Pos1을 지정하였습니다.");

                } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    RegionObject regionObject = new RegionObject(player);
                    regionObject.setPos2(event.getClickedBlock().getLocation());
                    player.sendMessage("Pos2를 지정하였습니다.");
                }
            }
        }
    }
}
