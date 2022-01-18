/*
JW (Jefford Shau, William Vongphanith)
APCS
FP -- Show Us What You Got
2022-01-13
*/

import java.io;
import java.utils;

public class Woo {

// Instance variables
  private Protagonist pat;
  private Monster smaug;

  private boolean shop;
  private int stages;
  private int maxStages;

  private InputStreamReader isr;
  private BufferedReader in;

  // default constructor
  public Woo() {
    stages = 1;
    maxStages = 30; // set the max number of stages
    isr = new InputStreamReader( System.in );
    in = new BufferedReader( isr );
    newGame();
  } // end default constructor

  // initiates a new game
  public void newGame() {
    String s;
    String name = "";
    int playerChoice = 0;

    // implement hero classes
    s = "The Hero has arrived!";
    s += "\nChoose your hero: \n";
    s += "\t1: Warrior: " + Warrior.about() + "\n";
    s += "\t2: Priest: " + Priest.about() + "\n";
    s += "\t3: Assassin: " + Assassin.about() + "\n";
    s += "\t4: Mage: " + Mage.about() + "\n";
    s += "Selection: ";
    System.out.print( s );
    try {
      playerChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    s = "Chosen one, what is your great name? ";
    System.out.print( s );
    try {
	    name = in.readLine();
    }
    catch ( IOException e ) { }
    // instantiate the player's character
    if (playerChoice == 1) {
      pat = new Warrior(name);
    } else if (playerChoice == 2) {
      pat = new Priest(name);
    } else if (playerChoice == 3) {
      pat = new Assassin(name);
    } else if (playerChoice == 4) {
      pat = new Mage(name);
    } else {
      System.out.println("We could not identify a hero to match your description. A default hero was provided.");
      pat = new Protagonist( name );
    }
  } // end newGame

  // one turn/stage
  public boolean Turn() {
    if (pat.isAlive()) {
      System.out.println("Stage: " + stages);
      // randomly assigns the stage depending on stage number
      if (stages % 7 != 0 || stages != maxStages) {
        int stageSel = (int) (Math.random() * 10);
        // receives a few gems, nothing happens
        if (stageSel < 2) {
          int randGems = (int) (Math.random() * 3); //adjust number of gems received
          System.out.println("You looked around and found " + randGems + "gems! You do not spot any monsters and continue on your journey. ");
          pat.getGems(randGems);
        }
        // fights a monster
        else if (stageSel < 8) {
          battleMonster();
        }
        // random item drop
        else if (stageSel < 10) {
          // if inventory full can choose to drop an item to pick up the new one
        }
      }
      // shop
      else if (stages % 7 == 0) {
        System.out.println("As you walk forward, you see a merchant ahead of you.");
        shop();
      }
      // final boss
      else if (stages == maxStages) {
        // fight final boss
      }
      // adds 1 to stage
      if (pat.isAlive()) {
        stages += 1;
        return true;
      }
      // player dead
      else {
        return false;
      }
    }
    // player dead
    else {
      return false;
    }
  }

  public void battleMonster() {
    int i = 1;

    //initiates the monster
    int monsterChoice = (int) (Math.random() * 2);
    String aboutDescrip = "";
    if (monsterChoice == 0) {
      smaug = new GoblinKing();
      aboutDescrip = GoblinKing.about();
    }
    else if (monsterChoice == 1) {
      smaug = new Dragon();
      aboutDescrip = Dragon.about();
    }
    else if (monsterChoice == 2) {
      smaug = new Golem();
      aboutDescrip = Golem.about();
    }
    System.out.println( "\nYou are " + pat.getName() + ", the " + pat.getRole() + ". " + "You are fighting a " + smaug.getRole() + ", " + aboutDescrip );

    // fighting with turns
    while( smaug.isAlive() && pat.isAlive() ) {
      try {
      System.out.println( "\nWhat is your choice?" );
      System.out.println( "\t1: Attack.\n\t2: Use item.\n\t3:Flee." );
      i = Integer.parseInt( in.readLine() );
      }
      catch ( IOException e ) { }
      if ( i == 1 )
        attack();
        else if ( i == 2 ) {
          // use item
        }
        else if ( i == 3 ) {
          System.out.println("You begin to escape. The monster slashes at you before you escape.");
          d2 = smaug.attack( pat );
          System.out.println( "\n" + "Monster smacked " + pat.getName() + " for " + d2 + " points of damage.");
          System.out.println("\nYe health: " + pat.getHealth());
        }
      }
    }

  public void attack() {
      int d1, d2;
      d1 = pat.attack( smaug );
      System.out.println( "\n" + pat.getName() + " dealt " + d1 + " points of damage.");
      System.out.println("\nMonster health: " + smaug.getHealth());
      // healing Role if implemented
      // if (pat.getRole() == "Priest") {
      //   pat.getHeal();
      //   System.out.println("\nYe health for 5!");
      //   System.out.println("\nYe health: " + pat.getHealth());
      // }
      d2 = smaug.attack( pat );
      System.out.println( "\n" + "Monster smacked " + pat.getName() + " for " + d2 + " points of damage.");
      System.out.println("\nYe health: " + pat.getHealth());
    }

  // shop interface
  public void shop() {
    String s;
    String[] totalItems = {"Rusty Dagger", "Straw Shield", "Stone Shield"};
    int in = totalItems.length();
    int shopChoice = 0;
    s = "Hero welcome to the shop! Would you ilke to: ";
    s += "\t1: Buy\n";
    s += "\t2: Sell\n";
    s += "\t3: Exit Shop";
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
      break;
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
      break;
    }
    else if (buyChoice == 5) {
      shop();
    }
  }

  // selling interface
  public void sellInterface() {
    String s;
    int sellChoice = 0;
    s = "\nAnything that will help you on your journey?";
    s += "\t1:  + \n"; // fill in, list item in inventory
    s += "\t2: + \n"; // fill in, list item in inventory
    s += "\t3: + \n"; // fill in, list item in inventory
    s += "\t4: Exit Shop. ";
    s += "\t5: Back";
    s += "Selection: ";
    System.out.print( s );
    try {
      sellChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (sellChoice == 1) {
      // add item to inventory and subtract gems
    }
    else if (sellChoice == 2) {
      // add item to inventory and subtract gems
    }
    else if (sellChoice == 3) {
      // add item to inventory and subtract gems
    }
    else if (sellChoice == 4) {
      break;
    }
    else if (sellChoice == 5) {
      shop();
    }
  }

  public static void main( String[] args ) {
    Woo game = new Woo();
    while( pat.isAlive() ) {
      game.Turn();
      stages += 1;
    }
      System.out.println( "Thy game doth be over." );
  }
}
