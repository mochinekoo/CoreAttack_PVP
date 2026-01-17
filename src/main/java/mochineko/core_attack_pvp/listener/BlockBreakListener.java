package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.Main;
import mochineko.core_attack_pvp.json.BlockGuardJson;
import mochineko.core_attack_pvp.manager.CoreManager;
import mochineko.core_attack_pvp.manager.GameManager;
import mochineko.core_attack_pvp.manager.JsonManager;
import mochineko.core_attack_pvp.manager.TeamManager;
import mochineko.core_attack_pvp.status.BlockResourceType;
import mochineko.core_attack_pvp.status.FileType;
import mochineko.core_attack_pvp.status.GameStatus;
import mochineko.core_attack_pvp.status.GameTeam;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class BlockBreakListener implements Listener {

    private final BlockGuardJson json = (BlockGuardJson) new JsonManager(FileType.CONFIG).getDeserializedJson();

    /**
     * ブロックの保護のリスナー
     */
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

    /**
     * コア破壊のリスナー
     */
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CoreManager coreManager = CoreManager.getInstance();
        GameManager gameManager = GameManager.getInstance();
        GameTeam team = TeamManager.getInstance().getJoinGameTeam(player);
        Block block = event.getBlock();
        Location loc = block.getLocation();
        GameTeam breakTeam = coreManager.getBreakCoreTeam(loc);

        if (block.getType() != CoreManager.CORE_BLOCK.getMaterial()) return; //削ったブロックが、コアブロックじゃない場合
        if (breakTeam == null) return; //削った場所が、チームのコアじゃない場合

        //以降はイベントcancel（=削れないようにする）
        event.setCancelled(true);

        if (gameManager.getStatus() != GameStatus.RUNNING) {
            ChatUtil.sendInfoMessage(player, "ゲーム開始前に削ることはできません。");
            return;
        }
        if (team == null) {
            ChatUtil.sendInfoMessage(player, "チーズに所属していないため削ることができません。");
            return;
        }
        if (breakTeam == team) {
            ChatUtil.sendInfoMessage(player, "自軍のコアは削ることができません。");
            return;
        }

        //正常に削れた時の処理
        int getCore = coreManager.getCore(breakTeam);
        if (getCore <= 1) {
            block.setType(Material.BEDROCK);
        }
        else {
            coreManager.subtractCore(breakTeam, 1);
            ChatUtil.sendGlobalInfoMessage("コアが削られてます。残り：" + getCore);
        }
    }

    @EventHandler
    public void onBreakResources(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CoreManager coreManager = CoreManager.getInstance();
        GameTeam team = TeamManager.getInstance().getJoinGameTeam(player);
        Block block = event.getBlock();
        Location loc = block.getLocation();
        GameTeam breakTeam = coreManager.getBreakCoreTeam(loc);

        if (block.getType() == Material.COBBLESTONE) event.setCancelled(true);
        if (BlockResourceType.isBlockResourceType(block.getType())) {
            BlockResourceType blockResource = BlockResourceType.getBlockResourceType(block.getType());
            event.setCancelled(true);
            if (block.getType() == Material.GRAVEL) {
                player.getInventory().addItem(new ItemStack(blockResource.getRandomObtainMaterial()));
            }
            else {
                player.getInventory().addItem(new ItemStack(blockResource.getObtainMaterial().get(0)));
            }

            block.setType(Material.COBBLESTONE);
            new BukkitRunnable() {
                @Override
                public void run() {
                    block.setType(blockResource.getBreakMaterial().get(0));
                }
            }.runTaskLater(Main.getPlugin(Main.class), 20L * blockResource.getRespawnTime());
        }
    }
    
}
