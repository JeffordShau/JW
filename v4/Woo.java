/*
JW (Jefford Shau, William Vongphanith)
APCS
FP -- Show Us What You Got
2022-01-21
*/

import characters.*;
import items.*;

import java.io.*;
import java.util.ArrayList;

public class Woo {
  private final static int maxDays = 30; // set the max number of days
  private static int days = 1;
  private int nextitemId = 3;
  // Instance variables
  private Protagonist pat;
  private Monster smaug;

  private InputStreamReader isr;
  private BufferedReader in;

  // total items arraylist
  private ArrayList<Item> items = new ArrayList<>();
  private ArrayList<Item> buyShop = new ArrayList<>();
  private ArrayList<Item> inventory = new ArrayList<>();
  // default constructor
  public Woo() {
    isr = new InputStreamReader( System.in );
    in = new BufferedReader( isr );
    newGame();
  } // end default constructor

  // initiates a new game
  public void newGame() {
    String s;
    String name = "";
    int playerChoice = 0;
    s = "The Hero has arrived!";
    s += "\nChoose your hero: \n";
    s += "\t1: Easy: \n\tRogue: " + Rogue.about() + "\n";
    s += "\t2: Normal: \n\tWarrior: " + Warrior.about() + "\n";
    s += "\t3: Hard: \n\tAssassin: " + Assassin.about() + "\n";
    s += "\t4: Very Hard: \n\tThief: " + Thief.about() + "\n";
    s += "\t5: Impossible: \n\tCursed Hero: " + Cursed_Hero.about() + "\n";
    s += "Selection: ";
    System.out.print( s );
    try {
      playerChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    s = "Chosen one, what is thy great name? ";
    System.out.print( s );
    try {
      name = in.readLine();
    }
    catch ( IOException e ) {
      System.out.println( "Error reading name. " );
    }
    if (playerChoice == 1) {
      pat = new Rogue(name);
      Item bracelet_of_life = new Bracelet("Bracelet of Life", nextitemId, "A legendary healing bracelet", 100, 1);
      items.add(bracelet_of_life);
      nextitemId += 1;
    } else if (playerChoice == 2) {
      pat = new Warrior(name);
      Item bracelet_of_strength = new Bracelet("Bracelet of Strength", nextitemId, "A legendary strenth bracelet", 100, 1);
      items.add(bracelet_of_strength);
      nextitemId += 1;
    } else if (playerChoice == 3) {
      pat = new Assassin(name);
      Item bracelet_of_stealth = new Bracelet("Bracelet of Stealth", nextitemId, "A legendary speed bracelet", 100, 1);
      items.add(bracelet_of_stealth);
      nextitemId += 1;
    } else if (playerChoice == 4) {
      pat = new Thief(name);
      Item bracelet_of_wealth = new Bracelet("Bracelet of Wealth", nextitemId, "A legendary wealth bracelet", 100, 1);
      items.add(bracelet_of_wealth);
      nextitemId += 1;
    } else if (playerChoice == 5) {
      pat = new Cursed_Hero(name);
      Item bracelet_of_poison = new Bracelet("Bracelet of Poison", nextitemId, "An unremovable cursed bracelet", 100, 1);
      items.add(bracelet_of_poison);
      nextitemId += 1;
    } else {
      System.out.println("Hero unidentified. The easiest difficulty has been selected.");
      pat = new Rogue(name);
      Item bracelet_of_life = new Bracelet("Bracelet of Life", nextitemId, "A legendary healing bracelet", 100, 1);
      items.add(bracelet_of_life);
      nextitemId += 1;
    }
  } // end newGame

  // one turn/stage
  public boolean Turn() {
    String s;
    if (pat.isAlive()) {
      if (pat.getRole() == "Rogue") {
        if (pat.getHealth() < 20) {
          System.out.println("Your Bracelet of Life healed you for 1 hp!");
          pat.addHealth(1);
        }
      }
      System.out.println("Day " + days + "\tHealth: " + pat.getHealth() + "\tAttack: " + pat.getAttack() + "\tDefense: " + pat.getDefense() + "\tGems: " + pat.getGems());
      int randGems = 0;
      // randomly assigns the stage depending on day number
      if (days % 7 != 0 && days != maxDays) {
        int stageSel = (int) (Math.random() * 10);
        // receives a few gems, has a choice on next action
        if (stageSel < 2) {
          if (days < 10) {
            randGems = (int) (Math.random() * 10);
          }
          else if (days < 20) {
            randGems = (int) (days / 4) * (int) (Math.random() * 10);
          }
          else if (days < 30) {
            randGems = (int) (days / 4) * (int) (Math.random() * 10);
          }
          System.out.println("You looked around and found " + randGems + " gems!");
          pat.addGems(randGems);
          pat.getGems();
          s = "You do not spot any monsters today. What would you like to do?\n";
          s += "\t1: Search for monsters\n";
          s += "\t2: Use Item\n";
          s += "\t3: Sleep\n";
          int turnChoice = 0;
          System.out.println(s);
          try {
            turnChoice = Integer.parseInt(in.readLine());
          }
          catch ( IOException e ) { }
          if (turnChoice == 1) {
            battleMonster();
          } else if (turnChoice == 2) {
            useItem();
          } else if (turnChoice == 3) {
            System.out.println("You decide to take a nap.");
            return true;
          } else {
            System.out.println("We could not identify your action. You went to sleep for the night.");
            return true;
          }
        }
        // fights a monster
        else if (stageSel < 8) {
          battleMonster();
        }
        // random item drop
        else if (stageSel < 10) {
          int itemIdx = (int) (Math.random() * items.length);
          System.out.println("You found a " + items[itemIdx] + "!");
          if (pat.inventorySize() < 4) {
            // Item foundItem = items[itemIdx];
            // int randDur = ((int) Math.random() * 50) * 2;
            // foundItem.setDurability(randDur);
            // pat.addItem(foundItem);
          }
          else {
            System.out.println("Your inventory is full. You must drop an item to pick one up. What is your choice?");
            int itemToDrop = 1;
            try {
              itemToDrop = Integer.parseInt(in.readLine());
              pat.removeItem(pat.findById(itemToDrop));
              pat.addItem(items[itemIdx]);
            }
            catch (IOException e) {
              System.out.println("You did not enter a valid item name. You were unable to pick up the item.");
            }
          }
        }
      }
      // final boss
      else if (days == maxDays) {
        // fight final boss
      }
      // shop
      else if (days % 7 == 0) {
        System.out.println("As you walk forward, you see a merchant ahead of you.");
        shop();
      }
      return true;
    }
    // player dead
    else {
      return false;
    }
  }

  public String displayBuyItem (int idx) {
    String s = "";
    s = buyShop[idx].getName();
    s += "\tDurability: " + buyShop[idx].getDurability();
    s += "\tPower: " + buyShop[idx].getPower();
    return s;
  }

  public String displayInventoryItem (int idx) {
    Item displayItem = pat.getInventoryIdx(idx);
    String t = "";
    int sellPrice = 0;
    if (days < 10) {
      sellPrice = 20 * displayItem.getDurability();
    }
    else if (days < 20) {
      sellPrice = 40 * displayItem.getDurability();
    }
    else if (days < 30) {
      sellPrice = 60 * displayItem.getDurability();
    }
    t = displayItem.getName();
    t += "\tDurability: " + displayItem.getDurability();
    t += "\tPower: " + displayItem.getPower();
    t += "\tSell Price: " + sellPrice; // adjust this
    return t;
  }

  public Item createItem (String name, int id, String type, String description, int durability, int power) {
    if (type == "shield") {
      Item newitem = new Shield(name, id, description, durability, power);
      return newitem;
    }
    else if (type == "sword") {
      Item newitem = new Sword(name, id, description, durability, power);
      return newitem;
    }
    Item newitem = new Item();
    return newitem;
  }

  public void useItem() {
  }

  public void battleMonster() {
    int i = 1;
    String aboutDescrip = "";

    if (days < 10) {
      int monsterChoice = (int) (Math.random() * 2);
      if (monsterChoice == 0) {
        smaug = new Rat();
        aboutDescrip = Rat.about();
      }
      else if (monsterChoice == 1) {
        smaug = new Blob();
        aboutDescrip = Blob.about();
      }
      else if (monsterChoice == 2) {
        smaug = new Crow();
        aboutDescrip = Crow.about();
      }
    }
    System.out.println( "You are fighting a " + smaug.getRole() + ", " + aboutDescrip);

    // fighting with turns
    while( smaug.isAlive() && pat.isAlive() ) {
      System.out.println( "\nWhat is your choice?" );
      System.out.println( "\t1: Attack.\n\t2: Use item.\n\t3: Flee." );
      try {
        i = Integer.parseInt( in.readLine() );
      }
      catch ( IOException e ) { }
      if ( i == 1 ) {
        int order = (int) (Math.random() * 2);
        if (order == 0) {
          int first, second;
          first = pat.attack( smaug );
          System.out.println( "\n" + pat.getName() + " dealt " + first + " damage.");
          System.out.println("\nMonster health: " + smaug.getHealth());
          second = smaug.attack( pat );
          System.out.println( "\n" + "Monster smacked " + pat.getName() + " for " + second + " points of damage.");
          System.out.println("\nYe health: " + pat.getHealth());
        }
        else if (order == 1) {
          int first, second;
          second = smaug.attack( pat );
          System.out.println( "\n" + "Monster smacked " + pat.getName() + " for " + second + " points of damage.");
          System.out.println("\nYe health: " + pat.getHealth());
          first = pat.attack( smaug );
          System.out.println( "\n" + pat.getName() + " dealt " + first + " points of damage.");
          System.out.println("\nMonster health: " + smaug.getHealth());
        }
      }
      else if ( i == 2 ) {
        // use item
      }
      else if ( i == 3 ) {
        System.out.println("You begin to escape, but the monster slashes at you one time before you escape.");
        int fleeDmg = smaug.attack( pat );
        System.out.println( "\n" + "Monster smacked " + pat.getName() + " for " + fleeDmg + " points of damage.");
        System.out.println("\nYe health: " + pat.getHealth());
        return;
      }
    }
  }

  // shop interface
  public void shop() {
    String s;
    int shopChoice = 0;
    ArrayList<Item> totalItems = new ArrayList<Item>();
    s = "Hero welcome to the shop! Would you ilke to: \n";
    s += "\t1: Buy\n";
    s += "\t2: Sell\n";
    s += "\t3: Exit Shop\n";
    System.out.print(s);
    try {
      shopChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (shopChoice == 1) {
      buyInterface();
    }
    else if (shopChoice == 2) {
      sellInterface();
    }
    else if (shopChoice == 3) {
      return;
    }
  }

  // purchase interface
  public void buyInterface() {
    String s;
    int buyChoice = 0;
    int buyPrice = 0;
    int randBuyItems = (int) (Math.random() * 3);
    if (days < 10) {
      buyPrice = 30;
    }
    else if (days < 20) {
      buyPrice = 60;
    }
    else if (days < 30) {
      buyPrice = 90;
    }
    buyShop.clear();
    for (int i = 0; i < 3; i++) {
      if (randBuyItems == 0) {
        Item buyItem = createItem("Rusty Dagger", nextitemId, "sword", "", 100, 3);
      }
      else if (randBuyItems == 1) {
        Item buyItem = createItem("Wooden Shield", nextitemId, "shield", "", 100, 1);
      }
      buyShop.add(buyItem);
      nextitemId += 1;
    }
    s = "\nAnything that will help you on your journey?";
    s += "\t1: " + displayBuyItem(0) + "\n"; // fill in
    s += "\t2: " + displayBuyItem(1) + "\n"; // fill in
    s += "\t3: " + displayBuyItem(2) + "\n"; // fill in
    s += "\t4: Exit Shop\n";
    s += "\t5: Back\n";
    s += "Selection: ";
    System.out.print( s );
    try {
      buyChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (buyChoice == 1) {
      pat.additem(buyShop[0]);
      pat.removeGems(buyPrice);
    }
    else if (buyChoice == 2) {
      pat.additem(buyShop[1]);
      pat.removeGems(buyPrice);
     }
    else if (buyChoice == 3) {
      pat.additem(buyShop[2]);
      pat.removeGems(buyPrice);
    }
    else if (buyChoice == 4) {
      return;
    }
    else if (buyChoice == 5) {
      shop();
    }
  }

  // selling interface
  public void sellInterface() {
    String s, a, b, c, d, f, g, h = "";
    int sellChoice = 0;
    s = "\nAnything that will help you on your journey?";
    a = "\t1: " + displayInventoryItem(0) + "\n"; // fill in, list item in inventory
    b = "\t2: " + displayInventoryItem(1) + "\n"; // fill in, list item in inventory
    c = "\t3: " + displayInventoryItem(2) + "\n"; // fill in, list item in inventory
    d = "\t4: " + displayInventoryItem(3) + "\n"; // fill in, list item in inventory
    f = "\t5: Exit Shop \n";
    g = "\t6: Back\n";
    h = "Selection: \n";
    System.out.print( s + a + b + c + d + f + g + h);
    try {
      sellChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (sellChoice == 1) {
      pat.removeItem(0);
      int price = Integer.parseInt(a.substring(a.length() - 2, a.length()));
      pat.addGems(price);
    }
    else if (sellChoice == 2) {
      pat.removeItem(1);
      int price = Integer.parseInt(b.substring(b.length() - 2, b.length()));
      pat.addGems(price);
    }
    else if (sellChoice == 3) {
      pat.removeItem(2);
      int price = Integer.parseInt(c.substring(c.length() - 2, c.length()));
      pat.addGems(price);
    }
    else if (sellChoice == 4) {
      pat.removeItem(3);
      int price = Integer.parseInt(d.substring(d.length() - 2, d.length()));
      pat.addGems(price);
    }
    else if (sellChoice == 5) {
      return;
    }
    else if (sellChoice == 6) {
      shop();
    }
  }

  public static void main( String[] args ) {
    Woo game = new Woo();
    while( days <= maxDays ) {
      if ( !game.Turn() ) {
        break;
      }
      days += 1;
    }
    if (days > maxDays) {
      System.out.println("Congrats Hero! Your arduous journey is now over.");
    }
    else {
      System.out.println("You have died.");
    }
  }
}
