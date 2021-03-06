package v1.monsters;

public class Character {
  protected String name;
  protected int health;
  protected int baseDamage;
  protected int damageMult;
  protected int defense;
  protected int gems;
  protected String[] inventory;

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




}
