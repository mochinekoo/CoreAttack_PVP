package mochineko.core_attack_pvp.manager;

import mochineko.core_attack_pvp.Main;
import mochineko.core_attack_pvp.library.GameBase;
import mochineko.core_attack_pvp.status.GameStatus;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.Bukkit;
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

        BukkitTask task = new BukkitRunnable() {
            int countTime = 10;
            @Override
            public void run() {
                if (getStatus() == GameStatus.WAITING || getStatus() == GameStatus.COUNTTING) {
                    if (countTime <= 0) {
                        ChatUtil.sendGlobalInfoMessage("ゲーム開始!");
                        setStatus(GameStatus.RUNNING);
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            ScoreboardManager.getInstance(player.getUniqueId()).setScoreboard();
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
                    subtractTime(1);
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);
        setTask(task);
        return 0;
    }

    @Override
    public void resetGame() {

    }
}
