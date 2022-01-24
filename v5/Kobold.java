// package characters;

public class Kobold extends Monster {

  public Kobold(String newName, int newHealth, int newBaseDamage, int newDefense) {
    super(newName, newHealth, newBaseDamage, newDefense);
  }

  public String getRole () {
    return "Kobold";
  }

}
