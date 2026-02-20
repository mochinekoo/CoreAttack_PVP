package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.manager.TeamManager;
import mochineko.core_attack_pvp.status.GameTeam;
import mochineko.core_attack_pvp.status.ItemStackProperty;
import mochineko.core_attack_pvp.util.ChatUtil;
import mochineko.core_attack_pvp.util.ItemUtil;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        TeamManager teamManager = TeamManager.getInstance();
        HumanEntity player = event.getWhoClicked();
        ItemStack hasItem = event.getCurrentItem();
        ItemStack slotItem = event.getCursor();

        if (teamManager.getJoinGameTeam((Player) player) == GameTeam.ADMIN) return; //運営なら自由にアイテムを移動できるように
        boolean isCancel = false;
        if (hasItem != null) {
            if (ItemUtil.containsProperty(hasItem, ItemStackProperty.TRANSFERABLE)) {
                isCancel = true;
            }
        }
        if (slotItem != null) {
            if (ItemUtil.containsProperty(slotItem, ItemStackProperty.TRANSFERABLE)) {
                isCancel = true;
            }
        }

        event.setCancelled(isCancel);
        if (isCancel) {
            ChatUtil.sendInfoMessage(player, "そのアイテムを移動することはできません。");
        }
    }
}
