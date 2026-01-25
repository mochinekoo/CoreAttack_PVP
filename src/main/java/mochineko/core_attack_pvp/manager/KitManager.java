package mochineko.core_attack_pvp.manager;

import mochineko.core_attack_pvp.library.KitBase;
import mochineko.core_attack_pvp.status.KitType;
import mochineko.core_attack_pvp.util.BukkitUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KitManager {

    private static KitManager instance;
    private static Map<UUID, KitBase> kit_map = new HashMap<>();

    private KitManager() {}

    public static KitManager getInstance() {
        if (instance == null) instance = new KitManager();
        return instance;
    }

    public void setKit(UUID uuid, KitBase kit) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        if (player.isOnline()) {
            BukkitUtil.updateListName(player.getPlayer());
            kit_map.put(uuid, kit);
        }
    }

    public void setKit(UUID uuid, KitType type) {
        setKit(uuid, type.newInstance(Bukkit.getOfflinePlayer(uuid)));
    }

    public KitBase getKit(UUID uuid) {
        return kit_map.get(uuid);
    }
}
