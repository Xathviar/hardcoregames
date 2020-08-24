package com.github.xathviar.mc.hardcoregames;

import com.github.xathviar.mc.hardcoregames.commands.KitSelector;
import com.github.xathviar.mc.hardcoregames.commands.StartCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private Map<Player, Scoreboard> scoreboard = Collections.synchronizedMap(new HashMap<Player, Scoreboard>());
    private Map<Player, Objective> objective = Collections.synchronizedMap(new HashMap<Player, Objective>());

    @Override
    public void onEnable() {
        new KitSelector(this);
        new StartCommand(this);
        Bukkit.getServer().getPluginManager().registerEvents(new Listener(this), this);
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

    public Objective addNewPlayer(Player player) {
        Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        Objective o = board.registerNewObjective(player.getPlayerListName(), "dummy");
        o.setDisplayName("    -- HG --    ");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        scoreboard.put(player, board);
        objective.put(player, o);
        return o;
    }

    public Scoreboard getScoreboard(Player p) {
        return scoreboard.get(p);
    }

    public Objective resetObjective(Player p) {
        Objective objective = this.objective.get(p);
        objective.unregister();
        objective = scoreboard.get(p).registerNewObjective(p.getPlayerListName(), "dummy");
        objective.setDisplayName("    -- HG --    ");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        p.setScoreboard(this.scoreboard.get(p));
        return objective;
    }

    public Objective getObjective(Player p) {
        return objective.get(p);
    }
}
