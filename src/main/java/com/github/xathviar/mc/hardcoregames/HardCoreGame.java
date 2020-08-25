package com.github.xathviar.mc.hardcoregames;

import com.github.xathviar.mc.hardcoregames.KitHelper.GladiatorFight;
import org.bukkit.entity.Player;

import java.util.*;

public class HardCoreGame {
    private static boolean isRunning = false;
    private static boolean gracePeriod = true;
    private static final List<Fighter> fighters = Collections.synchronizedList(new ArrayList<>());
    private static final List<GladiatorFight> gladiatorFights = Collections.synchronizedList(new ArrayList<>());

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
            System.out.println(fighter.getPlayer().getDisplayName() + " " + fighter.getKit().name().toLowerCase());
        }
    }

    public static List<Fighter> getFighters() {
        return fighters;
    }

    public static List<GladiatorFight> getGladiatorFights() {
        return gladiatorFights;
    }

    public static void removeGladiatorFight(Player entity) {
        synchronized (gladiatorFights) {
            gladiatorFights.removeIf(x -> x.getF1().getPlayer().equals(entity));
            gladiatorFights.removeIf(x -> x.getF2().getPlayer().equals(entity));
        }
    }

    public static boolean isGracePeriod() {
        return gracePeriod;
    }

    public static void setGracePeriod(boolean b) {
        gracePeriod = b;
    }
}
