package mochineko.core_attack_pvp.listener.kit;

import mochineko.core_attack_pvp.kit.Builder;
import mochineko.core_attack_pvp.library.KitBase;
import mochineko.core_attack_pvp.manager.KitManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BuilderListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        KitManager kitManager = KitManager.getInstance();
        KitBase kit = kitManager.getKit(player.getUniqueId());
        if (item == null) return;
        if (item.getType() != Material.BOOK) return;
        if (kit == null) return;
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Builder builder = (Builder) kit;
            builder.useKitItem();
        }
    }
}
