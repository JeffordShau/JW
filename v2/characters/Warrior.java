package v1.characters;

public class Warrior extends Protagonist {

  public Warrior(String name) {
    super(name);
  }

  public static String about() {
    return "You are a mighty warrior. Starting equipment: Bracelet of Strength.";
  }

  public String getRole() {
    return "v1.v1.characters.Warrior";
  }

}
