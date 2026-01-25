package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Arrays;
import java.util.List;

public class PlayerCraftListener implements Listener {

    //全体に影響が出るアイテム
    private final List<Material> NG_RECIPELIST = Arrays.asList(
            Material.TNT, Material.TNT_MINECART
    );

    @EventHandler
    public void onRecipe(CraftItemEvent event) {
        HumanEntity player = event.getWhoClicked();
        Recipe recipe = event.getRecipe();
        ItemStack resultItem = recipe.getResult();
        if (NG_RECIPELIST.contains(resultItem.getType())) {
            ChatUtil.sendErrorMessage(player, "そのアイテムをクラフトすることはできません。");
            event.setCancelled(true);
        }
    }
}
