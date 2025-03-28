import GameClasses.Location;
import Items.Item;
import Items.Vehicle;
import Items.Weapon;
import People.Player;

public class Game {
    public Game() {
        Player player = new Player("Luke Staley");
        Location home = new Location("Home");
        Location street = new Location("Street");
        Location school = new Location("School");
        school.createAdjacency(street);
        street.createAdjacency(home);

        Item car = new Item("Chair", "Sit on this", 2394, 2);
        Weapon katana = new Weapon("Katana", "Sharp", 100, 2, 50);
        Weapon bazooka = new Weapon("Bazooka", "U died", 1000, 20, 500);

        player.equipItem(car);
        player.equipItem(katana);
        System.out.println(player.getInventory());
        player.equipItem(bazooka);
        player.equipWeapon();
        System.out.println(player.getWeaponSlot());
        System.out.println(player.getInventory());
    }
    
}
