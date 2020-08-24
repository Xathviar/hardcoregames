package com.github.xathviar.mc.hardcoregames.commands;

import com.github.xathviar.mc.hardcoregames.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;


public class KitSelector implements CommandExecutor {
    private Main plugin;

    public KitSelector(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("kit").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equals("kit")) {
            if (HardCoreGame.isRunning()) {
                HelperClass.sendMessage(commandSender, "You can't change your kit while a game is running!");
            } else if (strings.length > 0) {
                if (!(commandSender instanceof Player)) {
                    HelperClass.sendMessage(commandSender, "Only Players may execute this command!");
                } else {
                    Player player = (Player) commandSender;
                    Kit kit = Kit.getKitFromArguments(strings[0]);
                    if (kit != null) {
                        HardCoreGame.addPlayer(new Fighter(player, kit));
                        HelperClass.sendMessage(player, "Your Kit " + strings[0] + " was saved!");
                        Objective o = plugin.resetObjective((Player) commandSender);
                        Score score = o.getScore("Kit: " + kit.name().toLowerCase());
                        score.setScore(0);
                        player.setScoreboard(plugin.getScoreboard(player));
                        return true;
                    } else {
                        HelperClass.sendMessage(player, "Please use one of these kits: " + Kit.getKitsAsList());
                        return false;
                    }
                }
            } else {
                HelperClass.sendMessage(commandSender, "You need to select a kit!");
                HelperClass.sendMessage(commandSender, "Please use one of these kits: " + Kit.getKitsAsList());
            }
        }
        return false;
    }
}
