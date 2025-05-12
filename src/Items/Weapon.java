package Items;

import People.Player;
import People.Person;

public class Weapon extends Item {
    int power;
    Person holder;
    public Weapon(String name, String description, int durability, int durabilityPerUse, int power) {
        super(name, description, durability, durabilityPerUse, 0, "");
        this.power = power;
    }
    public int getPower() {
        return power;
    }

    public boolean use(Person holder) { // non-weapons don't need a holder here
        if (this.holder instanceof Player) {
            System.out.println("You attacked!");
        } else {
            System.out.println(holder + " attacked you.");
        }
        boolean exists = super.use();
        if (!exists) {
            System.out.println("Oops! " + (holder instanceof Player ? "your weapon" : holder + "'s weapon") + " broke after that.");
        } 
        return exists;
    }

    public boolean block(Person holder) {
        if (Math.random() * 100 <= power) {
        if (this.holder instanceof Player) {
            System.out.println("You blocked the attack!");
        } else {
            System.out.println(holder + " blocked your attack");
        }
        }
        boolean exists = super.use();
        if (!exists) {
            System.out.println("Oops! " + (holder instanceof Player ? "Your weapon" : holder + "'s weapon") + " broke after that.");
        }
        return exists;
    }

}
