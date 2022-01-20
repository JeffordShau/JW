package characters;

public class Slug extends Monster {
  public int _health;
  public int _baseDamage;

  public static String about() {
    return "who is looking at you weird.";
  }

  public Slug() {
    super();
    _health = 5;
    _baseDamage = 3;
  }

  public String getRole () {
    return "v1.monsters.Slug";
  }

}
