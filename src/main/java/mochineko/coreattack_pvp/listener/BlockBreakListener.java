package mochineko.coreattack_pvp.listener;

import mochineko.coreattack_pvp.manager.BlockManager;
import mochineko.coreattack_pvp.manager.CoreManager;
import mochineko.coreattack_pvp.manager.TeamManager;
import mochineko.coreattack_pvp.utility.TextUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        @NotNull Player player = event.getPlayer();
        @NotNull Block block = event.getBlock();
        @NotNull Location loc = block.getLocation().add(0, 0, 0);
        CoreManager coreManager = CoreManager.getInstance();
        TeamManager.GameTeam break_core_team = coreManager.getLocationCore(loc);

        if (break_core_team == null) {
            return;
        }
        else {
            TextComponent text = new TextComponent(player.getName() + "がコアを破壊しています。(現在:" + coreManager.getCore(break_core_team) + ")");
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
            coreManager.subtractCore(break_core_team, 1);
        }
    }

    //コア以外のブロック破壊
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        @NotNull Player player = event.getPlayer();
        @NotNull Block block = event.getBlock();
        @NotNull Location loc = block.getLocation().add(0, 0, 0);

        if (!BlockManager.canGetBlock(loc)) {
            event.setCancelled(true);
        }
        if (BlockManager.isGameBlock(loc)) {
            if (BlockManager.canGetBlock(loc)) {
                BlockManager.breakBlock(event);
            }

            event.setCancelled(true);
        }
    }

}
