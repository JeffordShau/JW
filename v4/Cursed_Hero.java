// package characters;

public class Cursed_Hero extends Protagonist {

  public Cursed_Hero(String name) {
    super(name);
  }

  public static String about() {
    return "You are a hero that has been poisoned. You will lose 1 health every stage. Starting equipment: Bracelet of Death.";
  }

  public String getRole() {
    return "Cursed Hero";
  }

}
