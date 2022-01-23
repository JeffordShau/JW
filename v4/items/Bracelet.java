package items;

public class Bracelet extends Item {
    private static String itemName = "Shield";
    private static String itemDescription = "A durable shield that protects you in battle.";

    public Bracelet () {
        super(itemName, itemDescription, 100, 10);
    }

    public Bracelet (String name, int id, String description, int durability, int power) {
        super(name, id, description, durability, power);
    }

}
