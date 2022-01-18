public class Assassin extends Protagonist {

  public Asssasin(String name) {
    super(name);
  }

  public static String about() {
    return "You are a stealthy rogue who specializes in assassinations. Starting equipment: Bracelet of Stealth.";
  }

  public String getRole() {
    return "Assasin";
  }

}
