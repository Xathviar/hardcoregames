package com.github.xathviar.mc.hardcoregames;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.world.ChunkPopulateEvent;


public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (!HardCoreGame.isRunning()) {
            event.setCancelled(true);
        } else if (event.getBlock().getType().equals(Material.RED_MUSHROOM)) {
            breakAdjacentBlocks(event.getBlock(), Material.RED_MUSHROOM);
        } else if (event.getBlock().getType().equals(Material.BROWN_MUSHROOM)) {
            breakAdjacentBlocks(event.getBlock(), Material.BROWN_MUSHROOM);
        }
    }

    private void breakAdjacentBlocks(Block block, Material material) {
        if (!block.getType().equals(material)) {
            return;
        }
        Location l = block.getLocation();
        World world = block.getWorld();
        block.breakNaturally();
        breakAdjacentBlocks(world.getBlockAt(l.getBlockX() - 1, l.getBlockY(), l.getBlockZ()), material);
        breakAdjacentBlocks(world.getBlockAt(l.getBlockX() + 1, l.getBlockY(), l.getBlockZ()), material);
        breakAdjacentBlocks(world.getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() + 1), material);
        breakAdjacentBlocks(world.getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 1), material);
    }

    @EventHandler
    public void onDamageEvent(EntityDamageEvent event) {
        if (!HardCoreGame.isRunning()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!HardCoreGame.isRunning()) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void soupHealing(PlayerInteractEvent event) {
        if (!HardCoreGame.isRunning()) {
            event.setCancelled(true);
        } else if (event.getPlayer().getItemInHand().getType().equals(Material.MUSHROOM_SOUP) && event.getPlayer().getHealth() < 20) {
            if (event.getPlayer().getHealth() >= 14) {
                event.getPlayer().setHealth(20);
            } else {
                event.getPlayer().setHealth(event.getPlayer().getHealth() + 6);
            }
            event.getPlayer().getItemInHand().setType(Material.BOWL);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void playerJoin(PlayerLoginEvent event) {
        if (HardCoreGame.isRunning() && !event.getPlayer().isOp()) {
            event.setKickMessage("There is already a running game!");
            event.setResult(PlayerLoginEvent.Result.KICK_FULL);
        }
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        if (HardCoreGame.isRunning()) {
            event.getEntity().kickPlayer(event.getDeathMessage());
            HardCoreGame.removePlayer(event.getEntity());
            if (HardCoreGame.checkWin()) {
                HelperClass.broadcastMessage(Bukkit.getOnlinePlayers()[0].getDisplayName() + " won!");
                HardCoreGame.setIsRunning(false);
                Bukkit.getServer().unloadWorld("world", false);
            }
        }
    }

    @EventHandler
    public void onWorldPopulate(ChunkPopulateEvent event) {
        Chunk c = event.getChunk();
        World w = c.getWorld();
        int cx = c.getX() << 4;
        int cz = c.getZ() << 4;
        for (int x = cx; x < cx + 16; x++) {
            for (int z = cz; z < cz + 16; z++) {
                for (int y = 0; y < 128; y++) {
                    if ((w.getBlockAt(x, y, z).getType() == Material.DIRT
                            || w.getBlockAt(x, y, z).getType() == Material.SAND
                            || w.getBlockAt(x, y, z).getType() == Material.GRASS)
                            && w.getBlockAt(x, y + 1, z).getType() == Material.AIR) {
                        if (Math.random() < 0.125) {
                            w.getBlockAt(x, y + 1, z).setType(Material.RED_MUSHROOM);
                        } else if (Math.random() < 0.25) {
                            w.getBlockAt(x, y + 1, z).setType(Material.BROWN_MUSHROOM);
                        }
                    }
                }
            }
        }
    }
}