package mochineko.coreattack_pvp.manager;

import mochineko.coreattack_pvp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ConfigManager {

    //インスタンス
    private static ConfigManager instance;
    private final Main plugin;
    private final FileConfiguration configuration;

    private ConfigManager() {
        this.plugin = Main.getPlugin(Main.class);
        this.configuration = plugin.getConfig();
    }

    public World getGameWorld() {
        String world_name = configuration.getString("location.world_name");
        return Bukkit.getWorld(world_name);
    }

    public Location getLobby() {
        int x = configuration.getInt("location.lobby.x");
        int y = configuration.getInt("location.lobby.y");
        int z = configuration.getInt("location.lobby.z");
        return new Location(getGameWorld(), x, y, z);
    }

    public Location getTeamSpawnLocation(TeamManager.GameTeam team) {
        String team_path = "location.team." + team.name().toLowerCase(Locale.ROOT);
        int x = configuration.getInt(team_path + ".spawn.x");
        int y = configuration.getInt(team_path + ".spawn.y");
        int z = configuration.getInt(team_path + ".spawn.z");
        return new Location(getGameWorld(), x, y, z);
    }

    public Location getTeamCoreLocation(TeamManager.GameTeam team) {
        String team_path = "location.team." + team.name().toLowerCase(Locale.ROOT);
        int x = configuration.getInt(team_path + ".core.x");
        int y = configuration.getInt(team_path + ".core.y");
        int z = configuration.getInt(team_path + ".core.z");
        return new Location(getGameWorld(), x, y, z);
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
}
