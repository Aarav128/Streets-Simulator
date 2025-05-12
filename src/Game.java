import java.util.ArrayList;
import java.util.Scanner;

import GameClasses.Location;
import Items.Item;
import Items.Vehicle;
import Items.Weapon;
import Items.Food;
import People.NPC;
import People.Player;
public class Game {
    Location[][] cityMap;
    Player player;
    Scanner scanner;
    Location school;
    Location walmart;
    Location home;
    int day = 1;
    public Game() {
        scanner = new Scanner(System.in);
        home = new Location("Home");
        school = new Location("School");
        walmart = new Location("Walmart");
        
        cityMap = new Location[][]{ 
            {null, home, null},
            {null, new Location("Street 1"), school},
            {walmart, new Location("Street 2"), new Location("Street 3")}
        };
        player = new Player("Luke", 100, new NPC("Mom", 100, Item.EMPTY_INVENTORY, home, "Hi son! Hope you're doing well.", false), new NPC("Dad", 100, Item.EMPTY_INVENTORY, home, "Hi son! Hope you're doing well.", false), home, 0, 10);

        // Item car = new Item("Chair", "Sit on this", 2394, 2, 1);
        // Weapon katana = new Weapon("Katana", "Sharp", 100, 2, 50);
        // Weapon bazooka = new Weapon("Bazooka", "U died", 1000, 20, 500)

    }
    void gameOver() {

    }

    public void simulateOneDay() {
        if (day != 1) {
            boolean survived = player.simualateMorning();
            if(!survived) {
                System.out.println("Unfortunately, you died in your sleep. Game over!");
                gameOver();
                return;
            }

            for (Location[] row : cityMap) {
                for (Location loc : row) {
                    if (loc != null) {
                        loc.simulateMorning();
                    }
                }
            }
        }
        home.addItem(new Food("Breakfast", "Eat this to have a productive day", (int) (Math.random() * 4 + 3), (int) (Math.random() * 2 + 1)));

        while(player.getRemainingHours() > 0) {
            simulateAction();
        }

        day++;
    }
    
    public void simulateAction() {
        System.out.println("You are at " + player.getLocation());
        System.out.println("There are " + player.getHours() + " hours left in the day");
        System.out.println("Health: " + player.getHealth() + 
        "\nIntelligence: " + player.getIntelligence() + 
        "\nCash: " + player.getCash());
        System.out.println("What would you like to do?");
        String display = "(m) Move between locations\n(i) View items in this location \n(u) View your inventory or use an item \n(t) Interact with people here";

        boolean canSchool = player.getLocation().equals(school);
        boolean canBeHired = !player.isEmployed() && player.getLocation().equals(walmart);
        boolean canWork = player.isEmployed() && player.getLocation().equals(walmart);


        // m, i, u, t, s, h, w
        if (canSchool) {
            display += "\n(s) Spend some time at school";
        }
        if (canBeHired) {
            display += "\n(h) Interview for a job at walmart";
        }
        if (canWork) {
            display += "\n(w) Work for some cash";
        }

        System.out.println(display);
        String action = scanner.nextLine();
        boolean completedAction = false;
        while (!completedAction) {
            switch(action) {
                case "m":
                    System.out.println("Pick the number of the location you would like to move to.");
                    ArrayList<Location> possibleSpots = getAdjacentLocations(player.getLocation().getCoordinates(cityMap));
                    for (int i = 0; i < possibleSpots.size(); i++) {
                        System.out.println(i + 1 + ". " + possibleSpots.get(i));
                    }
                    while (!completedAction) {
                        try {
                            Location newLocation = possibleSpots.get(scanner.nextInt() - 1); 
                            scanner.nextLine();
                            player.moveLocation(newLocation);
                            completedAction = true;
                        } catch (Error e) {
                            System.out.println("Please enter a valid number from the ones provided.");
                        }
                    }                
                    break;
                case "i":
                    ArrayList<Item> items = player.getLocation().getItems(); 
                    if (items.size() == 0){
                        System.out.println("There is nothing here.");
                    } else {
                        System.out.println("You can pick up an item instantly. Pick the number of the item you'd like to pick up, otherwise enter 0");
                        for (int i = 0; i < items.size(); i++) {
                            System.out.println(i + 1 + ". " + items.get(i) + ": " + items.get(i).getDescription());
                        }
                        try {
                            int index = scanner.nextInt() - 1;

                            scanner.nextLine();
                            if (index < items.size() && index >= 0) {
                                Item i = items.get(index); // check for out of bounds
                                player.getLocation().getItems().remove(index);
                                player.equipItem(i);
                                System.out.println("You picked up the " + i);
                                completedAction = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid response");
                        }
                    }
                    break;
                case "u":
                    ArrayList<Item> inventory = player.getInventory(); 
                    System.out.println("Here's your inventory. Note that you can't use some items right now");
                    ArrayList<Integer> usableItems = new ArrayList<Integer>();
                    for(int i = 0; i < inventory.size(); i++) {
                        Item item = inventory.get(i);
                        String msg = (i + 1) + ". " + item;
                        if (item instanceof Food) {
                            msg += ": You can eat this for some health and energy";
                            usableItems.add(i);
                        } else if (item instanceof Vehicle) {
                            msg += ": You can't use this right now, but you can use it if you chose to move to another location";
                        } else if (item instanceof Weapon) {
                            msg += ": You can't use this right now, but if this is your best weapon, you will automatically equip this in a fight";
                        }
                        System.out.println(msg);
                    }
                    System.out.println("Please enter the index of the item you want to use right now");
                    while(!completedAction) {
                        try {
                            int index = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if(usableItems.indexOf((Integer) index) != -1) {
                                Item i = inventory.get(index);
                                if (i instanceof Food) {
                                    player.addHealth(((Food)i).getHealthiness());
                                }
                                boolean alive = i.use();
                                player.useHours(i.getHoursPerUse());
                                if(!alive) {
                                    player.removeItemFromInventory(inventory.get(index));
                                }
                                completedAction = true;
                            } else {
                                System.out.println("You can't use that! Please try again.");
                            }
                        }
                        catch (Exception e) {
                            System.out.println(e.toString());
                            System.out.println("Invalid response, please try again");
                        }
                    }
                    break;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        
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
