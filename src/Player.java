import java.lang.reflect.Array;
import java.util.ArrayList;
import Items.*;

public class Player extends Person{
    private NPC mom;
    private NPC dad;
    private String name;
    private Location home;
    private double money;
    private boolean hasCar;
    private boolean hasBike;
    private String school;
    private int dailyHours;
    private int usedHours;
    private int intelligence;
    private int strength;

    private Vehicle transportation;
    public Player(String name) {
        this.name = name;
    }
    public Player(String name, NPC mom,NPC dad,Location home,int money, int dailyHours){
        this.name = name;
        this.mom = mom;
        this.dad = dad;
        this.home = home;
        this.money = money;
        this.dailyHours = dailyHours;
    }

    
}
