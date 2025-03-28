import java.util.ArrayList;

import Items.Item;
public class Person {
    private String name;
    private int age;
    private int health;//if true no problem if false chance of dying
    private int salary;
    private String job; 
    private int popularity;// ranges 1-100
    private ArrayList<Item> inventory;
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
}
