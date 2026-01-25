package mochineko.core_attack_pvp.listener;

import mochineko.core_attack_pvp.manager.TeamManager;
import mochineko.core_attack_pvp.status.GameTeam;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        TeamManager teamManager = TeamManager.getInstance();
        Entity damage = event.getEntity(); //ダメージを受けた人
        Entity damager = event.getDamager(); //ダメージを与えた人
        if (damage instanceof Player && damager instanceof Player) {
            Player damage_player = (Player) damage;
            Player damager_player = (Player) damager;
            GameTeam damage_team = teamManager.getJoinGameTeam(damage_player); //ダメージを受けた人のチーム
            GameTeam damager_team = teamManager.getJoinGameTeam(damager_player); //ダメージを与えた人のチーム
            if (damage_team == null) return;
            if (damager_team == null) return;
            if (damage_team == damager_team) { //同じチームなら当たらないようにする
                event.setCancelled(true);
                event.setDamage(0);
            }
        }
    }
}
