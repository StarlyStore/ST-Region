package net.starly.region.command;

import net.starly.region.data.RegionMapData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegionCmdTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) return List.of("생성", "제거", "목록");
        else if (args.length == 2) {
            if (List.of("제거", "remove").contains(args[0].toLowerCase())) return new ArrayList<>(RegionMapData.regionMap.keySet());
        }

        return Collections.emptyList();
    }
}
