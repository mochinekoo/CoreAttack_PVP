package mochineko.core_attack_pvp.manager;

import mochineko.core_attack_pvp.Main;
import mochineko.core_attack_pvp.library.GameBase;
import mochineko.core_attack_pvp.library.KitBase;
import mochineko.core_attack_pvp.status.GameStatus;
import mochineko.core_attack_pvp.status.GameTeam;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GameManager extends GameBase {

    private static GameManager instance;

    private GameManager() {}

    public static GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }

    @Override
    public int startGame() {
        if (isGameActive()) return -1;
        if (getStatus() != GameStatus.WAITING) return -1;
        KitManager kitManager = KitManager.getInstance();

        BukkitTask task = new BukkitRunnable() {
            int countTime = 10;
            @Override
            public void run() {
                if (getStatus() == GameStatus.WAITING || getStatus() == GameStatus.COUNTTING) {
                    if (countTime <= 0) {
                        ChatUtil.sendGlobalInfoMessage("ゲーム開始!");
                        setStatus(GameStatus.RUNNING);
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.getInventory().clear();
                            ScoreboardManager.getInstance(player.getUniqueId()).setScoreboard();
                            TeamManager.getInstance().assignTeam();
                            KitBase kit = kitManager.getKit(player.getUniqueId());
                            if (kit != null) {
                                kit.giveKitItem();
                            }
                            GameTeam team = TeamManager.getInstance().getJoinGameTeam(player);
                            if (team != null) {
                                Location teamSpawn = ConfigManager.getInstance().getTeamSpawnLocation(team);
                                player.teleport(teamSpawn);
                            }

                        }
                    }
                    else {
                        String message = String.format("ゲームを開始まであと%d秒", countTime);
                        ChatUtil.sendGlobalInfoMessage(message);
                        countTime--;
                        setStatus(GameStatus.COUNTTING);
                    }
                }
                else if (getStatus() == GameStatus.RUNNING) {
                    if (getTime() <= 0) {
                        this.cancel();
                        ChatUtil.sendGlobalInfoMessage("ゲーム終了!");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                setStatus(GameStatus.ENDING);
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    ScoreboardManager.getInstance(player.getUniqueId()).setScoreboard();
                                    player.teleport(ConfigManager.getInstance().getLobby());
                                }
                            }
                        }.runTaskLater(Main.getPlugin(Main.class), 20L*3);
                    }
                    subtractTime(1);
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);
        setTask(task);
        return 0;
    }

    @Override
    public void resetGame() {
        setTime(60*30);
    }
}
