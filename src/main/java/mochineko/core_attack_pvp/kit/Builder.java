package mochineko.core_attack_pvp.kit;

import mochineko.core_attack_pvp.Main;
import mochineko.core_attack_pvp.library.KitBase;
import mochineko.core_attack_pvp.status.ItemStackProperty;
import mochineko.core_attack_pvp.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Builder extends KitBase {

    private static final int BOOK_COOLDOWN = 30;

    private BukkitTask task;

    public static final List<ItemStack> KIT_ITEM = List.of(
            new ItemUtil(Material.BOOK, "ビルダー", Arrays.asList("あああ")).setProperty(ItemStackProperty.KIT_ITEM).buildItemStack()
    );

    public Builder(OfflinePlayer player) {
        super("Builder", player);
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack bookKit = findKitItem(new ItemStack(Material.BOOK));
                if (bookKit == null) return;
                ItemMeta meta = bookKit.getItemMeta();
                int coolDown = getCoolDown(bookKit.getType());
                if (coolDown <= 0) {
                    meta.setDisplayName("ビルダー" + ChatColor.GRAY + "(利用可能です!)");
                    setCanUseItem(bookKit.getType(), true);
                }
                else {
                    meta.setDisplayName("ビルダー" + ChatColor.GRAY + "(残りあと" + coolDown + "秒)");
                    setCoolDown(bookKit.getType(), coolDown - 1);
                    setCanUseItem(bookKit.getType(), false);
                }
                bookKit.setItemMeta(meta);
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);
    }

    @Override
    public List<ItemStack> getKitItems() {
        return KIT_ITEM;
    }

    public void useKitItem() {
        ItemStack bookKit = findKitItem(new ItemStack(Material.BOOK));
        if (!canUseItem(bookKit.getType())) return;

        Inventory inv = Bukkit.createInventory(null, 9 * 5, "ビルダー");
        inv.setItem(0, new ItemStack(Material.DIRT));
        getPlayer().openInventory(inv);
        setCoolDown(bookKit.getType(), BOOK_COOLDOWN);
    }

}
