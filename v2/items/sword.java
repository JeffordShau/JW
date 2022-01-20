package v0.items;

public class sword extends shopItem {
    private static final String itemName = "sword";
    private static final String itemDescription = "A sword to help you slay the monsters that be.";

    public sword () {
        super(itemName, 100, itemDescription, 100, 10);
    }

    public sword (int cost, int durability, int power) {
        super(itemName, cost, itemDescription, durability, power);
    }

    public int use () {
        // random attack val from 1-5
        int attack = (int) (Math.random() * 4) + 1;
        durability -= attack;
        return attack * power;
    }
}
