package mochineko.core_attack_pvp.command;

import mochineko.core_attack_pvp.manager.KitManager;
import mochineko.core_attack_pvp.status.KitType;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KitCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kit")) {
            KitManager kitManager = KitManager.getInstance();
            if (args[0].equalsIgnoreCase("select")) {
                kitManager.setKit(Bukkit.getOfflinePlayer(args[1]).getUniqueId(), KitType.valueOf(args[2]));
                ChatUtil.sendInfoMessage(send, "キットを変更しました。");
            }
            else if (args[0].equalsIgnoreCase("unselect")) {
            }
            else if (args[0].equalsIgnoreCase("list")) {
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kit")) {
            if (args.length == 1) {
                return Arrays.asList("select", "unselect", "list");
            }

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("select")) {
                    return Bukkit.getOnlinePlayers()
                            .stream()
                            .map(Player::getName)
                            .filter(filter -> filter.startsWith(args[1]))
                            .collect(Collectors.toList());
                }
            }

            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("select")) {
                    return Arrays.stream(KitType.values())
                            .map(Enum::name)
                            .filter(filter -> filter.startsWith(args[2]))
                            .collect(Collectors.toList());
                }
            }
        }
        return Collections.emptyList();
    }

}
