package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.json.BlockGuardJson;
import mochineko.core_attack_pvp.manager.CoreManager;
import mochineko.core_attack_pvp.manager.JsonManager;
import mochineko.core_attack_pvp.manager.TeamManager;
import mochineko.core_attack_pvp.status.FileType;
import mochineko.core_attack_pvp.status.GameTeam;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;

public class BlockBreakListener implements Listener {

    private final BlockGuardJson json = (BlockGuardJson) new JsonManager(FileType.CONFIG).getDeserializedJson();
    
    @EventHandler
    public void onGuard(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Map<String, BlockGuardJson.GuardData> guardData = json.getGuardData();
        for (BlockGuardJson.GuardData guard : guardData.values()) {
            if (guard.isAABB(event.getBlock().getLocation())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CoreManager coreManager = CoreManager.getInstance();
        GameTeam team = TeamManager.getInstance().getJoinGameTeam(player);
        Block block = event.getBlock();
        Location loc = block.getLocation();
        GameTeam breakTeam = coreManager.getBreakCoreTeam(loc);

        if (team == null) return; //削った人が、チームに所属していない場合
        if (block.getType() != CoreManager.CORE_BLOCK.getMaterial()) return; //削ったブロックが、コアブロックじゃない場合
        if (breakTeam == null) return; //削った場所が、チームのコアじゃない場合

        int getCore = coreManager.getCore(breakTeam);
        if (getCore <= 1) {
            block.setType(Material.BEDROCK);
        }
        else {
            coreManager.subtractCore(breakTeam, 1);
            ChatUtil.sendGlobalInfoMessage("コアが削られてます。残り：" + getCore);
        }

        event.setCancelled(true);
    }
    
}
