package v1.monsters;

import v1.monsters.Monster;

public class Rat extends Monster {
  public int _health;
  public int _baseDamage;

  public static String about() {
    return "who is looking at you a little hungrily.";
  }

  public Rat() {
    super();
    _health = 4;
    _baseDamage = 4;
  }

  public String getRole () {
    return "v1.monsters.Rat";
  }

}
