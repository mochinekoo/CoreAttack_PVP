package mochineko.coreattack_pvp;

import mochineko.coreattack_pvp.command.GameResetCommand;
import mochineko.coreattack_pvp.command.GameStartCommand;
import mochineko.coreattack_pvp.listener.BlockBreakListener;
import mochineko.coreattack_pvp.listener.PlayerJoinListener;
import mochineko.coreattack_pvp.manager.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("game_start").setExecutor(new GameStartCommand());
        getCommand("game_reset").setExecutor(new GameResetCommand());

        @NotNull PluginManager plm = getServer().getPluginManager();
        plm.registerEvents(new BlockBreakListener(), this);
        plm.registerEvents(new PlayerJoinListener(), this);

        //スコアボード
        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardManager.getInstance(player.getUniqueId()).setScoreboard();
        }

        saveDefaultConfig();

        getLogger().info("プラグインが有効になりました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
