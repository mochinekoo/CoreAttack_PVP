package mochineko.core_attack_pvp.util;

import mochineko.core_attack_pvp.library.KitBase;
import mochineko.core_attack_pvp.manager.KitManager;
import mochineko.core_attack_pvp.manager.TeamManager;
import mochineko.core_attack_pvp.status.GameTeam;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BukkitUtil {

    /**
     * ゲーム標準のリストネームに変える関数
     * @param player
     */
    public static void updateListName(Player player) {
        if (!player.isOnline()) return;
        KitManager kitManager = KitManager.getInstance();
        TeamManager teamManager = TeamManager.getInstance();
        KitBase kit = kitManager.getKit(player.getUniqueId());
        GameTeam team = teamManager.getJoinGameTeam(player);
        if (team == null) return;
        if (kit == null) return;
        if (kit.getName().equalsIgnoreCase("DefaultKit")) return;
        //例：[Builder] mochi__neko
        player.setPlayerListName(ChatColor.GRAY + "[" + kit.getName() + "]" + ChatColor.RESET + team.getColor() + player.getName());
    }

    /**
     * リストネームをリセットする関数
     */
    public static void resetListName(Player player) {
        if (!player.isOnline()) return;
        //例：mochi__neko
        player.setPlayerListName(ChatColor.RESET + player.getName());
    }
}
