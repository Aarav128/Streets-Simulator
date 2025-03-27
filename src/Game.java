public class Game {
    public Game() {
        Player player = new Player("Luke Staley");
        Location home = new Location("Home");
        Location street = new Location("Street");
        Location school = new Location("School");
        school.createAdjacency(street);
        street.createAdjacency(home);
    }
}
