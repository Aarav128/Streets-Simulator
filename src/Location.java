import java.util.ArrayList;

import Items.Item;

public class Location {
    private ArrayList<Location> adjacentLocations;
    private ArrayList<Person> characters;
    private ArrayList<Item> items; // these are items not held by any player. 
    private String name;

    public Location(String name) {
        adjacentLocations = new ArrayList<Location>();
        characters = new ArrayList<Person>();
        items = new ArrayList<Item>();
        this.name = name;
    }
    public void createAdjacency(Location other) {
        adjacentLocations.add(other);
        if(!other.getAdjacentLocations().contains(this)) {
            other.createAdjacency(this);
        }
    }

    public ArrayList<Location> getAdjacentLocations() {
        return adjacentLocations;
    }

    public String toString() {
        return name;
    }
}
