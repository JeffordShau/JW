package v1.monsters;

public class Crow extends Monster {
  public int _health;
  public int _baseDamage;

  public static String about() {
    return "Caw, caw.";
  }

  public Crow() {
    super();
    _health = 4;
    _baseDamage = 4;
  }

  public String getRole () {
    return "v1.monsters.Crow";
  }

}
