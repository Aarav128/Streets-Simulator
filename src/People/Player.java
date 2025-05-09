package People;
import java.lang.reflect.Array;
import java.util.ArrayList;

import GameClasses.Location;
import Items.*;

public class Player extends Person{
    private NPC mom;
    private NPC dad;
    private Location home;
    private String school;
    private int dailyHours;
    private int usedHours;
    private int intelligence;

    private double salary;
    private String job; 
    private double cash;

    private Vehicle transportation;
    
    public Player(Location[][] cityMap, String name, int age, int health, NPC mom,NPC dad, Location home,int cash, int dailyHours){
        super(cityMap, name, age, health, Item.EMPTY_INVENTORY, home);
        this.mom = mom;
        this.dad = dad;
        this.home = home;
        this.cash = cash;
        this.dailyHours = dailyHours;
    }
    public Location getHome() {
        return home;
    }
    public boolean useHours(int hrs) {
        // change hours stuff
        // check if day is over
        return true;
    }
    public void moveLocation(Location location) {
        boolean moved;
        if (transportation == null) {
            moved = useHours(3);
        } else {
            moved = useHours(3 - transportation.getSpeed());
        }
        if (moved) {
            // remove yourself from prevoius location
            // add yourself to current location
            setLocation(location);
            System.out.println("You traveled to " + location + ".");
        }
    }
    
}
