package mochineko.coreattack_pvp;

import mochineko.coreattack_pvp.command.GameResetCommand;
import mochineko.coreattack_pvp.command.GameStartCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("game_start").setExecutor(new GameStartCommand());
        getCommand("game_reset").setExecutor(new GameResetCommand());

        getLogger().info("プラグインが有効になりました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
