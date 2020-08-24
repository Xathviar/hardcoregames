package com.github.xathviar.mc.hardcoregames.listener;

import com.github.xathviar.mc.hardcoregames.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class NinjaListener implements org.bukkit.event.Listener {
    private Main main;

    public NinjaListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
        if (!HardCoreGame.isRunning()) {
            event.setCancelled(true);
        } else if (event.isSneaking()) {
            final Player p = event.getPlayer();
            final Fighter f = HardCoreGame.getFighter(p);
            if (f.getKit() == Kit.NINJA && f.getLastTarget() != null && !f.isOnCooldown()) {
                Player e = f.getLastTarget().getPlayer();
                if (e.isDead()) {
                    f.setLastTarget(null);
                }
                double newX;
                double newZ;
                float nang = e.getLocation().getYaw() + 90;

                if (nang < 0) nang += 360;

                newX = Math.cos(Math.toRadians(nang));
                newZ = Math.sin(Math.toRadians(nang));

                Location newLocation = new Location(e.getLocation().getWorld(), e.getLocation().getX() - newX,
                        e.getLocation().getY(), e.getLocation().getZ() - newZ, e.getLocation().getYaw(), e.getLocation().getPitch());

                p.teleport(newLocation);
                f.setOnCooldown(true);
                Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                    @Override
                    public void run() {
                        f.setOnCooldown(false);
                    }
                }, f.getKit().getKitCooldown());
            }

        }
    }

    @EventHandler
    public void onPlayerEntityHit(EntityDamageByEntityEvent event) {
        try {
            if (event.getDamager() instanceof Player
                    && event.getEntity() instanceof Player && HardCoreGame.getFighter((Player) event.getDamager()).getKit() == Kit.NINJA) {
                HardCoreGame.getFighter((Player) event.getDamager()).setLastTarget(HardCoreGame.getFighter((Player) event.getEntity()));
            }
        } catch (Exception ignored) {
        }
    }
}
