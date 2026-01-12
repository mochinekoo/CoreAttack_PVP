package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.manager.GameManager;
import mochineko.core_attack_pvp.manager.TeamManager;
import mochineko.core_attack_pvp.status.GameStatus;
import mochineko.core_attack_pvp.status.GameTeam;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Set;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        GameManager gameManager = GameManager.getInstance();
        TeamManager teamManager = TeamManager.getInstance();
        Player chat_player = event.getPlayer();
        GameTeam chat_team = teamManager.getJoinGameTeam(chat_player); //チャットした人の所属チーム
        if (chat_team == null)  return; //所属チームがnullの場合は何もしない

        Set<Player> recipient_list = event.getRecipients();
        recipient_list.removeIf(recipient_player -> {
            GameTeam recipient_team = teamManager.getJoinGameTeam(recipient_player); //受信者の所属チーム
            return chat_team != recipient_team; //チャットした人と受信者が違うチームの場合は、受信者から削除する（removeIfでfalseを返す）
        });

        if (gameManager.getStatus() == GameStatus.RUNNING) {
            //フォーマット：[チームチャット] [赤]<mochi__neko>: メッセージ
            String format = String.format("%s[チームチャット] %s[%s]%s<%s>:%s",
                    ChatColor.GRAY, chat_team.getColor(), chat_team.getTeamString(), ChatColor.RESET, chat_player.getName(), event.getMessage());
            event.setFormat(format);
        }
    }
}
