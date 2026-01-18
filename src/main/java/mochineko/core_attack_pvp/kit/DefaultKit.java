package mochineko.core_attack_pvp.kit;

import mochineko.core_attack_pvp.library.KitBase;
import mochineko.core_attack_pvp.status.ItemStackProperty;
import mochineko.core_attack_pvp.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DefaultKit extends KitBase {

    public DefaultKit(OfflinePlayer player) {
        super("DefaultKit", player, 0);
    }

    @Override
    public List<ItemStack> getKitItems() {
        return List.of(
                new ItemUtil(Material.WOODEN_AXE).setProperty(ItemStackProperty.TRANSFERABLE).buildItemStack(),
                new ItemUtil(Material.WOODEN_PICKAXE).setProperty(ItemStackProperty.TRANSFERABLE).buildItemStack(),
                new ItemUtil(Material.WOODEN_SHOVEL).setProperty(ItemStackProperty.TRANSFERABLE).buildItemStack()
        );
    }

}
