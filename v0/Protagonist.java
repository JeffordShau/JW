public class Protagonist extends Character {

  public Protagonist() {
    super();
  }

  public Protagonist(String name) {
    super(name);
  }

  public getGems(int randGems) {
    gems += randGems;
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
}
