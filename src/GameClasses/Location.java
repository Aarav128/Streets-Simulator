package GameClasses;
import java.util.ArrayList;

import Items.Item;
import People.Person;

public class Location {
    private ArrayList<Person> characters;
    private ArrayList<Item> items; // these are items not held by any player. 

    private String name;

    public Location(String name) {
        characters = new ArrayList<Person>();
        items = new ArrayList<Item>();
        this.name = name;
    }

    public int[] getCoordinates(Location[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (this.equals(map[i][j])) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public String toString() {
        return name;
    }
    
    public void addCharacter(Person p) {
        characters.add(p);
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public ArrayList<Person> getCharacters() {
        return characters;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void removeCharacter(Person p) {
        if(characters.indexOf(p) != -1) {
            characters.remove(characters.indexOf(p));
        }
    }
    
}
