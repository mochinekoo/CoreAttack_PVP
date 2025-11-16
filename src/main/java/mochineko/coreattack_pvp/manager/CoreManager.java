package mochineko.coreattack_pvp.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoreManager {

    //定数
    private static final Material CORE_BLOCK = Material.LEGACY_ENDER_STONE;
    private static final ConfigManager configManager = ConfigManager.getInstance();

    //インスタンス
    private static CoreManager instance;

    //コア
    private Map<TeamManager.GameTeam, Integer> core_map;

    public CoreManager() {
        this.core_map = new HashMap<>();

        //初期化
        for (TeamManager.GameTeam team : TeamManager.GameTeam.values()) {
            core_map.put(team, 100);
        }
    }

    public Integer getCore(TeamManager.GameTeam team) {
        return core_map.get(team);
    }

    public void addCore(TeamManager.GameTeam team, Integer add) {
        core_map.put(team, getCore(team) + add);
    }

    public void subtractCore(TeamManager.GameTeam team, Integer subtract) {
        core_map.put(team, getCore(team) - subtract);
    }

    public TeamManager.GameTeam getLocationCore(Location location) {
        return Arrays.stream(TeamManager.GameTeam.values())
                .filter(team -> configManager.getTeamCoreLocation(team).equals(location))
                .findFirst()
                .orElse(null);
    }

    public static CoreManager getInstance() {
        if (instance == null) {
            instance = new CoreManager();
        }
        return instance;
    }

}
