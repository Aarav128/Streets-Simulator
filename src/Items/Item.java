package Items;

import java.util.ArrayList;

import People.Player;

public class Item { // types: Weapon, Food, Car
    private String name;
    private String description;
    private int durability;
    private int durabilityPerUse;
    private int hoursPerUse;
    public static ArrayList<Item> EMPTY_INVENTORY = new ArrayList<Item>();
    public Item(String name, String description, int durability, int durabilityPerUse, int hoursPerUse) {
        this.name = name;
        this.description = description;
        this.durability = durability;
        this.durabilityPerUse = durabilityPerUse;
        this.hoursPerUse = hoursPerUse;
    }
    public boolean use() {
        durability -= durabilityPerUse;
        if (durability <= 0) {
            return false; // TODO: implement item destruction
        }
        return true;
    }
    public String toString() {
        return name;
    }
}
