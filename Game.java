import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;
  private static String clearSpaces = "";
  private static ArrayList<String> NAMES;

  public static void main(String[] args) {
    while(clearSpaces.length()<WIDTH-2)
      clearSpaces+=" ";
    this.NAMES = new ArrayList<String>{"Abby","Bob","Charlie","David", "Ethan", "Franz", "Gabriel"};
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    Text.color(BORDER_COLOR, BORDER_BACKGROUND);
    for (int i = 0; i<=WIDTH; i++)
    {
      Text.go(0,i);
      System.out.print("*");
      Text.go(7,i);
      System.out.print("*");
      Text.go(HEIGHT-6,i);
      System.out.print("*");
      Text.go(HEIGHT,i);
      System.out.print("*");
    }
    for (int i = 0; i<=HEIGHT; i++)
    {
      Text.go(i,0);
      System.out.print("*");
      Text.go(i,WIDTH);
      System.out.print("*");
      if (i<7)
      {
        Text.go(i,28);
        System.out.print("*");
        Text.go(i,54);
        System.out.print("*");
      }
      if (i>HEIGHT-6)
      {
        Text.go(i,28);
        System.out.print("*");
        Text.go(i,54);
        System.out.print("*");
      }
      Text.color(BORDER_BACKGROUND, BORDER_COLOR);
    }
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startRow, startCol);
    System.out.println(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    ArrayList<String> textBits = new ArrayList<String>(height);
    for (int i = 1; i<text.length(); i++)
    {
      if (i == width+1 || text.charAt(i-1) == '\n')
      {
        if (text.charAt(i-1) == '\n')
          textBits.add(text.substring(0,i-1));
        else
          textBits.add(text.substring(0,i));
        text = text.substring(i);
        i = 0;
      }
    }
    if (!text.equals(""))
      textBits.add(text);
    while(textBits.size() > height)
      textBits.remove(textBits.size()-1);
    for (int i = 0; i<textBits.size(); i++) {
      while(textBits.get(i).length()<width)
        textBits.set(i, textBits.get(i) + " ");
    }
    while(textBits.size() < height)
      textBits.add(" ".repeat(width));
    for (int i = 0; i<textBits.size(); i++) {
      drawText(Text.colorize(textBits.get(i), Text.WHITE, 0), row+i, col);
    }
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int choice = Math.Random()*4;
      int index = Math.Random()*NAMES.size();
      String name = NAMES.get(index);
      NAMES.remove(index);
      if (choice == 0){
        return new CodeWarrior(name);
      }
      else if (choice == 1){
        return new HIV(name);
      }
      else if (choice == 2){
        return new COVID(name);
      }
      else{
        return new Norovirus(name);
      }
    }


    public static void drawParty(ArrayList<Adventurer> party,int startRow){
      for (int i = 0; i<party.size(); i++)
      {
        int col = 2;
        if (i == 1)
          col = 29;
        else if (i == 2)
          col = 55;
        TextBox(startRow, col, 25, 2, party.get(i)+"");
        drawText("HP: "+colorByPercent(party.get(i).getHP(), party.get(i).getmaxHP()), startRow+2, col);
        drawText(party.get(i).getSpecialName()+": "+colorByPercent(party.get(i).getSpecial(), party.get(i).getSpecialMax()), startRow+3, col);
        drawText("Immune system level: "+party.get(i).getImmuneSystem()+"x", startRow+4, col);
      }
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    if ((float)hp/maxHP< 0.25)
      output = Text.colorize(output, Text.RED);
    else if ((float)hp/maxHP< 0.75)
      output = Text.colorize(output, Text.YELLOW);
    else
      output = Text.colorize(output, Text.WHITE);
    return output;
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> us, ArrayList<Adventurer> them)
  {
    drawBackground();
    drawParty(us, 2);
    drawParty(them, 25);
  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(8, 2);
      //show cursor
      Text.showCursor();
      String input = in.nextLine();
      Text.hideCursor();
      drawText(Text.colorize(clearSpaces, Text.WHITE, 0), 8, 2);

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    enemies.add(createRandomAdventurer());
    enemies.add(createRandomAdventurer());
    enemies.add(createRandomAdventurer());

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    party.add(createRandomAdventurer());
    party.add(createRandomAdventurer());
    party.add(createRandomAdventurer());

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    String log = preprompt;
    TextBox(9, 2, 78, 15, Text.colorize(log, Text.WHITE, 0));

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);
      if (input != "")
        log = input+"\n"+log;
      TextBox(9, 2, 78, 15, Text.colorize(log, Text.WHITE, 0));
      //example debug statment


      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.startsWith("attack ") || input.startsWith("a ")){
          party.get(whichPlayer).attack(enemies.get(Integer.parseInt(input.substring(input.indexOf(' ')+1))));
        }
        else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          log = prompt+"\n"+log;


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";
          log = prompt+"\n"+log;

          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";
        log = prompt+"\n"+log;

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
        log = prompt+"\n"+log;
      }

      //display the updated screen after input has been processed.
      drawScreen(party, enemies);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
