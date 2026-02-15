package mochineko.core_attack_pvp.library;

import mochineko.core_attack_pvp.Main;
import mochineko.core_attack_pvp.status.ItemStackProperty;
import mochineko.core_attack_pvp.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class KitBase {

    private final String kit_name;
    private final OfflinePlayer player;
    private Map<Material, Integer> coolDownMap;
    private Map<Material, Boolean> canUseItemMap;

    public KitBase(String kit_name, OfflinePlayer player) {
        this.kit_name = kit_name;
        this.player = player;
        this.coolDownMap = new HashMap<>();
        this.canUseItemMap = new HashMap<>();
    }

    public String getName() {
        return kit_name;
    }

    public OfflinePlayer getOfflinePlayer() {
        return player;
    }

    public Player getPlayer() {
        return player.getPlayer();
    }

    public abstract List<ItemStack> getKitItems();

    public int getCoolDown(Material material) {
        if (getKitItems().stream().anyMatch(item -> item.getType() == material)) {
            if (!coolDownMap.containsKey(material)) {
                coolDownMap.put(material, 0);
            }
            return coolDownMap.get(material);
        }
        return -1;
    }

    public void setCoolDown(Material material, int coolDown) {
        coolDownMap.put(material, coolDown);
    }

    public boolean canUseItem(Material material) {
        if (getKitItems().stream().anyMatch(item -> item.getType() == material)) {
            if (!canUseItemMap.containsKey(material)) {
                canUseItemMap.put(material, true);
            }
            return canUseItemMap.get(material);
        }
        return false;
    }

    public void setCanUseItem(Material material, boolean canUse) {
        canUseItemMap.put(material, canUse);
    }

    public void giveKitItem() {
        getPlayer().getInventory().addItem(getKitItems().toArray(new ItemStack[0]));
    }

    public ItemStack findKitItem(ItemStack item) {
        for (ItemStack playerItem : getPlayer().getInventory().getContents()) {
            if (playerItem == null) continue;
            if (item.getType() != playerItem.getType()) continue;
            if (!ItemUtil.containsProperty(playerItem, ItemStackProperty.KIT_ITEM)) continue;
            if (playerItem.getType() == item.getType()) {
                return playerItem;
            }
        }
        return null;
    }
}
