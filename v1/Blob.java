public class Blob extends Monster {
  public int _health;
  public int _baseDamage;

  public static String about() {
    return "This blob is looking for some food.";
  }

  public Blob() {
    super();
    _health = 6;
    _baseDamage = 4;
  }

  public String getRole () {
    return "Blob";
  }

}
