// package characters;

public class Thief extends Protagonist {

  public Thief(String newName, int newHealth, int newBaseDamage, int newDefense) {
    super(newName, newHealth, newBaseDamage, newDefense);
  }

  public static String about() {
    return "You are a sneaky thief. Starting equipment: Bracelet of Wealth.";
  }

  public String getRole() {
    return "Thief";
  }

}
