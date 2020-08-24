package com.github.xathviar.mc.hardcoregames;

import com.github.xathviar.mc.hardcoregames.commands.KitSelector;
import com.github.xathviar.mc.hardcoregames.commands.StartCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new KitSelector(this);
        new StartCommand(this);
        Bukkit.getServer().getPluginManager().registerEvents(new Listener(), this);
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
}
