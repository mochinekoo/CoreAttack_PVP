package mochineko.core_attack_pvp.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static final String TEXT_INFO = ChatColor.AQUA + "[Game] " + ChatColor.RESET;
    public static final String TEXT_ERROR = ChatColor.RED + "[エラー] " + ChatColor.RESET;

    public static void sendGlobalInfoMessage(String message) {
        Bukkit.broadcastMessage(TEXT_INFO + message);
    }

    public static void sendGlobalErrorMessage(String message) {
        Bukkit.broadcastMessage(TEXT_ERROR + message);
    }

    public static void sendInfoMessage(CommandSender sender, String message) {
        sender.sendMessage(TEXT_INFO + message);
    }

    public static void sendErrorMessage(CommandSender sender, String message) {
        sender.sendMessage(TEXT_ERROR + message);
    }
}
