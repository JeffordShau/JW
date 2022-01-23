// package characters;

public class Rat extends Monster {
  public int _health;
  public int _baseDamage;

  public Rat() {
    super();
    _health = 4;
    _baseDamage = 4;
  }

  public String getRole () {
    return "Rat";
  }

}
