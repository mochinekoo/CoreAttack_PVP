package mochineko.coreattack_pvp.manager;

import mochineko.coreattack_pvp.Main;
import mochineko.coreattack_pvp.status.GameStatus;
import mochineko.coreattack_pvp.utility.TextUtil;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GameManager {

    //インスタンス
    private static GameManager manager;

    //ゲーム
    private GameStatus status;
    private BukkitTask task;
    private int time;

    private GameManager() {
        this.status = GameStatus.WAITING;
        this.time = 60*30;
    }

    public void startGame() {
        if (task == null) {
            this.task = new BukkitRunnable() {
                int countTime = 10;
                @Override
                public void run() {
                    if (status == GameStatus.WAITING || status == GameStatus.COUNTING) {
                        if (countTime > 0) {
                            TextUtil.sendInfoMessage("ゲーム開始まであと" + countTime + "秒");
                            countTime--;
                        }
                        else {
                            TextUtil.sendInfoMessage("ゲーム開始!!");
                            status = GameStatus.RUNNING;
                        }
                    }
                    else if (status == GameStatus.RUNNING) {

                    }
                }
            }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);
        }
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public static GameManager getInstance() {
        if (manager == null) {
            manager = new GameManager();
        }
        return manager;
    }

}
