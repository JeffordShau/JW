// package characters;

public class Demon extends Monster {

  public Demon(String newName, int newHealth, int newBaseDamage, int newDefense) {
    super(newName, newHealth, newBaseDamage, newDefense);
  }

  public String getRole () {
    return "Demon";
  }

}
