// package characters;

public class Monster extends Character {

  public Monster() {
    super();
  }

  public Monster(String newName, int newHealth, int newBaseDamage, int newDefense, int newDamageMulti) {
    super(newName, newHealth, newBaseDamage, newDefense, newDamageMulti);
  }

  public String getRole () {
    return "Monster";
  }
}
