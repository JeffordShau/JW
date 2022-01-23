// package items;

public class Shield extends Item {
    private static String itemName = "Shield";
    private static String itemDescription = "A durable shield that protects you in battle.";

    public Shield () {
        super();
    }

    public Shield (String name, int id, String description, int durability, int power) {
        super();
    }

    public int use () {
        // random protection val from 1-10
        int reduction = (int) (Math.random() * 9) + 1;
        durability -= reduction;
        return power;
    }
}
