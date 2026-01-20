package mochineko.core_attack_pvp.util;

import mochineko.core_attack_pvp.status.ItemStackProperty;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtil {

    private ItemStack itemStack;

    public ItemUtil(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemUtil(Material material, String displayName, List<String> lore) {
        this.itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }

    public ItemUtil setProperty(ItemStackProperty... propertys) {
        ItemMeta meta = itemStack.getItemMeta();
        List<String> getLore = meta.getLore() == null ? List.of() : meta.getLore();
        for (ItemStackProperty itemStackProperty : propertys) {
            getLore.add(ChatColor.GOLD + itemStackProperty.name());
        }
        meta.setLore(getLore);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack buildItemStack() {
        return itemStack;
    }

    public static boolean containsProperty(ItemStack itemStack, ItemStackProperty property) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return false;
        if (meta.getLore() == null) return false;
        for (String lore : meta.getLore()) {
            if (lore.contains(property.name())) {
                return true;
            }
        }
        return false;
    }


}
