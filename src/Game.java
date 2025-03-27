public class Game {
    public Game() {
        Player player = new Player();
        Location home = new Location("Home");
        Location neighborhood = new Location("Neighborhood");
        neighborhood.createAdjacency(home);
    }
}
