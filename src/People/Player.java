package People;

import GameClasses.Location;
import Items.*;

public class Player extends Person{
    private static NPC mom;
    private static NPC dad;
    private Location home;
    private int dailyHours;
    private int usedHours;
    private int intelligence;

    private double salary;
    private boolean employed;
    private int cash;

    private Vehicle transportation;
    private final int RENT_INCREASE = 5; // per parent missing
    
    public Player(String name, int health, NPC mom, NPC dad, Location home, int cash, int dailyHours){
        super(name, health, Item.EMPTY_INVENTORY, home);
        Player.mom = mom;
        Player.mom = dad;
        this.home = home;
        this.cash = cash;
        this.dailyHours = dailyHours;
        this.employed = false;
        this.intelligence = 35;
    }

    
    public int getHours() {
        return dailyHours - usedHours;
    }

    public void getHired(String jobTitle) {
        if (intelligence > 50) {
            System.out.println("Congratulations! You have been hired. Make sure you keep yourself constantly educated so you can work well");
            employed = true;
            salary = 5;
        }
    }

    public void work(int hours) {
        if (hours + usedHours >= dailyHours) {
            System.out.println("You can't work that long! It'll be too late");
        }
        else {
            useHours(hours);
            cash += salary * hours;
            System.out.println("You worked for " + hours + " hours and earned $" + (salary * hours));
        }
    }


    public void goToSchool() {
        if (dailyHours - usedHours > 10) {
            useHours(4);
            System.out.println("You went to school. You spent a lot of hours there, but you feel smarter");
            intelligence += 5;
        } 
        else {
            System.out.println("School's closed");
        }
    }

    @Override
    public boolean simualateMorning() {
        usedHours = 0;
        intelligence -= (int) (Math.random() * 3);
        if(!getLocation().equals(home)) {
            System.out.println("You feel groggy. Today won't be as productive.");
            useHours(2);
            setLocation(home);
        }
        if (!employed && (mom != null || dad != null)) {
            System.out.println("You recieved an allowance of 5 dollars. Be thankful.");
            cash += 5;
        } 
        int rentPayment = 0;
        String rentMessage = "";
        if (dad == null) {
            addHealth(-1);
            rentPayment += RENT_INCREASE;
            rentMessage += "Because dad isn't around, you have to pay some rent.\n";
        }
        if (mom == null) {
            addHealth(-1);
            rentPayment += RENT_INCREASE;
            rentMessage += "Because mom isn't around, you have to pay some rent.\n";
        }

        if (rentPayment == 0) {
            rentMessage = "Lucky you, your parents paid all the rent";
        }
        // rent payment;
        cash -= rentPayment; 
        System.out.println(rentMessage);
        System.out.print(rentPayment > 0 ? "You have to pay a rent of $" + rentPayment + "\n" : "");
        if (cash < 0) {
            addHealth(-100);
            System.out.println("You did not have enough money to pay today's rent. \n" +
                "You have been evicted and a pack of wolves are feasting on you.");
        }
        return super.simualateMorning();
    }

    public int getRemainingHours() {
        return dailyHours - usedHours;

    }

    
    public Location getHome() {
        return home;
    }
    public boolean useHours(int hrs) {
        // change hours stuff
        // check if day is over
        usedHours += hrs;
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
    
    public boolean isEmployed() {
        return employed;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCash() {
        return cash;
    }

    public static void momDeath() {
        mom = null;
    }

    public static void dadDeath() {
        dad = null;
    }
}
