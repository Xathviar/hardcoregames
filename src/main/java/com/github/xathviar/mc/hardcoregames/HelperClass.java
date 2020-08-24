package com.github.xathviar.mc.hardcoregames;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class HelperClass {

    public static void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.AQUA + "[HG] " + ChatColor.RESET + message);
    }

    public static void sendMessage(HumanEntity entity, String message) {
        sendMessage((Player) entity, message);
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.AQUA + "[HG] " + ChatColor.RESET + message);
    }

    public static void broadcastMessage(String message) {
        Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[HG] " + ChatColor.RESET + message);
    }

    public static void breakAdjacentBlocks(Block block, Material material) {
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
}

