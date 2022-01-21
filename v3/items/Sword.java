package items;

public class Sword extends Item {
    private static final String itemId = "sword";
    private static final String itemName = "Sword";
    private static final String itemDescription = "A sword to help you slay the monsters that be.";

    public Sword () {
        super(itemName, itemId, itemDescription, 100, 10);
    }

    public Sword (String name, String description, int durability, int power) {
        super(name, itemId, description, durability, power);
    }

    public int use () {
        // random attack val from 1-5
        int attack = (int) (Math.random() * 4) + 1;
        durability -= attack;
        return attack * power;
    }
}
