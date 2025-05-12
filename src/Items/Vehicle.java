package Items;

public class Vehicle extends Item{
    int speed; // reduces hours cost to move by speed
    public int getSpeed() {
        return speed;
    }
    public Vehicle(String name, String description, int speed) {
        super(name, description, 100, 10, 0, "");
        this.speed = speed;
    }

    @Override
    public boolean use() {
        System.out.println("Your " + this + " saved you " + speed + " hours on the journey");
        boolean exists = super.use();
        if (!exists) {
            System.out.println("Oops! " + this + " broke after that.");
        }
        return exists;
    }
}
