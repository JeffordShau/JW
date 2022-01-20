package items;

public class shield extends shopItem {
    private static final String itemName = "shield";
    private static final String itemDescription = "A durable shield that protects you in battle.";

    public shield () {
        super(itemName, 100, itemDescription, 100, 25);
    }

    public shield (int cost, int durability, int power) {
        super(itemName, cost, itemDescription, durability, power);
    }

    public int use () {
        // random protection val from 1-10
        int protection = (int) (Math.random() * 9) + 1;
        durability -= protection;
        return protection * power;
    }
}
