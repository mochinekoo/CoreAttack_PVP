package mochineko.core_attack_pvp.json;

import mochineko.core_attack_pvp.library.DeserializedJson;
import mochineko.core_attack_pvp.manager.ConfigManager;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Map;

public class BlockGuardJson extends DeserializedJson {

    private Map<String, GuardData> guard_data;

    public Map<String, GuardData> getGuardData() {
        return guard_data;
    }

    public static class GuardData {
        private static final ConfigManager configManager = ConfigManager.getInstance();
        private static final World gameWorld = configManager.getGameWorld();

        private int[] start_loc;
        private int[] end_loc;

        public Location getStartLocation() {
            return getStartLocation(gameWorld);
        }

        public Location getStartLocation(World world) {
            return new Location(world, start_loc[0], start_loc[1], start_loc[2]);
        }

        public Location getEndLocation() {
            return getEndLocation(gameWorld);
        }

        public Location getEndLocation(World world) {
            return new Location(world, end_loc[0], end_loc[1], end_loc[2]);
        }

        public boolean isAABB(Location location) {
            Location start = getStartLocation();
            Location end = getEndLocation();
            int minX = Math.min(start.getBlockX(), end.getBlockX());
            int minY = Math.min(start.getBlockY(), end.getBlockY());
            int minZ = Math.min(start.getBlockZ(), end.getBlockZ());
            int maxX = Math.max(start.getBlockX(), end.getBlockX());
            int maxY = Math.max(start.getBlockY(), end.getBlockY());
            int maxZ = Math.max(start.getBlockZ(), end.getBlockZ());
            return location.getX() >= minX && location.getX() <= maxX &&
                    location.getY() >= minY && location.getY() <= maxY &&
                    location.getZ() >= minZ && location.getZ() <= maxZ;
        }
    }
}
