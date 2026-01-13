package mochineko.core_attack_pvp.manager;

import mochineko.core_attack_pvp.Main;
import mochineko.core_attack_pvp.status.GameStatus;
import mochineko.core_attack_pvp.status.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ScoreboardManager {

    private static final Map<UUID, ScoreboardManager> board_map = new HashMap<>();
    private static final String SCOREBOARD_NAME = "Game";
    private static final String DISPLAY_NAME = ChatColor.AQUA + "Game";

    //スコアボード
    private final GameManager manager;
    private final OfflinePlayer player;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private BukkitTask task;
    private Map<Integer, Score> score_map;

    private ScoreboardManager(UUID uuid) {
        this.manager = GameManager.getInstance();
        this.player = Bukkit.getOfflinePlayer(uuid);
        Scoreboard new_scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = new_scoreboard.registerNewObjective(SCOREBOARD_NAME, "dummy", DISPLAY_NAME);
        this.scoreboard = this.objective.getScoreboard();
        this.score_map = new HashMap<>();

        board_map.put(uuid, this);
    }

    /**
     * スコアボードインスタンスを取得する関数
     * @param uuid スコアボードを取得したいプレイヤー
     */
    public static ScoreboardManager getInstance(UUID uuid) {
        if (!board_map.containsKey(uuid)) {
            board_map.put(uuid, new ScoreboardManager(uuid));
        }

        return board_map.get(uuid);
    }

    /**
     * スコアを取得する関数
     * @param score スコアを取得したい場所
     */
    public Score getScore(int score) {
        if (!score_map.containsKey(score)) {
            score_map.put(score, new Score(getObjective(), null, score));
        }

        return score_map.get(score);
    }

    /**
     * スコアボードを設定する関数
     */
    public void setScoreboard() {
        Objective player_obj = getObjective();
        resetScore();
        player_obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        if (manager.getStatus() == GameStatus.WAITING) {
            //ゲーム開始前
            player_obj.getScore("===============").setScore(30);
            player_obj.getScore(" ").setScore(29);
            player_obj.getScore( ChatColor.GOLD + "ゲーム開始待機中...").setScore(28);
            player_obj.getScore("  ").setScore(27);
            // game_obj.getScore("現在の人数：" + ).setScore(26);
            player_obj.getScore("   ").setScore(25);
            player_obj.getScore("============").setScore(24);
        }
        else if (manager.getStatus() == GameStatus.RUNNING) {
            //ゲーム中
            player_obj.getScore("===============").setScore(30);
            player_obj.getScore("  ").setScore(29);
            //player_obj.getScore(ChatColor.RED + "赤：" + ChatColor.GOLD + "").setScore(28);
            //player_obj.getScore(ChatColor.BLUE + "青：" + ChatColor.GOLD + "").setScore(27);
            player_obj.getScore(" ").setScore(26);
            player_obj.getScore("============").setScore(25);
        }

        updateScoreboard();
        Bukkit.getLogger().info("[ScoreboardManager] スコアボードを設定しました。（type=" + manager.getStatus().name() + ")");
    }

    /**
     * スコアボードを更新する関数
     * @apiNote 手動で呼び出す必要なし
     */
    private void updateScoreboard() {
        if (task == null) {
            this.task = new BukkitRunnable() {
                CoreManager coreManager = CoreManager.getInstance();
                @Override
                public void run() {
                    if (manager.getStatus() == GameStatus.WAITING) {
                        getScore(26).updateScore(ChatColor.GOLD + "現在の人数：" + Bukkit.getOnlinePlayers().size());
                    }
                    else if (manager.getStatus() == GameStatus.RUNNING) {
                        getScore(28).updateScore(ChatColor.RED + "赤：" + ChatColor.GOLD + coreManager.getCore(GameTeam.RED));
                        getScore(27).updateScore(ChatColor.BLUE + "青：" + ChatColor.GOLD + coreManager.getCore(GameTeam.BLUE));
                    }

                    //スコアボードセット
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    player.getPlayer().setScoreboard(scoreboard);

                }
            }.runTaskTimer(Main.getPlugin(Main.class), 0L, 2L);
        }
    }

    /**
     * スコアボードをリセットする関数
     * @apiNote 手動で呼び出す必要なし
     */
    public void resetScore() {
        Set<String> scores = getScoreboard().getEntries();
        for (String score : scores) {
            getScoreboard().resetScores(score);
        }
    }

    public Objective getObjective() {
        return objective;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public OfflinePlayer getOfflinePlayer() {
        return player;
    }

    public Player getPlayer() {
        return player.getPlayer();
    }

    public BukkitTask getTask() {
        return task;
    }

    public GameManager getManager() {
        return manager;
    }

    public static class Score {
        private String name = null;
        private final int score;
        private final Objective objective;

        public Score(Objective objective, String name, int score) {
            this.name = name;
            this.score = score;
            this.objective = objective;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public Objective getObjective() {
            return objective;
        }

        public void updateScore(String name) {
            if (this.name != null) {
                objective.getScoreboard().resetScores(this.name);
            }
            this.name = name;
            objective.getScore(name).setScore(score);
        }
    }

}

