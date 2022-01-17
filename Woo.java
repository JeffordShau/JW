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

  public Woo() {
    stages = 1;
    maxStages = 30; // set the max number of stages
    isr = new InputStreamReader( System.in );
    in = new BufferedReader( isr );
    newGame();
  }

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
  }

  public boolean Turn() {
    if (pat.isAlive()) {
      System.out.println("Stage: " + stages);
      if (stages % 7 != 0 || stages != maxStages) {
        int stageSel = (int) (Math.random() * 10);
        if (stageSel < 2) {
          int randGems = (int) (Math.random() * 3); //adjust number of gems received
          System.out.println("You looked around and found " + randGems + "gems!You do not spot any monsters and continue on your journey. ");
          pat.getGems(randGems);
        }
        else if (stageSel < 8) {
          battleMonster();
        }
        else if (stageSel < 10) {
          // random item drop
        }
      }
      else if (stages & 7 = 0) {
        // item shop
      }
      else if (stages == 0) {
        // fight final boss
      }
      if (pat.isAlive()) {
        stages += 1;
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

    public void battleMonster() {
      int i = 1;

      //instantiate the monster
      int monsterChoice = (int) (Math.random() * 2);
      String aboutDescrip = "";
      if (monsterChoice == 0) {
        smaug = new GoblinKing();
        aboutDescrip = GoblinKing.about();
      } else if (monsterChoice == 1) {
        smaug = new Dragon();
        aboutDescrip = Dragon.about();
      } else if (monsterChoice == 2) {
        smaug = new Golem();
        aboutDescrip = Golem.about();
      }

      System.out.println( "\nYou are " + pat.getName() + ", the " + pat.getRole() + ". " + "You are fighting a " + smaug.getRole() + ", " + aboutDescrip );

      while( smaug.isAlive() && pat.isAlive() ) {

      try {
        System.out.println( "\nWhat is your choice?" );
        System.out.println( "\t1: Attack.\n\t2: Use item.\n\t3:Flee." );
        i = Integer.parseInt( in.readLine() );
      }
          catch ( IOException e ) { }

          if ( i == 1 )
            attack();
          else if ( i == 2 )
            // use item
          else if ( i == 3 ) {
            System.out.println("You try to flee but the Monster slashes at you.")

            d2 = smaug.attack( pat );

            System.out.println( "\n" + "Monster smacked " + pat.getName() + " for " + d2 + " points of damage.");

            System.out.println("\nYe health: " + pat.getHealth());
          }
      }

    public void attack() {
      int d1, d2;

      d1 = pat.attack( smaug );

      System.out.println( "\n" + pat.getName() + " dealt " + d1 + " points of damage.");

      System.out.println("\nMonster health: " + smaug.getHealth());

      // healing Role
      if (pat.getRole() == "Priest") {
        pat.getHeal();
        System.out.println("\nYe health for 5!");
        System.out.println("\nYe health: " + pat.getHealth());
      }

      d2 = smaug.attack( pat );

      System.out.println( "\n" + "Monster smacked " + pat.getName() + " for " + d2 + " points of damage.");

      System.out.println("\nYe health: " + pat.getHealth());
    }

    public static void main( String[] args ) {
      YoRPG game = new YoRPG();
      while( pat.isAlive() ) {
        game.Turn();
        stages += 1;
      }
      System.out.println( "Thy game doth be over." );


  }







}
