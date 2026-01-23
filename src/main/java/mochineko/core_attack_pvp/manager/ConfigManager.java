package mochineko.core_attack_pvp.manager;

import mochineko.core_attack_pvp.Main;
import mochineko.core_attack_pvp.status.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

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

    public Location getTeamSpawnLocation(GameTeam team) {
        //パス例：location.spawn.red.x
        String team_path = "location.spawn." + team.name().toLowerCase(Locale.ROOT);
        int x = configuration.getInt(team_path + ".x");
        int y = configuration.getInt(team_path + ".y");
        int z = configuration.getInt(team_path + ".z");
        return new Location(getGameWorld(), x, y, z);
    }

    public Location getTeamCoreLocation(GameTeam team) {
        //パス例：location.core.red.x
        String team_path = "location.core." + team.name().toLowerCase(Locale.ROOT);
        int x = configuration.getInt(team_path + ".x");
        int y = configuration.getInt(team_path + ".y");
        int z = configuration.getInt(team_path + ".z");
        return new Location(getGameWorld(), x, y, z);
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

}
