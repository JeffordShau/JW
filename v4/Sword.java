// package items;

public class Sword extends Item {
    private static String itemName = "Sword";
    private static String itemDescription = "A sword to help you slay the monsters that be.";
    private static int durability = 0;
    private static int power = 0;

    public Sword () {
        super();
    }

    public Sword (String name, int itemId, String itemDescription, int durability, int power) {
        super(name, id, description, durability, power);
    }

    public int use () {
        // random attack val from 1-5
        int reduction = (int) (Math.random() * 5) * 2;
        durability -= reduction;
        return power;
    }
}
