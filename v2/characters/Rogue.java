package characters;

public class Rogue extends Protagonist {

  public Rogue(String name) {
    super(name);
  }

  public static String about() {
    return "You are a jack-of-all traits hero. Starting equipment: Bracelet of Life.";
  }

  public String getRole() {
    return "v1.v1.characters.Rogue";
  }

}
