public class Main{
  public static void main(String[] args) {
    while (true) {
      Game game = new Game();
      boolean alive = true;
      while (alive) {
        alive = game.simulateOneDay();
      }
      System.out.println("You will respawn momentarily to try again");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
