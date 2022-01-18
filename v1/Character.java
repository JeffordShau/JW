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
