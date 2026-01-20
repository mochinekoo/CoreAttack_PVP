package mochineko.core_attack_pvp.library;

import mochineko.core_attack_pvp.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public abstract class KitBase {

    private final String kit_name;
    private final OfflinePlayer player;
    protected int cooldown;
    protected boolean canUse;

    public KitBase(String kit_name, OfflinePlayer player, int cooldown) {
        this.kit_name = kit_name;
        this.player = player;
        this.cooldown = cooldown;
        this.canUse = true;
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

    public void giveKitItem() {
        getPlayer().getInventory().addItem(getKitItems().toArray(new ItemStack[0]));
    }
}
