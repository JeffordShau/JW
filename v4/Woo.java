/*
JW (Jefford Shau, William Vongphanith)
APCS
FP -- Show Us What You Got
2022-01-21
*/

// import characters.*;
// import items.*;

import java.io.*;
import java.util.ArrayList;

public class Woo {
  private final static int maxDays = 30; // set the max number of days
  private static int days = 1;
  private int nextitemId = 0;
  // Instance variables
  private Protagonist pat;
  private Monster smaug;

  private InputStreamReader isr;
  private BufferedReader in;

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
      inventory.add(bracelet_of_life);
      nextitemId += 1;
    } else if (playerChoice == 2) {
      pat = new Warrior(name);
      Item bracelet_of_strength = new Bracelet("Bracelet of Strength", nextitemId, "A legendary strenth bracelet", 10, 1);
      items.add(bracelet_of_strength);
      inventory.add(bracelet_of_strength);
      nextitemId += 1;
    } else if (playerChoice == 3) {
      pat = new Assassin(name);
      Item bracelet_of_stealth = new Bracelet("Bracelet of Stealth", nextitemId, "A legendary speed bracelet", 10, 1);
      items.add(bracelet_of_stealth);
      inventory.add(bracelet_of_stealth);
      nextitemId += 1;
    } else if (playerChoice == 4) {
      pat = new Thief(name);
      Item bracelet_of_wealth = new Bracelet("Bracelet of Wealth", nextitemId, "A legendary wealth bracelet", 10, 1);
      items.add(bracelet_of_wealth);
      inventory.add(bracelet_of_wealth);
      nextitemId += 1;
    } else if (playerChoice == 5) {
      pat = new Cursed_Hero(name);
      Item bracelet_of_poison = new Bracelet("Bracelet of Poison", nextitemId, "An unremovable cursed bracelet", 10, 1);
      items.add(bracelet_of_poison);
      inventory.add(bracelet_of_poison);
      nextitemId += 1;
    } else {
      System.out.println("Hero unidentified. The easiest difficulty has been selected.");
      pat = new Rogue(name);
      Item bracelet_of_life = new Bracelet("Bracelet of Life", nextitemId, "A legendary healing bracelet", 10, 1);
      items.add(bracelet_of_life);
      inventory.add(bracelet_of_life);
      nextitemId += 1;
    }
  } // end newGame

  // one turn/stage
  public boolean Turn() {
    String s;
    if (pat.isAlive()) {
      if (inventory.get(0).getName() == "Bracelet of Life") {
        if (pat.getHealth() < 20) {
          System.out.println("Your Bracelet of Life healed you for 1 hp!");
          pat.addHealth(1);
        }
      }
      System.out.println("Day " + days + "\tHealth: " + pat.getHealth() + "\tAttack: " + pat.getAttack() + "\tDefense: " + pat.getDefense() + "\tGems: " + pat.getGems());
      // randomly assigns the stage depending on day number
      if (days % 7 != 0 && days != maxDays) {
        int stageSel = (int) (Math.random() * 10);
        // receives a random amount of gems, has a choice on next action
        if (stageSel < 2) {
          int randGems = 0;
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
        // receives a random item
        else if (stageSel < 10) {
          int randDur = 2 * ((int) (Math.random() * 51));
          int randItem = (int) (Math.random() * 3);
          Item luckyItem = createItem("", 0, "", "", 0, 0);
          if (randItem == 0) {
            luckyItem = createItem("Rusty Dagger", nextitemId, "sword", "", 100, 3);
          }
          else if (randItem == 1) {
            luckyItem = createItem("Wooden Shield", nextitemId, "shield", "", 100, 1);
          }
          else if (randItem == 2) {
            luckyItem = createItem("Potion of Health", nextitemId, "potion", "", 100, 1);
          }
          else if (randItem == 3) {
            luckyItem = createItem("Potion of Strength", nextitemId, "potion", "", 100, 1);
          }
          System.out.println("You found a " + luckyItem.getName() + "!");
          // if inventory full
          if (inventory.size() > 5) {
            if (dropInventoryItem()) {
              inventory.add(luckyItem);
              nextitemId += 1;
            }
          }
          else {
            inventory.add(luckyItem);
            nextitemId += 1;
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

  public boolean dropInventoryItem () {
    String s;
    int itemToDrop = 6;
    s = "Your inventory is full. You must drop an item to pick one up. What is your choice?\n";
    s += "\t1: " + displayInventoryItem(1) + "\n";
    s += "\t2: " + displayInventoryItem(2) + "\n";
    s += "\t3: " + displayInventoryItem(3) + "\n";
    s += "\t4: " + displayInventoryItem(4) + "\n";
    s += "\t5: Do not pick up \n";
    s += "Selection: \n";
    System.out.print( s );
    try {
      itemToDrop = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (itemToDrop < 5) {
      inventory.remove(itemToDrop);
      return true;
    }
    else if (itemToDrop == 5) {
      return false;
    }
    else {
      System.out.println("You choose not to pick up the item.");
      return false;
    }
  }

  // display shop item
  public String displayBuyItem (int idx) {
    String s = "";
    s = buyShop.get(idx).getName();
    s += "\tDurability: " + buyShop.get(idx).getDurability();
    s += "\tPower: " + buyShop.get(idx).getPower();
    return s;
  }

  //display inventory item
  public String displayInventoryItem (int idx) {
    String s = "";
    s = inventory.get(idx).getName();
    s += "\tDurability: " + inventory.get(idx).getDurability();
    s += "\tPower: " + inventory.get(idx).getPower();
    return s;
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
    else if (type == "potion") {
      Item newitem = new Potion(name, id, description, durability, power);
      return newitem;
    }
    Item newitem = new Item();
    return newitem;
  }

  public void useItem() {
  }

  public void battleMonster() {
    int i = 1;

    if (days < 10) {
      int monsterChoice = (int) (Math.random() * 2);
      if (monsterChoice == 0) {
        smaug = new Rat();
      }
      else if (monsterChoice == 1) {
        smaug = new Blob();
      }
      else if (monsterChoice == 2) {
        smaug = new Crow();
      }
    }
    System.out.println( "You are fighting the " + smaug.getRole() + "" + smaug.getName());

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
    s = "Hero welcome to the shop! Would you ilke to:\n";
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

  // buying interface
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
      Item shopItem = createItem("", 0, "", "", 0, 0);
      if (randBuyItems == 0) {
        shopItem = createItem("Rusty Dagger", nextitemId, "sword", "", 100, 3);
      }
      else if (randBuyItems == 1) {
        shopItem = createItem("Wooden Shield", nextitemId, "shield", "", 100, 1);
      }
      else if (randBuyItems == 2) {
        shopItem = createItem("Potion of Health", nextitemId, "potion", "", 100, 1);
      }
      buyShop.add(shopItem);
    }
    s = "\nAnything that will help you on your journey?";
    s += "\t1: " + displayBuyItem(0) + "\tBuy Price: "+ buyPrice + "\n";
    s += "\t2: " + displayBuyItem(1) + "\tBuy Price: "+ buyPrice + "\n";
    s += "\t3: " + displayBuyItem(2) + "\tBuy Price: "+ buyPrice + "\n";
    s += "\t4: Exit Shop\n";
    s += "\t5: Back\n";
    s += "Selection: ";
    System.out.print( s );
    try {
      buyChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { System.out.println("Sorry, we could not get your selection."); }
    // if inventory full
    if (inventory.size() > 5) {
      if (dropInventoryItem()) {
        if (buyChoice < 4) {
          inventory.add(buyShop.get(buyChoice));
          pat.removeGems(buyPrice);
          nextitemId += 1;
        }
        else if (buyChoice == 4) {
          return;
        }
        else if (buyChoice == 5) {
          shop();
        }
        nextitemId += 1;
      }
    }
    else {
      if (buyChoice < 4) {
        inventory.add(buyShop.get(buyChoice));
        pat.removeGems(buyPrice);
        nextitemId += 1;
      }
      else if (buyChoice == 4) {
        return;
      }
      else if (buyChoice == 5) {
        shop();
      }
      nextitemId += 1;
    }
  }

  // selling interface
  public void sellInterface() {
    String s = "";
    int sellChoice = 0;
    int sellPrice = 0;
    if (days < 10) {
      sellPrice = 20;
    }
    else if (days < 20) {
      sellPrice = 40;
    }
    else if (days < 30) {
      sellPrice = 60;
    }
    s = "\nAnything that will help you on your journey?";
    s += "\t1: " + displayInventoryItem(1) + "\tSell Price: " + (sellPrice * inventory.get(1).getDurability()) + "\n";
    s += "\t2: " + displayInventoryItem(2) + "\tSell Price: " + (sellPrice * inventory.get(2).getDurability()) + "\n";
    s += "\t3: " + displayInventoryItem(3) + "\tSell Price: " + (sellPrice * inventory.get(2).getDurability()) + "\n";
    s += "\t4: " + displayInventoryItem(4) + "\tSell Price: " + (sellPrice * inventory.get(2).getDurability()) + "\n";
    s += "\t5: Exit Shop \n";
    s += "\t6: Back\n";
    s += "Selection: \n";
    System.out.print( s );
    try {
      sellChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (sellChoice < 5) {
      inventory.remove(sellChoice);
      int price = sellPrice * inventory.get(sellChoice).getDurability();
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
