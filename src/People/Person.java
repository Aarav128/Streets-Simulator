package People;
import java.util.ArrayList;
import Items.Item;
import Items.Weapon;
import GameClasses.Location;
public class Person {
    private String name;
    private int health;//if true no problem if false chance of dying
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private Location[][] cityMap;
    private Weapon weaponSlot;
    private Location currentLocation;
    public Person(Location[][] cityMap, String name, int health, ArrayList<Item> inventory, Location currentLocation) {
        this.cityMap = cityMap;
        this.name = name;
        this.health = health;
        this.inventory = new ArrayList<Item>();
        this.currentLocation = currentLocation;
        currentLocation.addCharacter(this);
    }
    public void equipItem(Item item) {
        inventory.add(item);
    }

    public void useItem(Item item) {
        boolean exists = item.use();
        if (!exists) {
            removeItemFromInventory(item);
        }
    }

    public int useWeapon() {
        boolean exists = weaponSlot.use();
        int power = weaponSlot.getPower();
        if(!exists) {
            weaponSlot = null;
        }
        return power;
    }

    public void removeItemFromInventory(Item i) {
        int index = inventory.indexOf(i);
        if (index != -1) {
            inventory.remove(index);
        }
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void addHealth(int n) {
        health += n;
    }

    public int getHealth() {
        return health;
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
    public boolean simualateMorning() {
        health += (int)(Math.random() * 10 - 5); // random health change
        if (Math.random() * 100 >= health) {
            return false; // death
        }
        return true;
    }

    public String toString() {
        return name;
    }
}
