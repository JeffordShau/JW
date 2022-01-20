package items;

import java.util.ArrayList;
import characters.*;

public class Item {
  protected String name;
  protected int price;
  protected int durability;

  public Item() {
    name = "";
    price = 0;
    durability = 100;
  }

  public Item(String name, int cost, int durable) {
    name = name;
    price = cost;
    durability = durable;
  }

  public String getName() {
    return name;
  }

  public int getCost() {
    return price;
  }

  public int getDurability() {
    return durability;
  }

  public int reduceDurability() {
    int damage = (int) (Math.random() * 20);
    durability -= damage;
    return damage;
  }

  public String toString() {
    return name + " (" + durability + " durability)";
  }
}
