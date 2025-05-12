package Items;

import java.util.ArrayList;

public class Item { // types: Weapon, Food, Car
    private String name;
    private String description;
    private int durability;
    private int durabilityPerUse;
    private String usageMessage;
    private int hoursPerUse;
    public static ArrayList<Item> EMPTY_INVENTORY = new ArrayList<Item>();
    public Item(String name, String description, int durability, int durabilityPerUse, int hoursPerUse, String usageMessage) {
        this.name = name;
        this.description = description;
        this.durability = durability;
        this.durabilityPerUse = durabilityPerUse;
        this.hoursPerUse = hoursPerUse;
        this.usageMessage = usageMessage;
    }

    public String getDescription() {
        return description;
    }

    public boolean use() {
        durability -= durabilityPerUse;
        if(usageMessage != "") {
            System.out.println(usageMessage);
        }
        if (durability <= 0) {
            return false;
        }
        return true;
    }
    public int getHoursPerUse() {
        return hoursPerUse;
    }

    public String toString() {
        return name;
    }
}
