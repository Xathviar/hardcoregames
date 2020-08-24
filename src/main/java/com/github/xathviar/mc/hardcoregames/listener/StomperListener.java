package com.github.xathviar.mc.hardcoregames.listener;

import com.github.xathviar.mc.hardcoregames.HardCoreGame;
import com.github.xathviar.mc.hardcoregames.HelperClass;
import com.github.xathviar.mc.hardcoregames.Kit;
import com.github.xathviar.mc.hardcoregames.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class StomperListener implements org.bukkit.event.Listener {
    private Main main;

    public StomperListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void stomperListener(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL && event.getEntity() instanceof Player
                && HardCoreGame.getFighter((Player) event.getEntity()).getKit() == Kit.STOMPER) {
            if (event.getDamage() > 3) {
                double damage = event.getDamage();
                event.setDamage(3);
                List<Player> players = HelperClass.getNearbyPlayers((Player) event.getEntity());
                for (Player player : players) {
                    if (!player.isSneaking()) {
                        if (damage > player.getHealth()) {
                            player.setHealth(0);
                        } else {
                            player.setHealth(player.getHealth() - damage);
                        }
                    }
                }
            }
        }
    }
}
