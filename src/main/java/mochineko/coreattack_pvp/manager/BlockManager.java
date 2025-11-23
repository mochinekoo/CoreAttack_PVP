package mochineko.coreattack_pvp.manager;

import mochineko.coreattack_pvp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BlockManager {

    private static final List<Location> blockList = new ArrayList<>();

    /**
     * ゲーム用のブロックを破壊する処理
     * @implNote {@link GameBlock} のブロック
     * @param event BlockBreakEventを指定する
     */
    public static void breakBlock(BlockBreakEvent event) {
        @NotNull Player player = event.getPlayer();
        @NotNull final Block block = event.getBlock();
        @NotNull BlockData before_block = block.getBlockData().clone();
        @NotNull final Location loc = block.getLocation();

        if (isGameBlock(loc) && canGetBlock(loc)) {
            final GameBlock gameBlock = GameBlock.getConvertGameBlock(block.getType());
            int time = gameBlock.revivedTime;
            player.getInventory().addItem(new ItemStack(gameBlock.getPlayerBlock()));
            blockList.add(loc);
            block.setType(Material.COBBLESTONE);

            new BukkitRunnable() {
                @Override
                public void run() {
                    blockList.remove(loc);
                    block.setBlockData(before_block, true);
                    this.cancel();
                }
            }.runTaskLater(Main.getPlugin(Main.class), 20L *time);
        }
    }

    /**
     * そのブロックが破壊可能か
     * @param location 破壊可能かを調べたい場所
     */
    public static boolean canGetBlock(Location location) {
        return !blockList.contains(location.add(0, 0, 0)); //入ってたらfalse、入ってなかったらtrue
    }

    /**
     * そのブロックはゲーム内のブロックか
     * @param location ゲーム内のブロックか調べたい場所
     */
    public static boolean isGameBlock(Location location) {
        @NotNull Block block = location.getBlock();
        return GameBlock.getConvertGameBlock(block.getType()) != null;
    }

    public enum GameBlock {
        COAL_ORE(10, Material.COAL), //石炭
        IRON_ORE(10, Material.IRON_ORE), //鉄
        GOLD_ORE(10, Material.GOLD_ORE), //金
        DIAMOND_ORE(10, Material.DIAMOND), //ダイアモンド
        EMERALD_ORE(10, Material.EMERALD); //エメラルド

        private final int revivedTime; //デフォルトの復活時間
        private final Material get_playerBlock; //プレイヤーが入手するブロック

        GameBlock(int revivedTime, Material get_playerBlock) {
            this.revivedTime = revivedTime;
            this.get_playerBlock = get_playerBlock;
        }

        /**
         * デフォルトのブロック復活時間
         */
        public int getDefaultRevivedTime() {
            return revivedTime;
        }

        /**
         * @return プレイヤーが掘った時に得られるブロック。無い場合はnull。
         */
        public Material getPlayerBlock() {
            return get_playerBlock;
        }

        /**
         * {@link Material} から {@link GameBlock} に変換する関数
         * @param material 変換したいMaterialブロック
         * @return GameBlockを返す。無い場合はnull。
         */
        public static GameBlock getConvertGameBlock(Material material) {
            return Arrays.stream(GameBlock.values())
                    .filter(gameBlock -> material.name().equalsIgnoreCase(gameBlock.name()))
                    .findFirst()
                    .orElse(null);
        }
    }
}
