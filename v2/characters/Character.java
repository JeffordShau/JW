package v1.characters;
import java.util.ArrayList;

public class Character {
  protected String name;
  protected int health;
  protected int baseDamage;
  protected int damageMult;
  protected int defense;
  protected int gems;
  protected ArrayList<Item> inventory;

  public Character() {
    health = 20;
    baseDamage = 1;
    damageMult = 1;
    defense = 0;
    gems = 0;
  }

  public Character(String name) {
    name = name;
    health = 20;
    baseDamage = 1;
    damageMult = 1;
    defense = 0;
    gems = 0;
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public int getDefense() {
    return defense;
  }

  public int getAttack() {
    return baseDamage;
  }

  public void addHealth(int value) {
    health += value;
  }

  public boolean isAlive() {
    return health > 0;
  }

  public int attack (Character attacked) {
    int totalDamage = 0;
    if (damageMult != 1) {
      totalDamage = baseDamage * damageMult - attacked.getDefense();
      return attacked.reduceHealth(totalDamage);
    }
    totalDamage = baseDamage - attacked.getDefense();
    return attacked.reduceHealth(totalDamage);
  }

  public int reduceHealth(int damage) {
    health -= damage;
    return damage;
  }

  public void addItemToInventory (Item item) {
    inventory.add(item);
  }

  public void removeItemFromInventory (Item item) {
    inventory.remove(item);
  }

  public ArrayList<Item> getInventory () {
    return inventory;
  }

  public Item findByType (String type) {
    for (Item item : inventory) {
      if (item.getType().equals(type)) {
        return item;
      }
    }
    return null;
  }

  public int inventorySize () {
    return inventory.size();
  }
}
