package mochineko.core_attack_pvp.command;

import mochineko.core_attack_pvp.manager.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameSettingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("set_gametime")) {
            GameManager manager = GameManager.getInstance();
            if (args[0].equalsIgnoreCase("add")) {
                manager.addTime(Integer.parseInt(args[1]));
            }
            else if (args[0].equalsIgnoreCase("remove")) {
                manager.subtractTime(Integer.parseInt(args[1]));
            }
            else if (args[0].equalsIgnoreCase("set")) {
                manager.setTime(Integer.parseInt(args[1]));
            }
            else {

            }
        }
        return false;
    }

}
