package mochineko.core_attack_pvp.library;

import mochineko.core_attack_pvp.status.GameStatus;
import org.bukkit.scheduler.BukkitTask;

public abstract class GameBase {

    private int time;
    private GameStatus status;
    private BukkitTask task;
    private boolean isActive;

    public GameBase() {
        this.time = 0;
        this.status = GameStatus.WAITING;
    }

    public abstract int startGame();
    public abstract void resetGame();

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        if (time < 0) time = 0;
        this.time = time;
    }

    public void addTime(int time) {
        if ((this.time + time) < 0) this.time = 0;
        this.time += time;
    }

    public void subtractTime(int time) {
        if ((this.time - time) < 0) this.time = 0;
        this.time -= time;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        if (status == null) return;
        this.status = status;
    }

    public BukkitTask getTask() {
        return task;
    }

    public void setTask(BukkitTask task) {
        if (task == null) return;
        this.task = task;
    }

    /**
     * ゲームが実行中であるかを確認する関数
     * @return bool型で返す（実行中ならtrue、実行されてないならfalse）
     */
    public boolean isGameActive() {
        if (task == null) return false;
        return task.isCancelled();
    }
}
