package net.starly.region.command;

import net.starly.core.data.location.Region;
import net.starly.region.data.RegionMapData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.starly.region.RegionMain.config;
import static net.starly.region.data.RegionMapData.pos1Map;
import static net.starly.region.data.RegionMapData.pos2Map;

public class RegionCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        
        if (args.length == 0) {
            player.sendMessage(config.getMessage("messages.wrongCommand"));
            return true;
        }

        switch (args[0]) {
            case "도움말":
            case "help":
            case "?": {
                if (!player.hasPermission("starly.region.help")) {
                    player.sendMessage(config.getMessage("messages.noPermission"));
                    return true;
                }

                player.sendMessage(config.getMessage("messages.help"));
                return true;
            }

            case "생성":
            case "create": {
                if (!player.hasPermission("starly.region.create")) {
                    player.sendMessage(config.getMessage("messages.noPermission"));
                    return true;
                }

                if (args.length != 2) {
                    player.sendMessage(config.getMessage("messages.wrongCommand"));
                    return true;
                }

                if (!(pos1Map.containsKey(player) && pos2Map.containsKey(player))) {
                    player.sendMessage(config.getMessage("messages.create.noPosSet"));

                    return true;
                }
                if (RegionMapData.regionMap.containsKey(args[1])) {
                    player.sendMessage(config.getMessage("messages.create.alreadyExists")
                            .replace("{name}", args[1]));
                    return true;
                }

                RegionMapData.regionMap.put(args[1], new Region(pos1Map.get(player), pos2Map.get(player)));
                player.sendMessage(config.getMessage("messages.create.success")
                        .replace("{name}", args[1]));

                return true;
            }

            case "제거":
            case "remove": {
                if (!player.hasPermission("starly.region.remove")) {
                    player.sendMessage(config.getMessage("messages.noPermission"));
                    return true;
                }

                if (args.length != 2) {
                    player.sendMessage(config.getMessage("messages.wrongCommand"));
                    return true;
                }

                if (!RegionMapData.regionMap.containsKey(args[1])) {
                    player.sendMessage(config.getMessage("messages.remove.notExists")
                            .replace("{name}", args[1]));
                    return true;
                }

                RegionMapData.regionMap.remove(args[1]);
                player.sendMessage(config.getMessage("messages.remove.success")
                        .replace("{name}", args[1]));
                return true;
            }

            case "목록":
            case "list": {
                if (!player.hasPermission("starly.region.list")) {
                    player.sendMessage(config.getMessage("messages.noPermission"));
                    return true;
                }

                if (args.length != 1) {
                    player.sendMessage(config.getMessage("messages.wrongCommand"));
                    return true;
                }

                StringBuilder sb = new StringBuilder();
                for (String key : RegionMapData.regionMap.keySet()) {
                    sb.append(key).append(ChatColor.translateAlternateColorCodes('&', config.getString("messages.list.separator")));
                }
                sb.delete(sb.length() - (config.getString("messages.list.separator").length() + 1), sb.length() - 1);

                config.getMessages("messages.list").forEach(line -> player.sendMessage(line
                        .replace("{list}", sb.toString())));

                return true;
            }

            default: {
                player.sendMessage(config.getMessage("messages.wrongCommand"));
                return true;
            }
        }
    }
}
