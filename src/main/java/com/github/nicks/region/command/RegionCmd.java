package com.github.nicks.region.command;

import com.github.nicks.region.data.RegionObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegionCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player) {

            RegionObject regionObject = new RegionObject(player);

            switch (args[0]) {
                case "생성", "create", "제작" -> {
                    if(args.length == 2) {
                        regionObject.createRegion(args[1]);
                    } else {
                        player.sendMessage("§c사용법: /nkregion 생성 <이름>");
                        return true;
                    }

                }

                case "제거", "삭제", "지우기" -> {
                    if(args.length == 2) {
                        regionObject.deleteRegion(args[1]);
                    } else {
                        player.sendMessage("§c사용법: /nkregion 제거 <이름>");
                        return true;
                    }
                }

                case "목록", "list" -> {
                    if(args.length == 1) {
                        regionObject.listRegion();
                    } else {
                        player.sendMessage("§c사용법: /nkregion 목록");
                        return true;
                    }
                }

                case "테스트" -> {
                    regionObject.test();
                    return true;
                }

                default -> {
                    player.sendMessage("§c사용법: /nkregion <생성/제거/목록>");
                    return true;
                }
            }

        }


        return false;
    }
}
