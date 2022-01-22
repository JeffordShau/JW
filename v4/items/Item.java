package items;

public class Item {
  protected String name;
  protected String id;
  protected String description;
  protected int durability;
  protected int power;

  public Item() {
    this.name = "Item";
    this.description = "A default item.";
  }

  public Item(String name, String id, String description, int durability, int power) {
    this.name = name;
    this.description = description;
    this.id = id;
    this.durability = durability;
    this.power = power;
  }

  public String getName () {
    return name;
  }

  public String getId () {
    return id;
  }

  public String getDescription () {
    return description;
  }

  public int getDurability () {
    return durability;
  }

  public void setDurability (int num) {
    durability = num; 
  }

  public int getPower () {
    return power;
  }

  public String toString () {
    return name + " (id: " + id + ")";
  }
}
