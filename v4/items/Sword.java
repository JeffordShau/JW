package items;

public class Sword extends Item {
    private static String itemName = "Sword";
    private static String itemDescription = "A sword to help you slay the monsters that be.";

    public Sword () {
        super(itemName, itemDescription, 100, 10);
    }

    public Sword (String name, int itemId, String itemDescription, int durability, int power) {
        super(name, itemId, itemDescription, durability, power);
    }

    public int use () {
        // random attack val from 1-5
        int reduction = (int) (Math.random() * 5) * 2;
        durability -= reduction;
        return power;
    }
}
