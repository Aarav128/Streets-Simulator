package Items;

import java.util.ArrayList;

import People.Player;

public class Item { // types: Weapon, Food, Car
    private String name;
    private String description;
    private int durability;
    private int durabilityPerUse;
    private String usageMessage;
    private int hoursPerUse;
    private Player player;
    public static ArrayList<Item> EMPTY_INVENTORY = new ArrayList<Item>();
    public Item(String name, String description, int durability, int durabilityPerUse, int hoursPerUse, String usageMessage) {
        this.name = name;
        this.description = description;
        this.durability = durability;
        this.durabilityPerUse = durabilityPerUse;
        this.hoursPerUse = hoursPerUse;
        this.usageMessage = usageMessage;
    }
    public Item(String name, String description, int durability, int durabilityPerUse, int hoursPerUse, String usageMessage, Player player) {
        this.name = name;
        this.description = description;
        this.durability = durability;
        this.durabilityPerUse = durabilityPerUse;
        this.hoursPerUse = hoursPerUse;
        this.usageMessage = usageMessage;
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean use() {
        durability -= durabilityPerUse;
        if(usageMessage != "") {
            System.out.println(usageMessage);
        }
        if(player != null) {
            player.useHours(hoursPerUse);
        }
        if (durability <= 0) {
            return false; // TODO: implement item destruction
        }
        return true;
    }
    public String toString() {
        return name;
    }
}
