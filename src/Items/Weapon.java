package Items;
public class Weapon extends Item {
    // TODO
    int power;

    public Weapon(String name, String description, int durability, int durabilityPerUse, int power) {
        super(name, description, durability, durabilityPerUse, 0);
        this.power = power;
    }
    public int getPower() {
        return power;
    }
}
