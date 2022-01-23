// package characters;
// import java.util.ArrayList;
// import items.*;

public class Character {
  protected String name;
  protected int health;
  protected int baseDamage;
  protected int defense;

  public Character() {
    health = 4;
    baseDamage = 3;
    defense = 0;
  }

  public Character(String newName, int newHealth, int newBaseDamage, int newDefense) {
    name = newName;
    health = newHealth;
    baseDamage =  newBaseDamage;
    defense = newDefense;
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

  public boolean isAlive() {
    return health > 0;
  }

  public void addHealth(int value) {
    health += value;
  }

  public void subtractHeatlh(int value) {
    health -= value;
  }

  public int attack (Character attacked) {
    int totalDamage = baseDamage - attacked.getDefense();
    return attacked.reduceHealth(totalDamage);
  }

  public int reduceHealth(int damage) {
    health -= damage;
    return damage;
  }
}
