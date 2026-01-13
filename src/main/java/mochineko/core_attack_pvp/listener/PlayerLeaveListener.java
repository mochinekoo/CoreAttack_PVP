package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.manager.ScoreboardManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ScoreboardManager.removeInstance(player.getUniqueId());
    }
}
