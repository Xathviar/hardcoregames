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
            for (Fighter fighter1 : fighters) {
                if (fighter1.getPlayer().equals(fighter.getPlayer())) {
                    fighters.remove(fighter1);
                }
            }
            fighters.add(fighter);
        }
    }

    public static void removePlayer(Player entity) {
        synchronized (fighters) {
            for (Fighter fighter1 : fighters) {
                if (fighter1.getPlayer().equals(entity)) {
                    fighters.remove(fighter1);
                }
            }
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
}
