package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.manager.ScoreboardManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ScoreboardManager scoreboardManager = ScoreboardManager.getInstance(player.getUniqueId());
        scoreboardManager.setScoreboard();
    }
}
