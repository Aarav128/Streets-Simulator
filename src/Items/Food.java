package Items;
import People.*;
public class Food extends Item {
    
    private int healthiness;
    private int hoursGained;
    public Food(String name, String description, int healthiness, int hoursRecovered) {
        super(name, description, 100, 100, -hoursRecovered, 
            "That food tasted good and has you feeling energized and healthy.");
        this.healthiness = healthiness;
    }
    public void use(Player player){
        super.use();
        player.addHealth(healthiness);
        player.useHours(-hoursGained);
    }
}
