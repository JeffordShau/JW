// package characters;

public class Slug extends Monster {
  public int _health;
  public int _baseDamage;

  public Slug() {
    super();
    _health = 5;
    _baseDamage = 3;
  }

  public String getRole () {
    return "Slug";
  }

}
