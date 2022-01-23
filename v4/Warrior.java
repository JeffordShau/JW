// package characters;

public class Warrior extends Protagonist {

  public Warrior(String newName, int newHealth, int newBaseDamage, int newDefense) {
    super(newName, newHealth, newBaseDamage, newDefense);
  }

  public static String about() {
    return "You are a mighty warrior. Starting equipment: Bracelet of Strength.";
  }

  public String getRole() {
    return "Warrior";
  }

}
