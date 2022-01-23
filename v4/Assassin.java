// package characters;

public class Assassin extends Protagonist {

  public Assassin(String newName, int newHealth, int newBaseDamage, int newDefense) {
    super(newName, newHealth, newBaseDamage, newDefense);
  }

  public static String about() {
    return "You are a stealthy rogue who specializes in assassinations. Starting equipment: Bracelet of Stealth.";
  }

  public String getRole() {
    return "Assasin";
  }

}
