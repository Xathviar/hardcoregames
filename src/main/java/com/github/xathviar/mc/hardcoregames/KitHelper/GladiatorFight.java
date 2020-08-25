package com.github.xathviar.mc.hardcoregames.KitHelper;

import com.github.xathviar.mc.hardcoregames.Fighter;

public class GladiatorFight {
    private Fighter f1;
    private Fighter f2;

    public GladiatorFight(Fighter f1, Fighter f2) {
        this.f1 = f1;
        this.f2 = f2;
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
}
