// package items;

public class Item {
  protected String name;
  protected int itemId;
  protected String description;
  protected int durability;
  protected int power;

  public Item() {
    this.name = "Item";
    this.description = "A default item.";
    this.itemId = 100;
    this.durability = 100;
    this.power = 1;
  }

  public Item(String name, int id, String description, int durability, int power) {
    this.name = name;
    this.description = description;
    this.itemId = id;
    this.durability = durability;
    this.power = power;
  }

  public String getName () {
    return name;
  }

  public int getId () {
    return itemId;
  }

  public String getDescription () {
    return description;
  }

  public int getDurability () {
    return durability;
  }

  public void reduceDurability (int num) {
    durability -= num;
  }

  public int getPower () {
    return power;
  }
}
