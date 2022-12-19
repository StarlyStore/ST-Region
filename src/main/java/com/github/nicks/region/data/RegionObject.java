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
            region.saveRegion(name, getRegion().getPos1(), getRegion().getPos2());

            player.sendMessage("§a" + name + "§f라는 이름의 지역을 생성하였습니다.");
        } else {
            player.sendMessage("이미 존재하는 구역입니다.");
        }
    }

    public void test() {
        player.sendMessage(getRegion2().getPos1().toString());
        player.sendMessage(getRegion2().getPos2().toString());
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


    public void setPos1(Location location) {
        Region region = getRegion2();
        region.setPos1(location);
    }

    public void setPos2(Location location) {
        Region region = getRegion2();
        region.setPos2(location);
    }




    /**
     * 구역을 가져옵니다.
     * @return 구역
     */
    public Region getRegion() {
        return regionMap.get(player);
    }

    public Region getRegion2() {
        if(regionMap.get(player) == null) {
            Region region = new Region();
            regionMap.put(player, region);
            return region;
        } else {
            Region region = regionMap.get(player);
            return region;
        }
    }

}
