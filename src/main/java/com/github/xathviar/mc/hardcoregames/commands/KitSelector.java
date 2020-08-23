package com.github.xathviar.mc.hardcoregames.commands;

import com.github.xathviar.mc.hardcoregames.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class KitSelector implements CommandExecutor {
    private Main plugin;

    public KitSelector(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("kit").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        System.out.println("kits");
        if (s.equals("kit")) {
            if (strings.length > 0) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage("Only Players may execute this command!");
                } else {
                    Player player = (Player) commandSender;
                    Kit kit = Kit.getKitFromArguments(strings[0]);
                    if (kit != null) {
                        HardCoreGame.addPlayer(new Fighter(player, kit));
                        HelperClass.sendMessage(player, "Your Kit " + strings[0] + " was saved!");
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
