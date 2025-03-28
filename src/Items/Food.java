package Items;
import People.*;
public class Food extends Item {
    
    private int healthiness;
    public Food(String name, String description, int healthiness) {
        super(name, description, 100, 100);
        this.healthiness = healthiness;
    }
    public void use(Player player){
        super.use();
       player.addHealth(healthiness);
    }
}
