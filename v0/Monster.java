public class Monster extends Character {
  public int baseDamage;
  public int damageMult;
  public int defense;

  public Monster() {
    super();
    health = 300;
    baseDamage = 30;
    damageMult = 1;
    defense = 10;
  }

  public String getRole () {
    return "Monster";
  }
}
