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
        // Create the city
        scanner = new Scanner(System.in);
        home = new Location("Home");
        school = new Location("School");
        walmart = new Location("Walmart");

        Location dangerVille = new Location("Dangerous Street");
        ArrayList<Item> villainsWeapon = new ArrayList<Item>();
        villainsWeapon.add(new Weapon("Pistol", "It's very deadly", 100, 3, 40));
        NPC boss = new NPC("Big Villain (scary)", 400, villainsWeapon, dangerVille, "He's really scary.", true);
        boss.equipWeapon();

        @SuppressWarnings("unused")
        NPC friend1 = new NPC("Luke", 100, Item.EMPTY_INVENTORY, school, "Ralph has been terrorizing this school... check to see if you can find any weapons around us.", false);
        @SuppressWarnings("unused")
        NPC friend2 = new NPC("My Bestie", 10, Item.EMPTY_INVENTORY, school, "I don't have too long.. Ralph already got me... please get him", false);
        NPC schoolBully = new NPC("Ralph", 150, Item.EMPTY_INVENTORY, school, "He's the big bad bully. He has a nice car that you want.", true);
        schoolBully.equipItem(new Weapon("Ralph's Fists", "They're stronger than yours", 100, 0, 15));
        schoolBully.equipItem(new Vehicle("Car", "It's very fast", 2));
        school.addItem(new Weapon("Glass shard", "Sharp", 30, 10, 45));

        ArrayList<Item> playerInventory = new ArrayList<Item>();
        playerInventory.add(new Food("Energy Drink","Drink in moderation", -10, 8));
        playerInventory.add(new Food("Energy Drink","Drink in moderation", -10, 8));
        playerInventory.add(new Item("Phone", "It's a waste of time.", 100, 1, 4, "You just wasted four entire hours of your day."));
        home.addItem(new Vehicle("Bike", "It's kinda fast", 1));
        home.addItem(new Item("IPad", "Let's have fun.", 100, 0, 2, "You just wasted two hours playing Roblox"));
        player = new Player("Player", 100, new NPC("Mom", (int)(Math.random() * 50) + 30, Item.EMPTY_INVENTORY, home, "Hi son! Hope you're doing well.", true), new NPC("Dad", (int)(Math.random() * 70 + 30), Item.EMPTY_INVENTORY, home, "Hi son! Hope you're doing well.", false), home, 40, 16, playerInventory);


        for (int i = 0; i < 5; i++) {
            walmart.addItem(new Food("Health Bar", "It's good for your health", 20, 1, 4));
        }

        for (int i = 0; i < 5; i++) {
            walmart.addItem(new Food("Coffee", "Don't go overboard.", -4, 3, 6));
        }

        walmart.addItem(new Weapon("Knife", "Sharp", 100, 15, 20, 100));
        walmart.addItem(new Vehicle("Racecar", "It takes you places practically instantly", 3, 250));
        walmart.addItem(new Vehicle("Time Machine Teleportation Device", "It takes you places in negative time", 4, 10000));


        cityMap = new Location[][]{ 
            {null, home, null},
            {dangerVille, new Location("Street 1"), school},
            {walmart, new Location("Street 2"), new Location("Street 3")}
        };
        
        System.out.println("You have spawned");
        System.out.println("The end goal is to kill the villain....");
        System.out.println("But first, I suggest you go to school to meet your dying friend.. and deal with the bully");
        System.out.println("\n\n\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    void gameOver() {
        System.out.println("Welp... you died. Better luck next time");
        System.out.println("You lived for " + day + " days");
        System.out.println("You died with $" + player.getCash() + " on hand");
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        home.addItem(new Food("Breakfast", "Eat this to have a productive day", (int) (Math.random() * 4 + 3), (int) (Math.random() * 2 + 1)));
        System.out.println("Today is day " + day);
        while(player.getRemainingHours() > 0) {
            if(!simulateAction()) {
                gameOver();
                return false;
            };
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        boolean canBuy = player.getLocation().equals(walmart);
        boolean canSleep = player.getLocation().equals(home);


        // m, i, u, t, s, h, w, p, n
        if (canSchool) {
            display += "\n(s) Spend some time at school";
        }
        if (canBeHired) {
            display += "\n(h) Interview for a job at walmart";
        }
        if (canWork) {
            display += "\n(w) Work for some cash";
        }
        if (canBuy) {
            display += "\n(p) Buy something from Walmart";
        }
        if (canSleep) {
            display += "\n(n) Sleep the night away";
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
                    try {
                        int x = scanner.nextInt() - 1;
                        Location newLocation = possibleSpots.get(x); 
                        scanner.nextLine();
                        player.moveLocation(newLocation);
                    } catch (Exception e) {
                        System.out.println("Please enter a valid number from the ones provided.");
                    }             

                    completedAction = true;
                    break;
                case "i":
                    if(player.getLocation().equals(walmart)) {
                        completedAction = true;
                        System.out.println("You need money to get stuff from walmart");
                    } else {
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
                        completedAction = true;
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
                                    scanner.nextLine();
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
                    break;
                case "s":
                    if (!canSchool) {
                        System.out.println("Invalid action");
                    } else {
                        player.goToSchool();
                    }
                    completedAction = true;
                    break;
                case "h":
                    if (!canBeHired) {
                        System.out.println("Invalid action");
                    } else {
                        player.getHired("Walmart");
                    }
                    completedAction = true;
                    break;
                case "w":
                    if (!canWork) {
                        System.out.println("Invalid action");
                        completedAction = true;
                    } else {
                        int hoursWorkable = player.getRemainingHours() > 8 ? 8 : player.getRemainingHours();
                        System.out.println("You can work up to " + hoursWorkable + " hours. How many would you like to work?");
                        while(!completedAction) {
                            try {
                                int hoursWorked = scanner.nextInt();
                                scanner.nextLine();
                                if(hoursWorked > 0) {
                                    player.work(hoursWorked);
                                    completedAction = true;
                                } else {
                                    System.out.println("Invalid response, please enter a number in the correct range");
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Invalid response, please enter a number in the correct range");
                            }
                        }
                    }
                    break;
                case "p":
                    if (!canBuy) {
                        System.out.println("Invalid action");
                        completedAction = true;
                    } else {
                        ArrayList<Item> stock = walmart.getItems();
                        if(stock.size() == 0) {
                            System.out.println("Oops! We're all out!");
                        } else {
                            System.out.println("Welcome to Walmart! Here's what we have today.");
                            for (int i = 0; i < stock.size(); i++) {
                                Item item = stock.get(i);
                                System.out.println((i + 1) + ". $" + item.getPrice() + " - " + stock.get(i) + ": " + item.getDescription());
                            }
                            System.out.println("Select the item you want to buy by its catalog number.");
                            try {
                                int index = scanner.nextInt() - 1;
                                scanner.nextLine();
                                if (index < stock.size() && index >= 0) {
                                    Item i = stock.get(index); // check for out of bounds
                                    if (i.getPrice() < player.getCash()) {
                                        System.out.println("You purchased the " + i);
                                        walmart.getItems().remove(index);
                                        player.equipItem(i);
                                        player.addCash(-i.getPrice());
                                    } else {
                                        System.out.println("You're too poor for this. Get your money up and come back.");
                                    }
                                    completedAction = true;
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid response");
                            }
                        }
                    }
                    break;
                case "n":
                    if (!player.getLocation().equals(home)) {
                        System.out.println("Invalid action");
                    }
                    else {
                        player.sleep();
                    }
                    completedAction = true;
                    break;
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
            System.out.println(n + " has " + n.getHealth() + " hp remaining.");
            System.out.println("You have " + player.getHealth() + " hp remaining.\n");
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
                boolean lost = !player.takeDamage(dmg);
                if (lost) {
                    System.out.println("You could not get up after that fatal strike.");
                    return false;
                }
            } else {
                System.out.println(n + " stalls");
            }
            System.out.println("\n\n\n");
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
