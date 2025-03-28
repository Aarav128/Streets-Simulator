package People;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Location;
import Items.*;

public class Player extends Person{
    private NPC mom;
    private NPC dad;
    private String name;
    private Location home;
    private boolean hasCar;
    private boolean hasBike;
    private String school;
    private int dailyHours;
    private int usedHours;
    private int intelligence;
    private int strength;

    private double salary;
    private String job; 
    private double cash;

    private Vehicle transportation;
    public Player(String name) {
        this.name = name;
    }
    public Player(String name, NPC mom,NPC dad, Location home,int cash, int dailyHours){
        super(name, dailyHours, cash, dailyHours);
        this.name = name;
        this.mom = mom;
        this.dad = dad;
        this.home = home;
        this.cash = cash;
        this.dailyHours = dailyHours;
    }

    
}
