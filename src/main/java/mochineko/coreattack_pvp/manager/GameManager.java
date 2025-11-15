package mochineko.coreattack_pvp.manager;

import mochineko.coreattack_pvp.status.GameStatus;

public class GameManager {

    //インスタンス
    private static GameManager manager;

    //ゲーム
    private GameStatus status;
    private int time;

    private GameManager() {
        this.status = GameStatus.WAITING;
        this.time = 60*30;
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
