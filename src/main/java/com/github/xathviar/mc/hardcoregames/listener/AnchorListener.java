package com.github.xathviar.mc.hardcoregames.listener;

import com.github.xathviar.mc.hardcoregames.Fighter;
import com.github.xathviar.mc.hardcoregames.HardCoreGame;
import com.github.xathviar.mc.hardcoregames.Kit;
import com.github.xathviar.mc.hardcoregames.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class AnchorListener implements org.bukkit.event.Listener {
    private Main main;

    public AnchorListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void anchorListener(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player p = (Player) event.getEntity();
            Fighter f = HardCoreGame.getFighter(p);
            if (f.getKit() == Kit.ANCHOR) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                    p.playSound(p.getLocation(), Sound.ANVIL_LAND, 5, 0);
                    p.setVelocity(new Vector(0, 0, 0));
                }, 1);
            } else if (event.getDamager() instanceof Player && HardCoreGame.getFighter((Player) event.getDamager()).getKit() == Kit.ANCHOR) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                    p.playSound(p.getLocation(), Sound.ANVIL_LAND, 5, 0);
                    p.setVelocity(new Vector(0, 0, 0));
                }, 1);
            }
        }
    }
}