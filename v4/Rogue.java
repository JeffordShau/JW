// package characters;

public class Rogue extends Protagonist {

  public Rogue(String newName, int newHealth, int newBaseDamage, int newDefense) {
    super(newName, newHealth, newBaseDamage, newDefense);
  }

  public static String about() {
    return "You are a jack-of-all traits hero. Starting equipment: Bracelet of Life.";
  }

  public String getRole() {
    return "Rogue";
  }

}
