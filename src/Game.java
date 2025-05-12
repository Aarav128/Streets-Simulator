import java.util.ArrayList;
import java.util.Scanner;

import GameClasses.Location;
import Items.Item;
import Items.Vehicle;
import Items.Weapon;
import Items.Food;
import People.NPC;
import People.Person;
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
        ArrayList<Item> playerInventory = new ArrayList<Item>();
        playerInventory.add(new Weapon("Knife", "Sharp", 100, 20, 20));
        ArrayList<Item> momInventory = new ArrayList<Item>();
        momInventory.add(new Weapon("Knife", "Sharp", 30, 20, 20));

        player = new Player("Luke", 500, new NPC("Mom", (int)(Math.random() * 70 + 30), Item.EMPTY_INVENTORY, home, "Hi son! Hope you're doing well.", false), new NPC("Dad", (int)(Math.random() * 70 + 30), Item.EMPTY_INVENTORY, home, "Hi son! Hope you're doing well.", false), home, 0, 16, playerInventory);
        // Item car = new Item("Chair", "Sit on this", 2394, 2, 1);
        // Weapon katana = new Weapon("Katana", "Sharp", 100, 2, 50);
        // Weapon bazooka = new Weapon("Bazooka", "U died", 1000, 20, 500)
        System.out.println(player.getInventory());
        player.equipWeapon();
        System.out.println(player.getWeaponSlot());

    }
    void gameOver() {

    }

    public boolean simulateOneDay() {
        if (day != 1) {

            for (Location[] row : cityMap) {
                for (Location loc : row) {
                    if (loc != null) {
                        loc.simulateMorning();
                    }
                }
            }

            boolean survived = player.simualateMorning();
            if(!survived) {
                System.out.println("Unfortunately, you died in your sleep. Game over!");
                gameOver();
                return false;
            }
        }
        home.addItem(new Food("Breakfast", "Eat this to have a productive day", (int) (Math.random() * 4 + 3), (int) (Math.random() * 2 + 1)));

        while(player.getRemainingHours() > 0) {
            if(!simulateAction()) {
                gameOver();
                return false;
            };
        }

        day++;
        return true;
    }
    
    public boolean simulateAction() {
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
        boolean completedAction = false;
        while (!completedAction) {
            String action = scanner.nextLine();
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
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid response");
                        }
                    }
                    completedAction = true;
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
                    System.out.println("Please enter the number of the item you want to use right now");
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
                case "t":
                    ArrayList<Person> ppl = player.getLocation().getCharacters();
                    if(ppl.size() == 1) {
                        System.out.println("You are the only one here"); 
                    } else {
                        for (int i = 1; i < ppl.size(); i++) {
                            System.out.println(i + ": " + ppl.get(i));
                        }
                        System.out.println("Please enter the number of the person you want to interact with");
                        while (!completedAction) {
                            try {
                                int index = scanner.nextInt();
                                if (index < 1 || index >= ppl.size()) {
                                    System.out.println("Invalid response, please try again");
                                    continue;
                                }
                                NPC n = (NPC)ppl.get(index);
                                System.out.println(n + ": \"" + n.getPersonality() + "\"");
                                if (!n.isFightable()) {
                                    System.out.println("This person has nothing else to say. Press enter to continue");
                                    completedAction = true;
                                } else {
                                    System.out.println(n + " is angry! Get ready to fight");
                                    completedAction = true;
                                    return fightSequence(n);
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Invalid response, please try again");
                            }
                        }
                    }
                default:
                    System.out.println("Invalid action");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        return true;
        
    }

    private boolean fightSequence(NPC n) {
        boolean fighting = true;
        n.equipWeapon();
        player.equipWeapon();
        Weapon myWeapon = player.getWeaponSlot();
        Weapon yourWeapon = n.getWeaponSlot();
        int myPower = 5;
        int yourPower = 5;
        if (yourWeapon == null) {
            System.out.println(n + " has no weapon. This should be easy.");
        } else {
            System.out.println(n + " is using a " + n.getWeaponSlot());
            yourPower = yourWeapon.getPower();
        }

        if (myWeapon == null) {
            System.out.println("You have no weapon! Watch out");
        } else {
            System.out.println("You are using a " + n.getWeaponSlot());
            myPower = myWeapon.getPower();
        }

        
        while (fighting) {
            System.out.println("Your turn. Hit 1 to attack and 2 to flee");
            try {
                int action = scanner.nextInt();
                scanner.nextLine();
                if (action == 1) {
                    int dmg = myPower + (int) (Math.random() * 4 - 4);
                    if(myWeapon == null) {
                        System.out.println("You punch with your bare fists");
                    } else {
                        boolean x = myWeapon.use();
                        if (!x) {
                            myWeapon = null;
                            player.dropWeapon();
                        }
                    }
                    if(yourWeapon != null && Math.random() > 0.7) {
                        System.out.println(n + " partially blocked your attack.");
                        boolean x = yourWeapon.block(n);
                        if (!x) {
                            yourWeapon = null;
                            n.dropWeapon();
                        }
                        dmg /= 2;
                    }
                    System.out.println("You dealt " + dmg + " damage.");
                    boolean won = !n.takeDamage(dmg);
                    if (won) {
                        System.out.println("You won! " + n + " died.");
                        int cash = (int)(Math.random() * 12);
                        System.out.println("The contents of their inventory have spilled onto the floor.\nYou pick up $" + cash);
                        player.addCash(cash);
                        for(Item i : n.getInventory()) {
                            player.getLocation().addItem(i);
                        }
                        player.getLocation().removeCharacter(n);
                        fighting = false;
                        player.useHours((int) (Math.random() * 7));
                        return true;
                    }
                } else if (action == 2) {
                    if (Math.random() > .7 || ((int) (Math.random() * 10) < player.getRemainingHours() && player.getRemainingHours() > 3)) {
                        System.out.println("You were able to escape the fight. You ran all the way home");
                        player.moveLocation(home);
                        player.useHours((int) (Math.random() * 7));
                        fighting = false;
                        return true;
                    }
                    else {
                        System.out.println("You failed to escape and lost your chance to attack");
                    }
                } else {
                    System.out.println("Invalid response! You stalled.");
                }
            } catch (Exception e) {
                System.out.println("Invalid response! You stalled.");
            }

            
            System.out.println(n + "'s turn. ");
            boolean attacks = Math.random() > 0.3;
            if (attacks) {
                int dmg = yourPower + (int) (Math.random() * 4 - 4);
                if(yourWeapon == null) {
                    System.out.println("They punch you with their bare fists");
                } else {
                    boolean x = yourWeapon.use();
                    if (!x) {
                        yourWeapon = null;
                        n.dropWeapon();
                    }
                }

                if(myWeapon != null && Math.random() > 0.7) {
                    System.out.println("You partially blocked that attack.");
                    boolean x = myWeapon.block(n);
                    if (!x) {
                        myWeapon = null;
                        player.dropWeapon();
                    }
                    dmg /= 2;
                }

                System.out.println("You took " + dmg + " damage.");
                boolean lost = !n.takeDamage(dmg);
                if (lost) {
                    System.out.println("You could not get up after that fatal strike.");
                    return false;
                }
            } else {
                System.out.println(n + " stalls");
            }
        }
        return true;
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
