package com.github.xathviar.mc.hardcoregames;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Arrays;

public class Schematic {
    private int length, width, heigth;
    private Material[][][] schematic;

    public Schematic(int length, int heigth, int width) {
        this.length = length;
        this.heigth = heigth;
        this.width = width;
        this.schematic = new Material[length][heigth][width];
        fillWithAir(schematic);
    }

    public Schematic(Location start, Location end) {
        this.length = Math.abs(start.subtract(end).getBlockX());
        this.heigth = Math.abs(start.subtract(end).getBlockY());
        this.width = Math.abs(start.subtract(end).getBlockZ());
        this.schematic = new Material[length][heigth][width];
        fillWithAir(schematic);
    }

    public static void fillWithAir(Material[][][] schematic) {
        for (Material[][] materials : schematic) {
            for (Material[] material : materials) {
                Arrays.fill(material, Material.AIR);
            }
        }
    }

    public void place(Location start) {
        for (int i = 0; i < schematic.length; i++) {
            for (int j = 0; j < schematic[i].length; j++) {
                for (int k = 0; k < schematic[i][j].length; k++) {
                    Location location = new Location(start.getWorld(), (double) start.getBlockX() + i, (double) start.getBlockY() + j, (double) start.getBlockZ() + k);
                    location.getBlock().setType(schematic[i][j][k]);
                }
            }
        }
    }

    public Material[][][] getSchematic() {
        return schematic;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }
}
