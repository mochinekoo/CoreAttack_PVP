package mochineko.core_attack_pvp;

import mochineko.core_attack_pvp.command.GameStartCommand;
import mochineko.core_attack_pvp.command.TeamCommand;
import mochineko.core_attack_pvp.listener.BlockBreakListener;
import mochineko.core_attack_pvp.listener.PlayerChatListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Plugin startup logic
        PluginManager plm = getServer().getPluginManager();
        plm.registerEvents(new BlockBreakListener(), this);
        plm.registerEvents(new PlayerChatListener(), this);

        getCommand("game_start").setExecutor(new GameStartCommand());
        getCommand("game_team").setExecutor(new TeamCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
