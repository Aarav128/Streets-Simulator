package Items;
public class Weapon extends Item {
    // TODO
    int strength;

    public Weapon(String name, String description, int durability, int durabilityPerUse, int strength) {
        super(name, description, durability, durabilityPerUse);
        this.strength = strength;
    }
    public int getStrength() {
        return strength;
    }
}
