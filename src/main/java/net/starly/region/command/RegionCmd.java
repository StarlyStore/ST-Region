package net.starly.region.command;

import net.starly.core.data.location.Region;
import net.starly.region.data.RegionMapData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegionCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (args.length == 0) return false;

        switch (args[0]) {
            case "생성", "create" -> {
                if (args.length == 2) {
                    if (!RegionMapData.pos1Map.containsKey(player) || !RegionMapData.pos2Map.containsKey(player)) {
                        player.sendMessage("§c좌표를 선택해주세요.");

                        return true;
                    }

                    if (!RegionMapData.regionMap.containsKey(args[1])) {
                        RegionMapData.regionMap.put(args[1], new Region(RegionMapData.pos1Map.get(player), RegionMapData.pos2Map.get(player)));
                        player.sendMessage("§a성공적으로 §f" + args[1] + " §a이름의 구역을 생성했습니다.");

                        return true;
                    }
                    else
                        player.sendMessage("§c이미 존재하는 구역 이름입니다.");

                    return true;
                } else {
                    player.sendMessage("§c사용법: /region 생성 <이름>");

                    return true;
                }

            }

            case "제거", "remove" -> {
                if (args.length == 2) {
                    if (RegionMapData.regionMap.containsKey(args[1])) {
                        RegionMapData.regionMap.remove(args[1]);
                        player.sendMessage("§a성공적으로 구역을 제거했습니다.");

                        return true;
                    } else {
                        player.sendMessage("§c존재하지 않는 구역 이름입니다.");

                        return true;
                    }
                } else {
                    player.sendMessage("§c사용법: /region 제거 <이름>");
                    return true;
                }
            }

            case "목록", "list" -> {
                if (args.length == 1) {
                    StringBuilder sb = new StringBuilder();
                    for (String key : RegionMapData.regionMap.keySet()) {
                        sb
                                .append(key)
                                .append(", ");
                    }

                    player.sendMessage("§a구역 목록: " + sb);

                    return true;
                } else {
                    player.sendMessage("§c사용법: /region 목록");

                    return true;
                }
            }

            default -> {
                player.sendMessage("§c사용법: /region <생성|제거|목록>");
                return true;
            }
        }
    }
}
