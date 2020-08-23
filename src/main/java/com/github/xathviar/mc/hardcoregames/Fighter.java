package com.github.xathviar.mc.hardcoregames;

import org.bukkit.entity.Player;

public class Fighter {
    private Player player;
    private Kit kit;
    private int kills;

    public Fighter(Player player, Kit kit) {
        this.player = player;
        this.kit = kit;
        kills = 0;
    }

    public Player getPlayer() {
        return player;
    }
}
