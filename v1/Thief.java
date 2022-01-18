public class Thief extends Protagonist {

  public Thief(String name) {
    super(name);
  }

  public static String about() {
    return "You are a sneaky thief. Starting equipment: Bracelet of Wealth.";
  }

  public String getRole() {
    return "Thief";
  }

}
