public class Main{
  public static void main(String[] args) {
    while (true) {
      Game game = new Game();
      boolean alive = true;
      while (alive) {
        alive = game.simulateOneDay();
      }
      System.out.println("That's kind of sad... but I'm nice enough to give you another chance.");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
