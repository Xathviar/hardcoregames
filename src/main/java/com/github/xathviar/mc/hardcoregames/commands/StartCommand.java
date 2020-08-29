package com.github.xathviar.mc.hardcoregames.commands;

import com.github.xathviar.mc.hardcoregames.*;
import com.wimbli.WorldBorder.BorderData;
import com.wimbli.WorldBorder.Config;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
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
            for (Fighter fighter : HardCoreGame.getFighters()) {
                if (fighter.getKit() == Kit.NINJA) {
                    final Fighter f = fighter;
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            f.reduceKitCooldown();
                        }
                    }, 20, f.getKit().getKitCooldown());
                }
            }
            HardCoreGame.setGracePeriod(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    HelperClass.broadcastMessage("The Grace Period has ended!");
                    HardCoreGame.setGracePeriod(false);
                }
            }, 150 * 20);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                BarAPI.setMessage(onlinePlayer, "Grace Period", 150);
            }
            BorderData border = Config.Border("world");

            return true;
        }
        return false;
    }
}
