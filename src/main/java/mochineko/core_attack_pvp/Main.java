package mochineko.core_attack_pvp;

import mochineko.core_attack_pvp.command.GameSettingCommand;
import mochineko.core_attack_pvp.command.GameStartCommand;
import mochineko.core_attack_pvp.command.KitCommand;
import mochineko.core_attack_pvp.command.TeamCommand;
import mochineko.core_attack_pvp.listener.*;
import mochineko.core_attack_pvp.listener.kit.BuilderListener;
import mochineko.core_attack_pvp.manager.GameManager;
import mochineko.core_attack_pvp.manager.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        GameManager.getInstance().resetGame();
        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardManager.getInstance(player.getUniqueId()).setScoreboard();
        }

        // Plugin startup logic
        PluginManager plm = getServer().getPluginManager();
        plm.registerEvents(new BlockBreakListener(), this);
        plm.registerEvents(new BlockPlaceListener(), this);
        plm.registerEvents(new PlayerChatListener(), this);
        plm.registerEvents(new PlayerJoinListener(), this);
        plm.registerEvents(new PlayerLeaveListener(), this);
        plm.registerEvents(new PlayerDeadListener(), this);
        plm.registerEvents(new PlayerCraftListener(), this);
        plm.registerEvents(new PlayerDamageListener(), this);
        plm.registerEvents(new PlayerItemDropListener(), this);
        plm.registerEvents(new PlayerInventoryClickListener(), this);

        //kit
        plm.registerEvents(new BuilderListener(), this);

        getCommand("game_start").setExecutor(new GameStartCommand());
        getCommand("game_team").setExecutor(new TeamCommand());
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("set_gametime").setExecutor(new GameSettingCommand());

        //tab
        getCommand("game_team").setTabCompleter(new TeamCommand());
        getCommand("kit").setTabCompleter(new KitCommand());
        getCommand("game_start").setTabCompleter(new GameStartCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
