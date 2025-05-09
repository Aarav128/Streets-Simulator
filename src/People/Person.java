package People;
import java.util.ArrayList;
import Items.Item;
import Items.Weapon;
import GameClasses.Location;
public class Person {
    private String name;
    private int age;
    private int health;//if true no problem if false chance of dying
    private int popularity;// ranges 1-100
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private Location[][] cityMap;
    private Weapon weaponSlot;
    private Location currentLocation;
    public Person() {
        
    }
    public Person(Location[][] cityMap, String name, int age, int health, int popularity, ArrayList<Item> inventory) {
        this.cityMap = cityMap;
        this.name = name;
        this.age = age;
        this.health = health;
        this.popularity = popularity;
        this.inventory = inventory;
    }
    public Person(Location[][] cityMap, String name, int age, int health, int popularity, Location currentLocation) {
        this.cityMap = cityMap;
        this.name = name;
        this.age = age;
        this.health = health;
        this.popularity = popularity;
        this.inventory = new ArrayList<Item>();
        this.currentLocation = currentLocation;
        currentLocation.addCharacter(this);
    }
    public void equipItem(Item item) {
        inventory.add(item);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void addHealth(int n) {
        health += n;
    }


    public void equipWeapon() {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof Weapon) {
                if (weaponSlot == null || ((Weapon)item).getPower() > weaponSlot.getPower()) {
                    Weapon weapon = (Weapon)inventory.remove(i);
                    weaponSlot = weapon;
                    i--;
                }
            }
        }
    }

    public void dropWeapon() {
        if (weaponSlot != null) {
            Weapon c = weaponSlot;

        }
    }

    public Location getLocation() {
        return currentLocation;
    }

    public void setLocation(Location newLocation) {
        currentLocation.removeCharacter(this);
        newLocation.addCharacter(this);
        currentLocation = newLocation;
    }

    public Weapon getWeaponSlot() {
        return weaponSlot;
    }
}
