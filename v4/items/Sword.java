package items;

public class Sword extends Item {
    private static final String itemId = "sword";
    private static final String itemName = "Sword";
    private static final String itemDescription = "A sword to help you slay the monsters that be.";

    public Sword () {
        super(itemName, itemId, itemDescription, 100, 10);
    }

    public Sword (String name, int durability, int power) {
        super(name, itemId, itemDescription, durability, power);
    }

    public int use () {
        // random attack val from 1-5
        int reduction = (int) (Math.random() * 5) * 2;
        durability -= reduction;
        return power;
    }
}
