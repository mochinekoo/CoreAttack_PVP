package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.json.BlockGuardJson;
import mochineko.core_attack_pvp.manager.JsonManager;
import mochineko.core_attack_pvp.status.FileType;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BlockPlaceListener implements Listener {

    //置いたらゲームに影響するブロック
    private static final List<Material> CANT_BLOCK = Arrays.asList(
            Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.REDSTONE_ORE,
            Material.OAK_LOG, Material.GRAVEL
    );

    //保護エリアのJSON
    private final BlockGuardJson json = (BlockGuardJson) new JsonManager(FileType.CONFIG).getDeserializedJson();

    @EventHandler
    public void onGuard(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        if (CANT_BLOCK.contains(block.getType())) {
            event.setCancelled(true);
            ChatUtil.sendInfoMessage(player, "そのブロックを設置することはできません。");
            return;
        }

        Map<String, BlockGuardJson.GuardData> guardData = json.getGuardData();
        if (guardData == null) return;
        for (BlockGuardJson.GuardData guard : guardData.values()) {
            if (guard.isAABB(event.getBlock().getLocation())) {
                event.setCancelled(true);
                ChatUtil.sendInfoMessage(player, "そのエリアにブロックを設置することはできません。");
                return;
            }
        }
    }
}
