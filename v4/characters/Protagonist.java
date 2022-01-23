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

  public void addGems(int addGems) {
    gems += addGems;
  }

  public void removeGems(int removeGems) {
    gems += removeGems;
  }

  public void getHeal() {
    health += 5;
  }

  public String getRole () {
    return "Protagonist";
  }

  public String getName () {
    return name;
  }

  public boolean isAlive() {
    return health > 0;
  }
}
