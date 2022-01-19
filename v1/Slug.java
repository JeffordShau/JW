public class Slug extends Monster {
  public int _health;
  public int _baseDamage;

  public static String about() {
    return "This rat is looking at you a little hungrily.";
  }

  public Slug() {
    super();
    _health = 5;
    _baseDamage = 3;
  }

  public String getRole () {
    return "Slug";
  }

}
