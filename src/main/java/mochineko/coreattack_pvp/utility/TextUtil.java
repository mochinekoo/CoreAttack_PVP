package mochineko.coreattack_pvp.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TextUtil {

    private static final String TEXT_INFO = ChatColor.AQUA + "[CoreAttackPVP] " + ChatColor.RESET;
    private static final String TEXT_ERROR = ChatColor.RED + "[エラー] " + ChatColor.RESET;

    public static void sendInfoMessage(String message) {
        Bukkit.broadcastMessage(TEXT_INFO + message);
    }

    public static void sendErrorMessage(String message) {
        Bukkit.broadcastMessage(TEXT_ERROR + message);
    }
}
