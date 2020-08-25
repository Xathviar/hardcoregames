package com.github.xathviar.mc.hardcoregames;

import com.github.xathviar.mc.hardcoregames.commands.KitSelector;
import com.github.xathviar.mc.hardcoregames.commands.StartCommand;
import com.github.xathviar.mc.hardcoregames.listener.AnchorListener;
import com.github.xathviar.mc.hardcoregames.listener.Listener;
import com.github.xathviar.mc.hardcoregames.listener.NinjaListener;
import com.github.xathviar.mc.hardcoregames.listener.StomperListener;
import net.minecraft.util.org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private Map<Player, Spigboard> scoreboard = Collections.synchronizedMap(new HashMap<Player, Spigboard>());

    @Override
    public void onEnable() {
        new KitSelector(this);
        new StartCommand(this);
        Bukkit.getServer().getPluginManager().registerEvents(new Listener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AnchorListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new NinjaListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new StomperListener(this), this);
        addRecipes();
    }

    public void addRecipes() {
        ItemStack is = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ShapelessRecipe soups1 = new ShapelessRecipe(is);
        ShapelessRecipe soups2 = new ShapelessRecipe(is);
        soups1.addIngredient(1, Material.BOWL);
        soups1.addIngredient(1, Material.CACTUS);
        soups2.addIngredient(1, Material.BOWL);
        soups2.addIngredient(1, Material.INK_SACK, 3);
        Bukkit.addRecipe(soups1);
        Bukkit.addRecipe(soups2);
    }

    public Scoreboard addNewPlayer(Player player) {
        Spigboard board = new Spigboard("    -- HG --    ");
        board.add(player);
        board.add("kit", "kit: " + "noob", 0);
        scoreboard.put(player, board);
        return board.getScoreboard();
    }
}