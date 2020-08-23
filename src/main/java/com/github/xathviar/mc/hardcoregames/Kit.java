package com.github.xathviar.mc.hardcoregames;

public enum Kit {
    NINJA("ninja"),
    STOMPER("stomper"),
    ANCHOR("anchor");

    String name;

    Kit(String name) {
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }
}
