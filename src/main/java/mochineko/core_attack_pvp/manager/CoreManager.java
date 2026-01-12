package mochineko.core_attack_pvp.manager;

import mochineko.core_attack_pvp.status.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.HashMap;
import java.util.Map;

public class CoreManager {

    private static CoreManager instance;
    private final Map<GameTeam, Integer> map = new HashMap<>();

    public static final int MAX_CORE = 100;
    public static final BlockData CORE_BLOCK = Bukkit.createBlockData(Material.END_STONE);

    private CoreManager() {
        for (GameTeam team : GameTeam.values()) {
            map.put(team, MAX_CORE);
        }
    }

    public static CoreManager getInstance() {
        if (instance == null) instance = new CoreManager();
        return instance;
    }

    public void setCore(GameTeam team, int core) {
        if (core >= 0) {
            map.put(team, core);
        }
    }

    public void addCore(GameTeam team, int core) {
        map.put(team, map.get(team) + core);
    }

    public void subtractCore(GameTeam team, int core) {
        int result = map.get(team) - core;
        if (result >= 0) {
            map.put(team, result);
        }
    }

    public int getCore(GameTeam team) {
        return map.get(team);
    }

    public GameTeam getBreakCoreTeam(Location location) {
        ConfigManager config = ConfigManager.getInstance();
        for (GameTeam team : GameTeam.values()) {
            Location core_location = config.getTeamCoreLocation(team);
            if (core_location.getBlockX() == location.getBlockX() &&
                    core_location.getBlockY() == location.getBlockY() &&
                    core_location.getBlockZ() == location.getBlockZ()) { //equalメゾットだとできないため、演算子で
                return team;
            }
        }
        return null;
    }
}
