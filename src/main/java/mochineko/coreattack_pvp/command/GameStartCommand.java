package mochineko.coreattack_pvp.command;

import mochineko.coreattack_pvp.manager.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GameStartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender send, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("game_start")) {
            GameManager manager = GameManager.getInstance();
            manager.startGame();
        }
        return false;
    }

}
