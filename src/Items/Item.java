package Items;

import People.Player;

public class Item { // types: Weapon, Food, Car
    private String name;
    private String description;
    private int durability;
    private int durabilityPerUse;
    private int hoursPerUse;
    private boolean exists = true;
    private Player player;
    public Item(String name, String description, int durability, int durabilityPerUse, int hoursPerUse) {
        this.name = name;
        this.description = description;
        this.durability = durability;
        this.durabilityPerUse = durabilityPerUse;
        this.hoursPerUse = hoursPerUse;
    }
    public Item() {

    }
    public void use() {
        durability -= durabilityPerUse;
        if (durability <= 0) {
            exists = false; // TODO: implement item destruction
        }
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public String toString() {
        return name;
    }
}
