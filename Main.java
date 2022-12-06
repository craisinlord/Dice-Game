import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;

class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String info1 = "\nPlaying\n\nEvery round begins with players shaking the dice around in their cups and bringing it down on the table with the dice underneath, keeping the dice concealed from everyone except themselves.\nEach player in turn, calls out a quantity of a certain dice face value that have supposedly been rolled by all the players collectively. For example “four 3’s” means that you claim there are at least four dice with a face value of 3. Each player then subsequently calls out a higher hand than the previous. Either the number of identical dice must be higher or the face value must be larger or both.\n\nExamples:\nTwo 3’s is greater than three 2’s\nThree 3’s is greater than two 3’s\nFour 2’s is greater than three 3’s\nFive 4’s is greater than four 2’s\n\n1’s are wild and can represent any face value in a call.\n\nCalling Bluffs\n\nInstead of calling out a higher hand on their turn, a player can call a bluff on the previous player by saying “Perudo”. When this is done all dice are revealed. If there are not enough dice on hand to match the claim, the challenged player loses that round, and loses a dice. If the bluff is falsely called, then the challenger loses the round, and a dice of their own";
    System.out.println("\nWelcome to Perudo!");
    System.out.println("Can you beat the Computer? Please enter your name: ");
    String p1 = input.nextLine();
    System.out.println("");
    System.out.println("Hello " + p1 + ". Do you know how to play?");
    String rules = input.nextLine();
    while (rules.equalsIgnoreCase("no")){
      System.out.println("\nHere are the instructions: \n" + info1);
      System.out.println("\n\nAre you ready?");
      rules = input.nextLine();
    }
    System.out.println("\nLet's Play!!\n");
    ComputerGuess player1 = new ComputerGuess(p1);
    int compDice = 5;
    int playerDice = 5;
    boolean roundActive = true;
    int round = 0;
    int guessnum = 0;
    int guessquantity = 0;
    int count;
    int faces = 6;
    int oldGuessnum = 0;
    int minGuessquantity = 0;

    int[] p1roll;
    int[] croll;
    while(playerDice!=0&&compDice!=0) {
      p1roll = player1.roll(playerDice, faces);
      croll = player1.roll(compDice, faces);

      round ++;
      System.out.println("Start Round " + round + "!");
      System.out.println("Computer Rolls: " + Arrays.toString(croll));//Test code
      System.out.println("Player Rolls: " + Arrays.toString(p1roll));
      try { // Delay for 2 seconds
        Thread.sleep(2000);
      } catch(InterruptedException ex){
          ex.printStackTrace();
      }
      while (roundActive){
        System.out.println("\nHow many dice will there be? ");
        guessquantity = input.nextInt();
        //checks to make sure the quess quantity is not greater than the total dice or less than the last guess
        if (guessnum!=faces) {
          while (guessquantity > (compDice + playerDice) || guessquantity < minGuessquantity){
            System.out.println("That number is invalid. Please enter again.");
            guessquantity = input.nextInt();
          }
        } else {
          while (guessquantity > (compDice + playerDice) || guessquantity <= minGuessquantity) {
            System.out.println("That number is invalid. Please enter again.");
            guessquantity = input.nextInt();
          }
        }
        System.out.println("What number dice? ");
        guessnum = input.nextInt();
        //checks to make sure the guess number is not greater than the number of faces on the die, less than 2, or less than the old guess while the guess quantity is the same as the old guess quantity
        while (guessnum > faces || guessnum < 2 || (guessnum <= oldGuessnum && guessquantity == minGuessquantity)) {
          System.out.println("That number is invalid. Please enter again.");
          guessnum = input.nextInt();
        }
        //counts all the dice of the number guessed between both players
        count = player1.count(guessnum, guessquantity, croll[0], croll[1], croll[2], croll[3], croll[4], p1roll[0], p1roll[1], p1roll[2], p1roll[3], p1roll[4]);
        //sets a new minimum guess quantity and stores the last number guessed
        if (guessquantity>minGuessquantity) {
          minGuessquantity = guessquantity;
        }
        oldGuessnum = guessnum;

        System.out.println("Min Guess Quantity is " + minGuessquantity);
        System.out.println("Old Guess Number is " + oldGuessnum);
    
        //computer calling perudo
        boolean call = player1.callPerudo(guessnum, guessquantity, croll[0], croll[1], croll[2], croll[3], croll[4], playerDice, compDice);
        if (call){
          System.out.println("The Computer thinks you're lying... PERUDO!\n");
          if (count<guessquantity) {
              System.out.println("You lost! There were " + count + " " + guessnum + "s");
              playerDice--;
              System.out.println("Computer new dice total: " + compDice);
              System.out.println("Your new dice total: " + playerDice);
              System.out.println("Total dice: " + (compDice+playerDice));
              roundActive = false;
            } else if (count>=guessquantity) {
              System.out.println("You won! There were " + count + " " + guessnum + "s");
              compDice--;
              System.out.println("Computer new dice total: " + compDice);
              System.out.println("Your new dice total: " + playerDice);
              System.out.println("Total dice: " + (compDice+playerDice));
              roundActive = false;
            } else { System.out.println("If you see this something went wrong.");
              }
        } else {
          
          System.out.println("The Computer thinks you're right\n");
          //determines the computer's next guess and changes the guess quantity and min guess quantity to match
          int compRaise[] = player1.newGuess(guessnum, guessquantity, croll[0], croll[1], croll[2], croll[3], croll[4], playerDice, compDice);
          guessquantity = compRaise[0];
          guessnum = compRaise[1];
          oldGuessnum = guessnum;
          if (guessquantity>minGuessquantity) {
            minGuessquantity = guessquantity;
          }
          System.out.println("The Computer says there will be " + guessquantity + " " + guessnum + "s");
          System.out.println("Min Guess Quantity is " + minGuessquantity);
          System.out.println("old guess num " + oldGuessnum);
        
          //player calling perudo
          System.out.println("Call Perudo or raise the total");
          input.nextLine();
          String perudo = input.nextLine();
          if (perudo.equalsIgnoreCase("perudo")) {
            if (count>=guessquantity) {
              System.out.println("You lost! There were " + count + " " + guessnum + "s");
              playerDice--;
              System.out.println("Computer new dice total: " + compDice);
              System.out.println("Your new dice total: " + playerDice);
              System.out.println("Total dice: " + (compDice+playerDice));
              roundActive = false;
            } else if (count<guessquantity) {
              System.out.println("You won! There were " + count + " " + guessnum + "s");
              compDice--;
              System.out.println("Computer new dice total: " + compDice);
              System.out.println("Your new dice total: " + playerDice);
              System.out.println("Total dice: " + (compDice+playerDice));
              roundActive = false;
            } else { System.out.println("If you see this something went wrong.");
              }
          }
        }
      }
      oldGuessnum = 0;
      minGuessquantity = 1;
      roundActive = true;
      System.out.println("");
      try {
        Thread.sleep(3000);
      } catch(InterruptedException ex){
          ex.printStackTrace();
      }
    }
    if (compDice==0) {
      System.out.println("You WON!!! You are smarter than a Computer, congrats " + p1 + "!");
    } else if (playerDice==0) {
      System.out.println("You lost. The Computer is smarter than you " + p1 + ", that sucks");
    } else {
      System.out.println("If you see this something went wrong.");
    }
  }
}