package com.github.xathviar.mc.hardcoregames;

import com.github.xathviar.mc.hardcoregames.commands.KitSelector;
import com.github.xathviar.mc.hardcoregames.commands.StartCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new KitSelector(this);
        new StartCommand(this);
        Bukkit.getServer().getPluginManager().registerEvents(new Listener(), this);
    }
}
