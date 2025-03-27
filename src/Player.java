public class Player extends Person{
    private NPC mom;
    private NPC dad;
    private String name;
    private Location home;
    private Item itemInHand;
    private double money;
    private boolean hasCar;
    private boolean hasBike;
    private String school;
    private int dailyHours;
    private int usedHours;
    public Player(String name) {
        this.name = name;
    }
    public Player(String name, NPC mom,NPC dad,Location home,int money, int dailyHours){
        this.name = name;
        this.mom = mom;
        this.dad = dad;
        this.home = home;
        this.money = money;
        this.dailyHours = dailyHours;
    }
}
