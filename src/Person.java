import java.util.ArrayList;
import Items.Item;
import Items.Weapon;
public class Person {
    private String name;
    private int age;
    private int health;//if true no problem if false chance of dying
    private int salary;
    private String job; 
    private int popularity;// ranges 1-100
    private ArrayList<Item> inventory;

    
    private Weapon weaponSlot;
    public Person() {
        
    }
    public Person(String name, int age, int health, int salary, String job, int popularity, ArrayList<Item> inventory) {
        this.name = name;
        this.age = age;
        this.health = health;
        this.salary = salary;
        this.job = job;
        this.popularity = popularity;
        this.inventory = inventory;
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
                if (weaponSlot == null || ((Weapon)item).getStrength() > weaponSlot.getStrength()) {
                    Weapon weapon = (Weapon)inventory.remove(i);
                    weaponSlot = weapon;
                    i--;
                }
            }
        }
    }
}
