import java.util.ArrayList;
import java.util.Scanner;

import GameClasses.Location;
import Items.Item;
import Items.Vehicle;
import Items.Weapon;
import People.Player;
public class Game {
    Location[][] cityMap;
    Player player;
    Scanner scanner;
    public Game() {
        scanner = new Scanner(System.in);
        Location home = new Location("Home");
        Location school = new Location("School");
        Location walmart = new Location("Walmart");
        
        cityMap = new Location[][]{ 
            {null, home, null},
            {null, new Location("Street 1"), school},
            {walmart, new Location("Street 2"), new Location("Street 3")}
        };
        player = new Player(cityMap, "Luke", 12, 100, null, null, home, 0, 10);

        // Item car = new Item("Chair", "Sit on this", 2394, 2, 1);
        // Weapon katana = new Weapon("Katana", "Sharp", 100, 2, 50);
        // Weapon bazooka = new Weapon("Bazooka", "U died", 1000, 20, 500)
        simulateOneDay();
        System.out.println(player.getLocation());
        System.out.println(home.getCharacters());

    }

    public void simulateOneDay() {
        System.out.println("You wake up at home");
        simulateAction();
    }
    
    public void simulateAction() {
        System.out.println("What would you like to do?");
        String display = "1. Travel";
        // add more actions
        System.out.println(display);
        int action = scanner.nextInt();
        if (action == 1) {
            System.out.println("Pick the number of the location you would like to move to.");
            ArrayList<Location> possibleSpots = getAdjacentLocations(player.getLocation().getCoordinates(cityMap));
            for (int i = 0; i < possibleSpots.size(); i++) {
                System.out.println(i + 1 + ": " + possibleSpots.get(i));
            }
            Location newLocation = possibleSpots.get(scanner.nextInt() - 1); // check for out of bounds
            player.moveLocation(newLocation);
        }

    }



    private ArrayList<Location> getAdjacentLocations(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        ArrayList<Location> adjacent = new ArrayList<Location>();

        if (row > 0 && cityMap[row -1][col] != null) {
            adjacent.add(cityMap[row - 1][col]);
        }
        if (row < cityMap.length - 1 && cityMap[row + 1][col] != null) {
            adjacent.add(cityMap[row + 1][col]);
        }
        if (col > 0 && cityMap[row][col - 1] != null) {
            adjacent.add(cityMap[row][col - 1]);
        }
        if (col < cityMap[0].length - 1 && cityMap[row ][col + 1] != null) {
            adjacent.add(cityMap[row][col + 1]);
        }
        return adjacent;
    }
    
}
