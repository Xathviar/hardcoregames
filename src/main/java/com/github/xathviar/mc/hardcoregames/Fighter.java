package com.github.xathviar.mc.hardcoregames;

import org.bukkit.entity.Player;

public class Fighter {
    private Player player;
    private Kit kit;
    private int kills;
    private int kitCooldown;
    private Fighter lastTarget;

    public Fighter(Player player, Kit kit) {
        this.player = player;
        this.kit = kit;
        kills = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setLastTarget(Fighter fighter) {
        this.lastTarget = fighter;
    }

    public Kit getKit() {
        return kit;
    }
}
