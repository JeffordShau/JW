import v1.characters.Character;

public class Monster extends Character {
  public int baseDamage;
  public int damageMult;
  public int defense;

  public Monster() {
    super();
    health = 10;
    baseDamage = 3;
    damageMult = 1;
    defense = 0;
  }

  public String getRole () {
    return "v1.monsters.Monster";
  }
}
