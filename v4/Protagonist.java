// package characters;

public class Protagonist extends Character {
  public int gems;

  public Protagonist() {
    super();
    gems = 0;
  }

  public Protagonist(String newName, int newHealth, int newBaseDamage, int newDefense, int newDamageMulti) {
    super(newName, newHealth, newBaseDamage, newDefense,newDamageMulti);
    gems = 0;
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

  public String getName () {
    return name;
  }
}
