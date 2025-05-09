package People;
import java.util.ArrayList;

import GameClasses.Location;
import Items.Item;

public class NPC extends Person{
    private String personality;
    
    public NPC(Location[][] cityMap, String name, int age, int health, ArrayList<Item> inventory, Location currentLocation, String personality) {
        super(cityMap, name, age, health, Item.EMPTY_INVENTORY, currentLocation);
        this.personality = personality;
    }
}
