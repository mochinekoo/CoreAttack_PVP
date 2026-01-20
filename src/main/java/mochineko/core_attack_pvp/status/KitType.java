package mochineko.core_attack_pvp.status;

import mochineko.core_attack_pvp.kit.Builder;
import mochineko.core_attack_pvp.kit.DefaultKit;
import mochineko.core_attack_pvp.library.KitBase;
import org.bukkit.OfflinePlayer;

public enum KitType {
    DEFAULT(DefaultKit.class),
    BUILDER(Builder.class);

    private Class<? extends KitBase> kit_class;

    KitType(Class<? extends KitBase> kit_class ) {
        this.kit_class = kit_class;
    }

    public KitBase newInstance(OfflinePlayer player) {
        try {
            return kit_class.getDeclaredConstructor(OfflinePlayer.class).newInstance(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
