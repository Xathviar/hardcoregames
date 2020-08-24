package com.github.xathviar.mc.hardcoregames;

public enum Kit {
    NINJA("ninja", 30),
    STOMPER("stomper", 0),
    ANCHOR("anchor", 0),
    NOOB("noob", 0);

    private String name;
    private int kitCooldown;

    Kit(String name, int kitCooldown) {
        this.name = name;
        this.kitCooldown = kitCooldown;
    }

    public static Kit getKitFromArguments(String s) {
        for (Kit kit : Kit.class.getEnumConstants()) {
            if (s.equalsIgnoreCase(kit.name)) {
                return kit;
            }
        }
        return null;
    }

    public static String getKitsAsList() {
        StringBuilder s = new StringBuilder();
        Kit[] kits = Kit.class.getEnumConstants();
        for (int i = 0; i < kits.length; i++) {
            s.append(kits[i].name);
            if (i != kits.length - 1) {
                s.append(", ");
            }
        }
        return s.toString();
    }

    public int getKitCooldown() {
        return kitCooldown;
    }

    public void setKitCooldown(int kitCooldown) {
        this.kitCooldown = kitCooldown;
    }

    public void setName(String name) {
        this.name = name;
    }
}
