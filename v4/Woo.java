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
  private Item[] items = {new Sword("Wooden Sword", 1, "a basic sword", 30, 100), new Shield("Wooden Shield", 2, "a basic shield", 20, 100)};

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
    } else if (playerChoice == 2) {
      pat = new Warrior(name);
    } else if (playerChoice == 3) {
      pat = new Assassin(name);
    } else if (playerChoice == 4) {
      pat = new Thief(name);
    } else if (playerChoice == 5) {
      pat = new Cursed_Hero(name);
    } else {
      System.out.println("Hero unidentified. A default hero was provided.");
      pat = new Protagonist( name );
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

  public String displayInventoryItem (int idx) {
    Item displayItem = pat.getInventoryIdx(idx);
    String t = "";
    t = displayItem.getName();
    t += "\tDurability: " + displayItem.getDurability();
    t += "\tPower: " + displayItem.getPower();
    t += "\tSell Price: " + displayItem.getDurability(); // adjust this
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
    s = "\nAnything that will help you on your journey?";
    s += "\t1:  + \n"; // fill in
    s += "\t2: + \n"; // fill in
    s += "\t3: + \n"; // fill in
    s += "\t4: Exit Shop. ";
    s += "\t5: Back";
    s += "Selection: ";
    System.out.print( s );
    try {
      buyChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (buyChoice == 1) {
      // add item to inventory and subtract gems
    }
    else if (buyChoice == 2) {
      // add item to inventory and subtract gems
    }
    else if (buyChoice == 3) {
      // add item to inventory and subtract gems
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
    String s;
    int sellChoice = 0;
    int sellPrice = 0;
    if (days < 10) {
      sellPrice = 20 * 
    }
    s = "\nAnything that will help you on your journey?";
    s += "\t1: " + displayInventoryItem(0) + "\tSell Price: "; // fill in, list item in inventory
    s += "\t2: " + displayInventoryItem(1) + "\tSell Price: "// fill in, list item in inventory
    s += "\t3: " + displayInventoryItem(2) + "\tSell Price: "// fill in, list item in inventory
    s += "\t4: " + displayInventoryItem(3) +"\tSell Price: "// fill in, list item in inventory
    s += "\t5: Exit Shop \n";
    s += "\t6: Back\n";
    s += "Selection: \n";
    System.out.print( s );
    try {
      sellChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (sellChoice == 1) {
      pat.removeItem(0);
    }
    else if (sellChoice == 2) {
      pat.removeItem(1);
    }
    else if (sellChoice == 3) {
      pat.removeItem(2);
    }
    else if (sellChoice == 4) {
      pat.removeItem(3);
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
