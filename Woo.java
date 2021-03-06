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
  private Monster finalBoss;

  private InputStreamReader isr;
  private BufferedReader in;

  private ArrayList<Item> items = new ArrayList<Item>();
  private ArrayList<Item> buyShop = new ArrayList<Item>();
  private ArrayList<Item> inventory = new ArrayList<Item>();
  private ArrayList<Integer> issaSword = new ArrayList<Integer>();
  private ArrayList<Integer> issaPotion = new ArrayList<Integer>();
  private ArrayList<Integer> issaShield = new ArrayList<Integer>();

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
    s += "\t1: Easy: \n\tRogue: " + Rogue.about() + "\n\n";
    s += "\t2: Normal: \n\tWarrior: " + Warrior.about() + "\n\n";
    s += "\t3: Hard: \n\tAssassin: " + Assassin.about() + "\n\n";
    s += "\t4: Very Hard: \n\tThief: " + Thief.about() + "\n\n";
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
      pat = new Rogue(name, 20, 1, 0);
      Item bracelet_of_life = new Bracelet("Bracelet of Life", nextitemId, "A legendary healing bracelet", 100, 1);
      items.add(bracelet_of_life);
      inventory.add(bracelet_of_life);
      nextitemId += 1;
    } else if (playerChoice == 2) {
      pat = new Warrior(name, 20, 1, 0);
      Item bracelet_of_strength = new Bracelet("Bracelet of Strength", nextitemId, "A legendary strenth bracelet", 10, 1);
      items.add(bracelet_of_strength);
      inventory.add(bracelet_of_strength);
      nextitemId += 1;
    } else if (playerChoice == 3) {
      pat = new Assassin(name, 20, 1, 0);
      Item bracelet_of_stealth = new Bracelet("Bracelet of Stealth", nextitemId, "A legendary speed bracelet", 10, 1);
      items.add(bracelet_of_stealth);
      inventory.add(bracelet_of_stealth);
      nextitemId += 1;
    } else if (playerChoice == 4) {
      pat = new Thief(name, 20, 1, 0);
      Item bracelet_of_wealth = new Bracelet("Bracelet of Wealth", nextitemId, "A legendary wealth bracelet", 10, 1);
      items.add(bracelet_of_wealth);
      inventory.add(bracelet_of_wealth);
      nextitemId += 1;
    } else if (playerChoice == 5) {
      pat = new Cursed_Hero(name, 20, 1, 0);
      Item bracelet_of_death = new Bracelet("Bracelet of Death", nextitemId, "An unremovable cursed bracelet", 10, 1);
      items.add(bracelet_of_death);
      inventory.add(bracelet_of_death);
      nextitemId += 1;
    } else {
      System.out.println("Hero unidentified. The easiest difficulty has been selected.");
      pat = new Rogue(name, 20, 1, 0);
      Item bracelet_of_life = new Bracelet("Bracelet of Life", nextitemId, "A legendary healing bracelet", 10, 1);
      items.add(bracelet_of_life);
      inventory.add(bracelet_of_life);
      nextitemId += 1;
    }
    Item rusty_dagger = new Sword("Rusty Dagger", nextitemId, "A really reusty dagger", 50, 2);
    nextitemId += 1;
    Item flimsy_shield = new Shield("Flimsy Shield", nextitemId, "A terrible shield", 20, 2);
    inventory.add(rusty_dagger);
    inventory.add(flimsy_shield);
  } // end newGame

  // one turn/stage
  public boolean Turn() {
    String s;
    if (pat.isAlive()) {
      // daily bracelet of life effect
      if (inventory.get(0).getName() == "Bracelet of Life") {
        if (pat.getHealth() < 20) {
          System.out.println("\nYour Bracelet of Life healed you for 1 hp!");
          pat.addHealth(1);
        }
      }
      System.out.println("\nDay " + days + "\tHealth: " + pat.getHealth() + "\tAttack: " + pat.getAttack() + "\tDefense: " + pat.getDefense() + "\tGems: " + pat.getGems());
      // randomly assigns the stage depending on day number
      if (days % 7 != 0 && days != maxDays) {
        int stageSel = (int) (Math.random() * 10);
        // receives a random amount of gems, has a choice on next action
        if (stageSel < 3) {
          int randGems = ranGems();
          System.out.println("\nYou looked around and found " + randGems + " gems!");
          pat.addGems(randGems);
          free();
        }
        // fights a monster
        else if (stageSel < 7) {
          battleMonster();
        }
        // receives a random item
        else if (stageSel < 10) {
          Item luckyItem = randItem();
          System.out.println("\nYou found a " + luckyItem.getName() + "!\tDurability: " + luckyItem.getDurability() + "\tPower: " + luckyItem.getPower() );
          // if inventory full
          if (inventory.size() > 4) {
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
        finalBoss();
      }
      // shop
      else if (days % 7 == 0) {
        System.out.println("As you walk forward, you see a merchant ahead of you.");
        shop();
      }
      // daily bracelet of wealth effect
      if (inventory.get(0).getName() == "Bracelet of Wealth") {
        int wealth = (int) (Math.random() * 4);
        System.out.println("Your Bracelet of Wealth has given you gems!");
        pat.addGems(wealth);
      }
      // daily bracelet of death effect
      if (inventory.get(0).getName() == "Bracelet of Death") {
        System.out.println("Your Bracelet of Death is eating away at your health!");
        pat.subtractHealth(1);
        if (pat.isAlive()) {
          return true;
        }
        return false;
      }
      return true;
    }
    // player dead
    else {
      return false;
    }
  }

  public int ranGems() {
    int randGems = 0;
    if (days < 10) {
      randGems = 2 * ((int) (Math.random() * 5));
    }
    else if (days < 20) {
      randGems = (int) (days / 4) * (int) (Math.random() * 10);
    }
    else if (days < 30) {
      randGems = (int) (days / 4) * (int) (Math.random() * 10);
    }
    return randGems;
  }

  public void free () {
    String s = "\nYou do not spot any monsters today. What would you like to do?\n";
    s += "\t1: Search for monsters\n";
    s += "\t2: Use Item\n";
    s += "\t3: Sleep\n";
    s += "Selection: ";
    int turnChoice = 0;
    System.out.println(s);
    try {
      turnChoice = Integer.parseInt(in.readLine());
    }
    catch ( IOException e ) { }
    if (turnChoice == 1) {
      battleMonster();
    }
    else if (turnChoice == 2) {
      if (!(useItem())){
        System.out.println("\nYou decide not to use any items and go to sleep.");
      }
    }
    else if (turnChoice == 3) {
      System.out.println("\nYou decide to take a nap.");
      return;
    }
    else {
      System.out.println("\nWe could not identify your action. You went to sleep for the night.");
      return;
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

  public void battleMonster() {
    if (days < 10) {
      int monsterChoice = (int) (Math.random() * 4);
      if (monsterChoice == 0) {
        smaug = new Rat("Big Rat", 4, 4, 0);
      }
      else if (monsterChoice == 1) {
        smaug = new Crow("Crow", 4, 4, 0);
      }
      else if (monsterChoice == 2) {
        smaug = new Slug("Giant Slug", 4, 5, 0);
      }
      else if (monsterChoice == 3) {
        smaug = new Goblin("Goblin", 6, 4, 0);
      }
    }
    else if (days < 20) {
      int monsterChoice = (int) (Math.random() * 8);
      if (monsterChoice == 0) {
        smaug = new Wyvern("Wyvern", 6, 6, 1);
      }
      else if (monsterChoice == 2) {
        smaug = new Orge("Orge", 9, 5, 1);
      }
      else if (monsterChoice == 3) {
        smaug = new Demon("Demon", 11, 6, 0);
      }
      else if (monsterChoice == 4) {
        smaug = new Harpy("Harpy", 7, 7, 0);
      }
      else if (monsterChoice == 5) {
        smaug = new Basilisk("Basilisk", 6, 6, 2);
      }
      else if (monsterChoice == 6) {
        smaug = new Chimera("Chimera", 6, 7, 0);
      }
      else if (monsterChoice == 7) {
        smaug = new Griffin("Griffin", 8, 6, 1);
      }
    }
    else if (days < 30) {
      int monsterChoice = (int) (Math.random() * 6);
      if (monsterChoice == 0) {
        smaug = new Salamander("Salamander", 8, 4, 3);
      }
      else if (monsterChoice == 1) {
        smaug = new Dragon("Dragon", 6, 5, 4);
      }
      else if (monsterChoice == 2) {
        smaug = new Sphinx("Sphinx", 9, 6, 0);
      }
      else if (monsterChoice == 3) {
        smaug = new Cyclops("Cyclops", 12, 8, 0);
      }
      else if (monsterChoice == 4) {
        smaug = new Kobold("Kobold", 8, 10, 0);
      }
      else if (monsterChoice == 5) {
        smaug = new Minotaur("Minotaur", 6, 8, 2);
      }
    }
    System.out.println( "\nYou spot a " + smaug.getName() + " ahead. \n" + smaug.getName() + "\tHealth: " + smaug.getHealth() + "\tAttack: " + smaug.getAttack() + "\tDefense: " + smaug.getDefense() );
    battleChoice();
  }

  public void battleChoice() {
    int i = 1;
    while( smaug.isAlive() && pat.isAlive() ) {
      System.out.println( "\nWhat is your choice?" );
      System.out.println( "\t1: Attack\n\t2: Use Item\n\t3: Flee\nSelection: " );
      try {
        i = Integer.parseInt( in.readLine() );
      }
      catch ( IOException e ) { }
      if ( i == 1 ) {
        weapon();
      }
      else if ( i == 2 ) {
        if (!(useItem())) {
          battleChoice();
        }
        else {
        characterAttack(smaug, pat, 1, 0, useShield()); // no attack weapon
        }
      }
      else if ( i == 3 ) {
        int fleeChance = (int) (Math.random() * 2);
        if (fleeChance == 0) {
          System.out.println("\nThe " + smaug.getName() + " swings down on you, but you quickly dodge to the side. You escape in time before the " + smaug.getName() + "can land another hit.");
          return;
        }
        else {
          System.out.println("\nYou begin to escape, but the " + smaug.getName() + " slashes down at you one time and lands a hit before you escape.");
          characterAttack(smaug, pat, 1, 0, useShield());
          return;
        }
      }
    }
    if (pat.isAlive()) {
      int randGems = ranGems();
      System.out.println("\nYou defeated the " + smaug.getName() + " and received " + randGems + " gems!");
      pat.addGems(randGems);
    }
  }

  public void weapon() {
    int itemChoice = 1;
    int weaponCount = 3;
    String s = "\nWhich weapon will you use?\n";
    s += "\t1: Back\n";
    s += "\t2: Fist\tPower: 1\n";
    issaSword.clear();
    for (int j = 0; j < inventory.size(); j++) {
      if (inventory.get(j) instanceof Sword) {
        issaSword.add(j); // adds inventory index
        s += "\t" + weaponCount + ": " + displayInventoryItem(j) + "\n";
        weaponCount += 1;
      }
    }
    s += "Selection: ";
    System.out.print( s );
    try {
      itemChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (itemChoice == 1) {
      return;
    }
    else if (itemChoice == 2) {
      attack(0);
    }
    else if (itemChoice > 2 && itemChoice < 7) {
      attack(inventory.get(issaSword.get(itemChoice - 3)).getPower()); // deal damage
      useItem(issaSword.get(itemChoice - 3)); // reduce durability
    }
  }

  public boolean useItem() {
    int itemChoice = 1;
    int itemCount = 2;
    String s = "\nWhich item will you use?\n";
    s += "\t1: Back\n";
    issaPotion.clear();
    for (int j = 0; j < inventory.size(); j++) {
      if (inventory.get(j) instanceof Potion) {
        issaPotion.add(j); // adds inventory index
        s += "\t" + itemCount + ": " + displayInventoryItem(j) + "\n";
        itemCount += 1;
      }
    }
    s += "Selection: ";
    System.out.print( s );
    try {
      itemChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (itemChoice == 1) {
      return false;
    }
    else if (itemChoice > 2 && itemChoice < 6) {
      if (inventory.get(issaPotion.get(itemChoice - 2)) instanceof Potion) {
        if (inventory.get(issaPotion.get(itemChoice - 2)).getName() == "Potion of Healing") {
          pat.setHealth(20);
          inventory.remove(issaPotion.get(itemChoice - 2));
          System.out.println("You used a potion of healing and now you are at max health!");
        }
      }
    }
    return true;
  }

  public int useShield() {
    String s;
    issaShield.clear();
    for(int idx = 0; idx < inventory.size(); idx++) {
      if (inventory.get(idx) instanceof Shield) {
        issaShield.add(idx);
      }
    }
    if (issaShield.size() > 0) {
      int itemCount = 1;
      int itemChoice = 1;
      s = "\nWhich shield will you use?\n";
      for (int j = 0; j < inventory.size(); j++) {
        if (inventory.get(j) instanceof Shield) {
          s += "\t" + itemCount + ": " + displayInventoryItem(j) + "\n";
          itemCount += 1;
          }
      }
      s += "Selection: ";
      System.out.println( s );
      try {
        itemChoice = Integer.parseInt( in.readLine() );
      }
      catch (IOException e) { }
      if (itemChoice > 0 && itemChoice < 5) {
        int shieldPower = inventory.get(issaShield.get(itemChoice - 1)).getPower();
        useItem(issaShield.get(itemChoice - 1)); // reduce durability
        return shieldPower;
      }
      return 0;
    }
    return 0;
  }

  // use item from inventory
  public void useItem(int index) {
    if (inventory.get(index) instanceof Sword || inventory.get(index) instanceof Shield) {
      int reducedDur = 5 * ((int) (Math.random() * 6));
      inventory.get(index).reduceDurability(reducedDur);
    }
    if (inventory.get(index).getDurability() <= 0) {
      System.out.println("Your " + inventory.get(index).getName() + "has broke!");
      inventory.remove(index);
    }
    return;
  }

  // deals with the attack order
  public void attack(int itemPower) {
    int damageMulti = 1;
    int attackOrder = (int) (Math.random() * 7);
    // bracelet of stealth effects
    if ((inventory.get(0).getName() == "Bracelet of Strength") && (days % 4 == 0)) {
      damageMulti = 2;
      System.out.println("Your Bracelet of Strength flashes!");
    }
    if (inventory.get(0).getName() == "Bracelet of Stealth") {
      characterAttack(pat, smaug, damageMulti, itemPower, 0);
      if (smaug.isAlive()) {
        characterAttack(smaug, pat, damageMulti, itemPower, useShield());
      }
      else {
        return;
      }
    }
    else if (attackOrder < 3) {
      characterAttack(smaug, pat, damageMulti, itemPower, useShield());
      if (pat.isAlive()){
        characterAttack(pat, smaug, damageMulti, itemPower, 0);
      }
      else {
        return;
      }
    }
    else {
      characterAttack(pat, smaug, damageMulti, itemPower, 0);
      if (smaug.isAlive()){
        characterAttack(smaug, pat, damageMulti, itemPower, useShield());
      }
      else {
        return;
      }
    }
  }

  public int characterAttack(Character attacker, Character attacked, int damageMulti, int itemPower, int shieldPower) {
    int totalDamage = (attacker.getAttack() + itemPower) * damageMulti;
    int damage = totalDamage - attacked.getDefense() - shieldPower;
    if (damage < 0) {
      damage = 0;
    }
    attacked.subtractHealth(damage);
    System.out.println( "\n" + attacker.getName() + " dealt " + damage + " damage.");
    System.out.println(attacked.getName() + "\tHealth: " + attacked.getHealth() + "\tAttack: " + attacked.getAttack() + "\tDefense: " + attacked.getDefense() );
    return damage;
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

  public Item randItem() {
    Item luckyItem = createItem("", nextitemId, "", "", 0, 0);
    int randDur = 2 * ((int) (Math.random() * 51));
    if (days < 10) {
      int randItem = (int) (Math.random() * 6);
      if (randItem == 0) {
        luckyItem = createItem("Rusty Dagger", nextitemId, "sword", "", randDur, 2);
      }
      else if (randItem == 1) {
        luckyItem = createItem("Wooden Shield", nextitemId, "shield", "", randDur, 1);
      }
      else if (randItem == 2) {
        luckyItem = createItem("Potion of Healing", nextitemId, "potion", "", randDur, 0);
      }
      else if (randItem == 3) {
        luckyItem = createItem("Pitchfork", nextitemId, "sword", "", randDur, 3);
      }
      else if (randItem == 4) {
        luckyItem = createItem("Pickaxe", nextitemId, "sword", "", randDur, 1);
      }
      else if (randItem == 5) {
        luckyItem = createItem("Cardboard", nextitemId, "shield", "", randDur, 0);
      }
    }
    else if (days < 20) {
      int randItem = (int) (Math.random() * 6);
      if (randItem == 0) {
        luckyItem = createItem("Goblin Club", nextitemId, "sword", "", randDur, 5);
      }
      else if (randItem == 1) {
        luckyItem = createItem("Leather Shield", nextitemId, "shield", "", randDur, 2);
      }
      else if (randItem == 2) {
        luckyItem = createItem("Potion of Healing", nextitemId, "potion", "", randDur, 1);
      }
      else if (randItem == 3) {
        luckyItem = createItem("Katana", nextitemId, "sword", "", randDur, 3);
      }
      else if (randItem == 4) {
        luckyItem = createItem("Cutlass", nextitemId, "sword", "", randDur, 4);
      }
      else if (randItem == 5) {
        luckyItem = createItem("Stone Shield", nextitemId, "sword", "", randDur, 3);
      }
    }
    else if (days < 30) {
      int randItem = (int) (Math.random() * 6);
      if (randItem == 0) {
        luckyItem = createItem("Excalibur", nextitemId, "sword", "", randDur, 6);
      }
      else if (randItem == 1) {
        luckyItem = createItem("Diamond Shield", nextitemId, "shield", "", randDur, 6);
      }
      else if (randItem == 2) {
        luckyItem = createItem("Potion of Healing", nextitemId, "potion", "", randDur, 1);
      }
      else if (randItem == 3) {
        luckyItem = createItem("Long Sword", nextitemId, "sword", "", randDur, 5);
      }
      else if (randItem == 4) {
        luckyItem = createItem("Round Shield", nextitemId, "shield", "", randDur, 5);
      }
      else if (randItem == 5) {
        luckyItem = createItem("Stone Shield", nextitemId, "sword", "", randDur, 3);
      }
    }
    return luckyItem;
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
    if (pat.getGems() < buyPrice) {
      System.out.println("Sorry, you do not have enough gems!");
      shop();
    }
    buyShop.clear();
    for (int i = 0; i < 3; i++) {
      Item shopItem = randItem();
      buyShop.add(shopItem);
    }
    s = "\nAnything that will help you on your journey?\n";
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
    int counter = 1;
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
    s = "\nWhat would you like to sell??\n";
    s += "\t" + counter + ": Exit Shop \n";
    counter += 1;
    s += "\t" + counter + ": Back \n";
    counter += 1;
    for (int i = 1; i < inventory.size() - 1; i++) {
      s += "\t" + counter + ": " + displayInventoryItem(i) + "\tSell Price: " + (int) (sellPrice * (0.01 * inventory.get(i).getDurability())) + "\n";
      counter += 1;
    }
    s += "Selection: \n";
    System.out.print( s );
    try {
      sellChoice = Integer.parseInt( in.readLine() );
    }
    catch (IOException e) { }
    if (sellChoice == 1) {
      return;
    }
    else if (sellChoice == 2) {
      shop();
    }
    else if (sellChoice < 7) {
      int price = (int) (sellPrice * (0.01 * inventory.get(sellChoice - 2).getDurability()));
      System.out.println("You sold your " + inventory.get(sellChoice - 2).getName() + " for " + price + " gems!");
      inventory.remove(sellChoice - 2);
      pat.addGems(price);
    }
  }

  public void finalBoss() {
    finalBoss = new Armored_Titan("Armored Titan", 36, 8, 2);
    System.out.println( "\nYou spot the final boss, " + smaug.getName() + " ahead. \nHealth: " + smaug.getHealth() + "\tAttack: " + smaug.getAttack() + "\tDefense: " + smaug.getDefense() );
    battleChoice();
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
      System.out.println("You have died.\nBetter luck next time.");
    }
  }
}
