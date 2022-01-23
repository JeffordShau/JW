// package characters;

public class Monster extends Character {
  public int baseDamage;
  public int damageMult;
  public int defense;

  public Monster() {
    super();
    health = 4;
    baseDamage = 3;
    damageMult = 1;
    defense = 0;
  }

  public String getRole () {
    return "Monster";
  }

  public boolean isAlive() {
    return health > 0;
  }

  public int getDefense() {
    return defense;
  }
}
