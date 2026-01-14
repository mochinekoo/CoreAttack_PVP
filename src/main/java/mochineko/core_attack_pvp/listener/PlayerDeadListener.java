package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.Main;
import mochineko.core_attack_pvp.manager.ConfigManager;
import mochineko.core_attack_pvp.manager.GameManager;
import mochineko.core_attack_pvp.manager.TeamManager;
import mochineko.core_attack_pvp.status.GameStatus;
import mochineko.core_attack_pvp.status.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeadListener implements Listener {

    @EventHandler
    public void onDead(PlayerDeathEvent event) {
        Player player = event.getEntity();
        TeamManager teamManager = TeamManager.getInstance();
        GameManager gameManager = GameManager.getInstance();
        ConfigManager configManager = ConfigManager.getInstance();
        GameTeam team = teamManager.getJoinGameTeam(player);
        if (gameManager.getStatus() != GameStatus.RUNNING) return;
        if (team != null) {
            Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), () -> {player.spigot().respawn();});
        }
    }
}
