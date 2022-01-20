package v1.items;

public class shopItem {
    protected String name;
    protected int price;
    protected String description;
    protected int durability;
    protected int power;

    public shopItem() {
        this.name = "Item";
        this.price = 0;
        this.description = "A default item.";
    }

    public shopItem(String name, int price, String description, int durability, int power) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName () {
        return name;
    }

    public int getPrice () {
        return price;
    }

    public String getDescription () {
        return description;
    }

    public int getDurability () {
        return durability;
    }

    public int getPower () {
        return power;
    }
}
