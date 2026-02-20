package mochineko.core_attack_pvp.command;

import mochineko.core_attack_pvp.manager.GameManager;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameStartCommand implements CommandExecutor, TabCompleter {

    private final int NO_OPTION = 0;
    private final int NO_COUNTDOWN_OPTION = 1;

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("game_start")) {
            GameManager gameManager = GameManager.getInstance();
            ChatUtil.sendInfoMessage(send, "ゲームを開始しています..");

            int gameStartResult = -1;
            if (args[0].equalsIgnoreCase("fast")) {
               gameStartResult = gameManager.startGame(NO_COUNTDOWN_OPTION); //カウントダウンなしで、
            }
            else {
                gameStartResult = gameManager.startGame(NO_OPTION);
            }

            if (gameStartResult == 0) {
                //今のところは何もしない
            }
            else {
                ChatUtil.sendErrorMessage(send, "ゲームの開始に失敗しました。");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender send, Command cmd, String s, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("fast");
        }

        return Collections.emptyList();
    }

}
