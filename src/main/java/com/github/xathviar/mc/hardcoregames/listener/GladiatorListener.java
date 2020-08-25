package com.github.xathviar.mc.hardcoregames.listener;

import com.github.xathviar.mc.hardcoregames.*;
import com.github.xathviar.mc.hardcoregames.KitHelper.GladiatorFight;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class GladiatorListener implements org.bukkit.event.Listener {
    private Main main;

    public GladiatorListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        if (!HardCoreGame.isRunning()) {
            event.setCancelled(true);
        } else if (HardCoreGame.getFighter(event.getPlayer()).getKit() == Kit.GLADIATOR) {
            final Player p = event.getPlayer();
            final Fighter f = HardCoreGame.getFighter(p);

            if (f.getKitCooldown() == 0) {
                if (event.getRightClicked().getType().equals(EntityType.PLAYER) && p.getItemInHand().getType() == Material.IRON_FENCE) {
                    startFight(event);
                }
                f.setKitCooldown(f.getKit().getKitCooldown());
            } else if (f.getKitCooldown() != 0) {
                HelperClass.kitCooldownMessage(p, f.getKitCooldown());
            }
        }
    }

    public void startFight(PlayerInteractEntityEvent event) {
        HelperClass.broadcastMessage("Gladifight");
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Player k = event.getEntity().getKiller();

        for (GladiatorFight gladiatorFight : HardCoreGame.getGladiatorFights()) {
            if (gladiatorFight.getF1().getPlayer().equals(p) ||
                    gladiatorFight.getF1().getPlayer().equals(k) ||
                    gladiatorFight.getF2().getPlayer().equals(p) ||
                    gladiatorFight.getF2().getPlayer().equals(k)) {
                HardCoreGame.removeGladiatorFight(p);
            }
        }
    }
}