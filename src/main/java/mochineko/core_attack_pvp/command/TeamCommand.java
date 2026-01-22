package mochineko.core_attack_pvp.command;

import mochineko.core_attack_pvp.manager.TeamManager;
import mochineko.core_attack_pvp.status.GameTeam;
import mochineko.core_attack_pvp.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * チームコマンドのクラス
 * @version 0.0.1
 */
public class TeamCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("game_team")) {
            TeamManager teamManager = TeamManager.getInstance();
            if (args[0].equalsIgnoreCase("join")) {
                //プレイヤーを特定のチームに参加させるコマンド
                GameTeam team = GameTeam.valueOf(args[1]);
                Player player = Bukkit.getPlayer(args[2]);
                teamManager.joinTeam(team, player);
                ChatUtil.sendInfoMessage(send, "プレイヤーをチームに参加させました。");
            }
            else if (args[0].equalsIgnoreCase("leave")) {
                //プレイヤーをチームから抜けさせるコマンド
                Player player = Bukkit.getPlayer(args[1]);
                teamManager.leaveTeam(player);
                ChatUtil.sendInfoMessage(send, "プレイヤーをチームから抜けさせました。");
            }
            else if (args[0].equalsIgnoreCase("list")) {
                //チームのリストを表示するコマンド
                StringBuilder team_list = new StringBuilder();
                team_list.append("==========").append("\n");
                for (GameTeam gameTeam : GameTeam.values()) {
                    Team team = teamManager.getConvertBoardTeam(gameTeam);
                    team_list.append(gameTeam.getTeamString()).append("(" + team.getSize() + "):").append(team.getEntries()).append("\n");
                }

                ChatUtil.sendInfoMessage(send, team_list.toString());
            }
            else if (args[0].equalsIgnoreCase("randomJoin")) {
                //プレイヤーをランダムにチームに参加させるコマンド
                Player player = Bukkit.getPlayer(args[1]);
                GameTeam randomJoin = teamManager.randomJoinTeam(player);
                ChatUtil.sendInfoMessage(send, "プレイヤーを" + randomJoin.getTeamString() + "に参加させました。");
            }
            else if (args[0].equalsIgnoreCase("assign")) {
                //全プレイヤーをチームに割り当てるコマンド
                teamManager.assignTeam();
                ChatUtil.sendInfoMessage(send, "チームを割り当てました。");
            }
            else if (args[0].equalsIgnoreCase("empty")) {
                //チームを空にするコマンド
                teamManager.emptyTeam();
                ChatUtil.sendInfoMessage(send, "チームを空にしました。");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("game_team")) {
            if (args.length == 1) {
                return Arrays.asList("join", "leave", "list", "randomJoin", "assign", "empty");
            }

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("join")) {
                    return Arrays.stream(GameTeam.values())
                            .map(Enum::name)
                            .filter(filter -> filter.startsWith(args[1]))
                            .collect(Collectors.toList());
                }
                if (args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("randomJoin")) {
                    return Bukkit.getOnlinePlayers().stream()
                            .map(Player::getName)
                            .filter(filter -> filter.startsWith(args[1]))
                            .collect(Collectors.toList());
                }
            }

            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("join")) {
                    return Bukkit.getOnlinePlayers().stream()
                            .map(Player::getName)
                            .filter(filter -> filter.startsWith(args[2]))
                            .collect(Collectors.toList());
                }
            }
        }
        return Collections.emptyList();
    }
}
