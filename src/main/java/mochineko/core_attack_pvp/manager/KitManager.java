package mochineko.core_attack_pvp.manager;

import mochineko.core_attack_pvp.library.KitBase;

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
        kit_map.put(uuid, kit);
    }

    public KitBase getKit(UUID uuid) {
        return kit_map.get(uuid);
    }
}
