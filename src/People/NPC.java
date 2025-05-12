package People;
import java.util.ArrayList;

import GameClasses.Location;
import Items.Item;

public class NPC extends Person{
    private String personality;
    private boolean fightable;
    
    public String getPersonality() {
        return personality;
    }

    public boolean isFightable() {
        return fightable;
    }

    public NPC(Location[][] cityMap, String name, int health, ArrayList<Item> inventory, Location currentLocation, String personality, boolean fightable) {
        super(cityMap, name, health, Item.EMPTY_INVENTORY, currentLocation);
        this.fightable = fightable;
        this.personality = personality;
    }


}
