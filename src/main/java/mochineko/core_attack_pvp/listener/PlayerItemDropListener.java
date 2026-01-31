package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.status.ItemStackProperty;
import mochineko.core_attack_pvp.util.ItemUtil;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerItemDropListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Item dropItem = event.getItemDrop();
        ItemStack item = dropItem.getItemStack();
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        if (meta.getLore() == null) return;
        if (ItemUtil.containsProperty(item, ItemStackProperty.TRANSFERABLE)) {
            dropItem.remove();
        }
    }
}
