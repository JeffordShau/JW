package characters;

public class Protagonist extends Character {

  public Protagonist() {
    super();
  }

  public Protagonist(String name) {
    super(name);
  }

  public int getGems() {
    return gems;
  }

  public void addGems(int randGems) {
    gems += randGems;
  }

  public void getHeal() {
    health += 5;
  }

  public String getRole () {
    return "v1.v1.characters.Protagonist";
  }

  public String getName () {
    return name;
  }

  public boolean isAlive() {
    return health > 0;
  }
}
