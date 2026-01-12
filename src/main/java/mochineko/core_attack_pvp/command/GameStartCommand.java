package mochineko.core_attack_pvp.command;

import mochineko.core_attack_pvp.manager.GameManager;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameStartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("game_start")) {
            GameManager gameManager = GameManager.getInstance();
            ChatUtil.sendInfoMessage(send, "ゲームを開始しています..");

            int gameStartResult = gameManager.startGame();
            if (gameStartResult == 0) {
                //今のところは何もしない
            }
            else {
                ChatUtil.sendErrorMessage(send, "ゲームの開始に失敗しました。");
            }
        }
        return false;
    }

}
