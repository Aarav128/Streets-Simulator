public class Main{
  public static void main(String[] args) {
    Game game = new Game();
    boolean alive = true;
    while (alive) {
      game.simulateOneDay();
    }
  }
}
