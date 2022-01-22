package characters;
import java.util.ArrayList;
import items.*;

public class Character {
  protected String name;
  protected int health;
  protected int baseDamage;
  protected int damageMult;
  protected int defense;
  protected int gems;
  protected ArrayList<Item> inventory = new ArrayList<Item>();

  public Character() {
    health = 20;
    baseDamage = 1;
    damageMult = 1;
    defense = 0;
    gems = 0;
  }

  public Character(String name) {
    this.name = name;
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

  public void addItem (Item item) {
    inventory.add(item);
  }

  public void removeItem (Item item) {
    inventory.remove(item);
  }

  public void removeItem (int idx) {
    inventory.remove(idx); 
  }

  public ArrayList<Item> getInventory () {
    return inventory;
  }

  public Item getInventoryIdx (int idx) {
    return inventory.get(idx);
  }

  public Item findById (int type) {
    for (Item item : inventory) {
      if (item.getId() == type) {
        return item;
      }
    }
    return null;
  }

  public int inventorySize () {
    return inventory.size();
  }
}
