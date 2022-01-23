// package characters;

public class Monster extends Character {

  public Monster() {
    super();
  }

  public Monster(String newName, int newHealth, int newBaseDamage, int newDefense) {
    super(newName, newHealth, newBaseDamage, newDefense);
  }

  public String getRole () {
    return "Monster";
  }
}
