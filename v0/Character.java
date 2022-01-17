public class Character {
  protected String name;
  protected int health;
  protected int baseDamage;
  protected int damageMult;
  protected int defense;
  protected int gems;
  protected String[] inventory; 

  public Character() {
    health = 100;
    baseDamage = 20;
    damageMult = 1;
    defense = 10;
    gems = 0;
  }

  public Character(String name) {
    name = name;
    health = 100;
    baseDamage = 20;
    damageMult = 1;
    defense = 10;
    gems = 0;
  }
