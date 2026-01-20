package mochineko.core_attack_pvp.command;

import mochineko.core_attack_pvp.manager.KitManager;
import mochineko.core_attack_pvp.status.KitType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kit")) {
            KitManager kitManager = KitManager.getInstance();
            if (args[0].equalsIgnoreCase("select")) {
                kitManager.setKit(Bukkit.getOfflinePlayer(args[1]).getUniqueId(), KitType.valueOf(args[2]));
            }
            else if (args[0].equalsIgnoreCase("unselect")) {
            }
            else if (args[0].equalsIgnoreCase("list")) {
            }
        }
        return false;
    }
}
