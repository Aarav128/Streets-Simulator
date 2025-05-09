package Items;

public class Vehicle extends Item{
    int speed; // reduces hours cost to move by speed
    public int getSpeed() {
        return speed;
    }
    public Vehicle(String name, String description, int speed) {
        super(name, description, 100, 0, 0);
        this.speed = speed;
    }
}
