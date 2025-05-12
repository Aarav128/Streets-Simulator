package Items;

import People.Player;
import People.Person;

public class Weapon extends Item {
    int power;
    Person holder;
    public Weapon(String name, String description, int durability, int durabilityPerUse, int power, Person holder) {
        super(name, description, durability, durabilityPerUse, 0, "");
        this.power = power;
        this.holder = holder;
    }
    public int getPower() {
        return power;
    }
    @Override
    public boolean use() {
        if (this.holder instanceof Player) {
            System.out.println("Your " + this + " did " + power + " damage!");
        } else {
            System.out.println(holder + "'s " + this + " did " + power + " damage to you!");
        }
        boolean exists = super.use();
        if (!exists) {
            System.out.println("Oops! " + this + " broke after that.");
        }
        return exists;
    }


}
