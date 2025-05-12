package Items;
public class Food extends Item {
    
    private int healthiness;
    public Food(String name, String description, int healthiness, int hoursRecovered) {
        super(name, description, 100, 100, -hoursRecovered, 
            "That food tasted good and has you feeling energized and healthy.");
        this.healthiness = healthiness;
    }
    public Food(String name, String description, int healthiness, int hoursRecovered, int price) {
        super(name, description, 100, 100, -hoursRecovered, 
            "That food tasted good and has you feeling energized and healthy.", price);
        this.healthiness = healthiness;
    }

    public int getHealthiness() {
        return healthiness;
    }
}
