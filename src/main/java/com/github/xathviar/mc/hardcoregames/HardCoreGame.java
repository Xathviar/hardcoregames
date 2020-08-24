package com.github.xathviar.mc.hardcoregames;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashSet;

import java.util.Set;

public class HardCoreGame {
    private static boolean isRunning = false;
    private static final Set<Fighter> fighters = Collections.synchronizedSet(new HashSet<Fighter>());

    public static boolean isRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        HardCoreGame.isRunning = isRunning;
    }

    public synchronized static void addPlayer(Fighter fighter) {
        synchronized (fighters) {
            fighters.removeIf(fighter1 -> fighter1.getPlayer().equals(fighter.getPlayer()));
            fighters.add(fighter);
        }
    }

    public static void removePlayer(Player entity) {
        synchronized (fighters) {
            fighters.removeIf(fighter1 -> fighter1.getPlayer().equals(entity));
        }
    }

    public static boolean checkWin() {
        System.out.println(fighters);
        return fighters.size() <= 1;
    }

    public static Fighter getFighter(Player player) {
        for (Fighter fighter : fighters) {
            if (fighter.getPlayer().equals(player)) {
                return fighter;
            }
        }
        return null;
    }

    public static void soutFighters() {
        for (Fighter fighter : fighters) {
            System.out.println(fighter.getPlayer().getDisplayName() + fighter.getKit().name().toLowerCase());
        }
    }
}
