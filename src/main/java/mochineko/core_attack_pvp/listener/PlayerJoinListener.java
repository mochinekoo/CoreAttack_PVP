package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.library.KitBase;
import mochineko.core_attack_pvp.manager.ConfigManager;
import mochineko.core_attack_pvp.manager.KitManager;
import mochineko.core_attack_pvp.manager.ScoreboardManager;
import mochineko.core_attack_pvp.util.BukkitUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        KitManager kitManager = KitManager.getInstance();
        KitBase kit = kitManager.getKit(player.getUniqueId());
        ConfigManager config = ConfigManager.getInstance();
        Location lobby = config.getLobby();
        ScoreboardManager scoreboardManager = ScoreboardManager.getInstance(player.getUniqueId());
        scoreboardManager.setScoreboard();
        if (kit != null) {
            BukkitUtil.updateListName(player);
        }
        if (lobby != null) {
            player.teleport(lobby);
        }
    }
}
