package com.github.xathviar.mc.hardcoregames.KitHelper;

import com.github.xathviar.mc.hardcoregames.Fighter;
import com.github.xathviar.mc.hardcoregames.Schematic;
import org.bukkit.Material;

public class GladiatorFight {
    private Fighter f1;
    private Fighter f2;
    private Schematic box = new Schematic(20, 6, 10);

    public GladiatorFight(Fighter f1, Fighter f2) {
        this.f1 = f1;
        this.f2 = f2;
        initBox();
        box.place(f1.getPlayer().getLocation());
    }

    public Fighter getF1() {
        return f1;
    }

    public void setF1(Fighter f1) {
        this.f1 = f1;
    }

    public Fighter getF2() {
        return f2;
    }

    public void setF2(Fighter f2) {
        this.f2 = f2;
    }

    private void initBox() {
        for (int i = 0; i < box.getHeigth(); i++) {
            for (int j = 0; j < box.getLength(); j++) {
                box.getSchematic()[j][i][0] = Material.GLASS;
                box.getSchematic()[j][i][box.getWidth() - 1] = Material.GLASS;
            }
            for (int j = 0; j < box.getWidth(); j++) {
                box.getSchematic()[0][i][j] = Material.GLASS;
                box.getSchematic()[box.getLength() - 1][i][j] = Material.GLASS;
            }
        }
        for (int i = 0; i < box.getLength(); i++) {
            for (int j = 0; j < box.getWidth(); j++) {
                box.getSchematic()[i][0][j] = Material.GLASS;
                box.getSchematic()[i][box.getHeigth() - 1][j] = Material.GLASS;
            }
        }
    }
}
