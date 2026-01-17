package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.manager.ConfigManager;
import mochineko.core_attack_pvp.manager.ScoreboardManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ConfigManager config = ConfigManager.getInstance();
        Location lobby = config.getLobby();
        ScoreboardManager scoreboardManager = ScoreboardManager.getInstance(player.getUniqueId());
        scoreboardManager.setScoreboard();
        if (lobby != null) {
            player.teleport(lobby);
        }
    }
}
