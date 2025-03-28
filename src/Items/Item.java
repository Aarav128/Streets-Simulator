package Items;
public class Item { // types: Weapon, Food, Car
    private String name;
    private String description;
    private int durability;
    private int durabilityPerUse;
    boolean exists = true;
    public Item(String name, String description, int durability, int durabilityPerUse) {
        this.name = name;
        this.description = description;
        this.durability = durability;
        this.durabilityPerUse = durabilityPerUse;
    }
    public Item() {

    }
    public void use() {
        durability -= durabilityPerUse;
        if (durability <= 0) {
            exists = false; // TODO: implement item destruction
        }
    }
}
