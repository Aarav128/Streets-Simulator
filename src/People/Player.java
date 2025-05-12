package People;
import java.lang.reflect.Array;
import java.util.ArrayList;

import GameClasses.Location;
import Items.*;

public class Player extends Person{
    private static NPC mom;
    private static NPC dad;
    private Location home;
    private String school;
    private int dailyHours;
    private int usedHours;
    private int intelligence;

    private double salary;
    private String job; 
    private double cash;

    private Vehicle transportation;
    private final int RENT = 10; // if both parents
    private final int RENT_INCREASE = 5; // per parent missing
    
    public Player(Location[][] cityMap, String name, int health, NPC mom, NPC dad, Location home,int cash, int dailyHours){
        super(cityMap, name, health, Item.EMPTY_INVENTORY, home);
        Player.mom = mom;
        Player.mom = dad;
        this.home = home;
        this.cash = cash;
        this.dailyHours = dailyHours;
    }

    public int getHours() {
        return dailyHours - usedHours;
    }
    @Override
    public boolean simualateMorning() {
        int rentPayment = RENT;
        if (this.dad == null) {
            addHealth(-1);
            rentPayment += RENT_INCREASE;
        }
        if (this.mom == null) {
            addHealth(-1);
            rentPayment += RENT_INCREASE;
        }
        if(!this.getLocation().equals(home)) {
            System.out.println("You feel groggy. Today won't be as productive.");
            useHours(2);
            setLocation(home);
        }
        // rent payment;
        cash -= rentPayment; 
        if (cash < 0) {
            addHealth(-getHealth());
            System.out.println("You did not have enough money to pay today's rent. \n" +
                "You have been evicted and a pack of wolves are feasting on you.");
        }
        return super.simualateMorning();
    }

    
    public Location getHome() {
        return home;
    }
    public boolean useHours(int hrs) {
        // change hours stuff
        // check if day is over
        usedHours -= hrs;
        boolean awake = usedHours < dailyHours;
        if(!awake) {
            System.out.println("In the process of doing that, you fell asleep. \n" +
                "Somehow, you woke up at home. Lucky you.");
        }
        return awake;
    }
    public void moveLocation(Location location) {
        boolean moved;
        if (transportation == null) {
            moved = useHours(3);
        } else {
            moved = useHours(3 - transportation.getSpeed());
            if (!transportation.use()) {
                transportation = null; // vehicle has run out of durability and is destroyed
            }
        }
        if (moved) {
            // remove yourself from prevoius location
            // add yourself to current location
            setLocation(location);
            System.out.println("You traveled to " + location + ".");
        }
    }

    
    public static void momDeath() {
        mom = null;
    }

    public static void dadDeath() {
        dad = null;
    }
}
