package com.github.xathviar.mc.hardcoregames.commands;

import com.github.xathviar.mc.hardcoregames.HardCoreGame;
import com.github.xathviar.mc.hardcoregames.HelperClass;
import com.github.xathviar.mc.hardcoregames.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    private Main plugin;

    public StartCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("start").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("start") && commandSender instanceof Player) {
            HardCoreGame.setIsRunning(true);
            HelperClass.broadcastMessage("HG has been started!");
            return true;
        }
        return false;
    }
}
