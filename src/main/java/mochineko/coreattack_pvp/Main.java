package mochineko.coreattack_pvp;

import mochineko.coreattack_pvp.command.GameResetCommand;
import mochineko.coreattack_pvp.command.GameStartCommand;
import mochineko.coreattack_pvp.manager.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("game_start").setExecutor(new GameStartCommand());
        getCommand("game_reset").setExecutor(new GameResetCommand());

        //スコアボード
        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardManager.getInstance(player.getUniqueId()).setScoreboard();
        }

        getLogger().info("プラグインが有効になりました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
