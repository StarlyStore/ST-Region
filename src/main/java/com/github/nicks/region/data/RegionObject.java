package com.github.nicks.region.data;

import com.github.nicks.nicklib.data.Config;
import com.github.nicks.nicklib.data.location.Region;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


import static com.github.nicks.region.data.RegionMapData.regionMap;

public class RegionObject {


    private Player player;
    private Region region = new Region();



    public RegionObject(Player player) {
        this.player = player;
    }


    /**
     * 구역을 생성합니다.
     * @param name 구역이름
     */
    public void createRegion(String name) {

        Config config = new Config("region/" + name);

        if(!config.isFileExist()) {
            config.setPos1(name, getRegion().getPos1());
            config.setPos2(name, getRegion().getPos2());
            config.saveConfig();

            player.sendMessage("§a" + name + "§f라는 이름의 지역을 생성하였습니다.");
        } else {
            player.sendMessage("이미 존재하는 구역입니다.");
        }
    }


    /**
     * 구역을 삭제합니다.
     * @param name 구역이름
     */
    public void deleteRegion(String name) {
        Config config = new Config("region/" + name);

        if(!config.isFileExist()) {
            player.sendMessage("존재하지 않는 구역입니다.");
        } else {
            config.delete();
            player.sendMessage("§a" + name + "§f라는 이름의 지역을 삭제하였습니다.");
        }
    }


    /**
     * 구역 목록을 표시합니다.
     */
    public void listRegion() {
        Config config = new Config("region/");

        for(String list : config.fileListName()) {
            player.sendMessage("§a" + list);
        }
    }


    /**
     * Pos1을 지정합니다.
     */
    public void setPos1(Location location) {
        region.setPos1(location);
        regionMap.put(player, region);
    }


    /**
     * Pos2를 지정합니다.
     */
    public void setPos2(Location location) {
        region.setPos2(location);
        regionMap.put(player, region);
    }


    public void enteredRegion() {
        Config config = new Config("region/");
        for(String list : config.fileListName()) {
            if(config.getPos1(list).getWorld().equals(player.getLocation().getWorld())) {
                if(player.getLocation().getBlockX() >= config.getPos1(list).getBlockX() && player.getLocation().getBlockX() <= config.getPos2(list).getBlockX()) {
                    if(player.getLocation().getBlockY() >= config.getPos1(list).getBlockY() && player.getLocation().getBlockY() <= config.getPos2(list).getBlockY()) {
                        if(player.getLocation().getBlockZ() >= config.getPos1(list).getBlockZ() && player.getLocation().getBlockZ() <= config.getPos2(list).getBlockZ()) {
                            player.sendMessage("§a" + list + "§f구역에 들어왔습니다.");
                        }
                    }
                }
            }
        }
    }




    /**
     * 구역을 가져옵니다.
     * @return 구역
     */
    public Region getRegion() {
        return regionMap.get(player);
    }


    /**
     * Pos1과 Pos2의 직육면체 값을 구합니다.
     * @param pos1
     * @param pos2
     * @return
     */
    public int squareSize(Vector pos1, Vector pos2) {
        Vector min = Vector.getMinimum(pos1, pos2);
        Vector max = Vector.getMaximum(pos1, pos2);
        Vector res = max.subtract(min);

        res.setX(Math.abs(res.getX()));
        res.setY(Math.abs(res.getY()));
        res.setZ(Math.abs(res.getZ()));
        res.add(new Vector(1, 1, 1));
        return res.getBlockX() * res.getBlockY() * res.getBlockZ();
    }
}
