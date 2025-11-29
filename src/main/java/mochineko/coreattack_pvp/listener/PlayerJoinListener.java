package mochineko.coreattack_pvp.listener;

import mochineko.coreattack_pvp.Main;
import mochineko.coreattack_pvp.manager.GameManager;
import mochineko.coreattack_pvp.manager.ScoreboardManager;
import mochineko.coreattack_pvp.manager.TeamManager;
import mochineko.coreattack_pvp.status.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        @NotNull Player player = event.getPlayer();
        @NotNull UUID uuid = player.getUniqueId();
        ScoreboardManager scoreboardManager = ScoreboardManager.getInstance(uuid);
        GameManager gameManager = GameManager.getInstance();
        TeamManager teamManager = TeamManager.getInstance();

        if (gameManager.getStatus() == GameStatus.RUNNING) {
            teamManager.randomJoin(player);
        }
        else if (gameManager.getStatus() == GameStatus.WAITING || gameManager.getStatus() == GameStatus.COUNTING){

        }

        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), scoreboardManager::setScoreboard, 20L);

    }

}
