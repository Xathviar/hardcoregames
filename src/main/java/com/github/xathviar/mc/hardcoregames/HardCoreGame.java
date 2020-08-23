package com.github.xathviar.mc.hardcoregames;

import org.bukkit.entity.Player;

import java.util.HashSet;

import java.util.Set;

public class HardCoreGame {
    private static boolean isRunning = false;
    private static Set<Fighter> fighters = new HashSet<>();

    public static boolean isRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        HardCoreGame.isRunning = isRunning;
    }

    public static void addPlayer(Fighter fighter) {
        for (Fighter fighter1 : fighters) {
            if (fighter1.getPlayer().equals(fighter.getPlayer()))
            fighters.remove(fighter1);
        }
        fighters.add(fighter);
        System.out.println(fighters.toString());
    }

    public static void removePlayer(Player entity) {
        fighters.remove(entity);
    }

    public static boolean checkWin() {
        if (fighters.size() <= 1)
            return true;
        return false;
    }
}
