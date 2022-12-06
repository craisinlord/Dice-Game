public class ComputerGuess {
  //attributes
  private String player1;
  private int num;
  private int guess;
  private int diceNum;
  private int count;
  private int compnum1;
  private int compnum2;
  private int compnum3;
  private int compnum4;
  private int compnum5;
  private int plnum1;
  private int plnum2;
  private int plnum3;
  private int plnum4;
  private int plnum5;
  private int playerDiceNum;
  private int compDiceNum;
  private int dieFaces;
  
  //constructor
  public ComputerGuess(String name1) {
    player1 = name1;
  }
  
  //Rolling code
  public int[] roll(int dice, int faces){
      diceNum = dice;
      dieFaces = faces;
      int[] ans = new int[5];
      ans[0] = (int)(Math.random()*dieFaces)+1;
      ans[1] = (int)(Math.random()*dieFaces)+1;
      ans[2] = (int)(Math.random()*dieFaces)+1;
      ans[3] = (int)(Math.random()*dieFaces)+1;
      ans[4] = (int)(Math.random()*dieFaces)+1;
      if (dice==4) {
        ans[4]=0;
      }
      else if (dice==3) {
        ans[4]=0;
        ans[3]=0;
      }
      else if (dice==2) {
        ans[4]=0;
        ans[3]=0;
        ans[2]=0;
      }
      else if (dice==1) {
        ans[4]=0;
        ans[3]=0;
        ans[2]=0;
        ans[1]=0;
      }
      return ans;
  }

  public int count(int n, int g, int comp1, int comp2, int comp3, int comp4, int comp5, int pl1, int pl2, int pl3, int pl4, int pl5){
    count = 0;
    num = n;
    guess = g;
    compnum1 = comp1;
    compnum2 = comp2;
    compnum3 = comp3;
    compnum4 = comp4;
    compnum5 = comp5;
    plnum1 = pl1;
    plnum2 = pl2;
    plnum3 = pl3;
    plnum4 = pl4;
    plnum5 = pl5;
    if (compnum1==num||compnum1==1){
      count++;
    } 
    if (compnum2==num||compnum2==1){
      count++;
    }
    if (compnum3==num||compnum3==1){
      count++;
    }
    if (compnum4==num||compnum4==1){
      count++;
    }
    if (compnum5==num||compnum5==1){
      count++;
    }
    if (plnum1==num||plnum1==1){
      count++;
    }
    if (plnum2==num||plnum2==1){
      count++;
    }
    if (plnum3==num||plnum3==1){
      count++;
    }
    if (plnum4==num||plnum4==1){
      count++;
    }
    if (plnum5==num||plnum5==1){
      count++;
    }
    return count;
  }
  
  public boolean callPerudo(int n, int g, int comp1, int comp2, int comp3, int comp4, int comp5, int pDN, int cDN) {
    playerDiceNum = pDN;
    compDiceNum = cDN;
    int compGuess = 1;
    double ran = Math.random();
    System.out.println(ran);
    num = n;
    guess = g;
    compnum1 = comp1;
    compnum2 = comp2;
    compnum3 = comp3;
    compnum4 = comp4;
    compnum5 = comp5;
    if (compnum1==num||compnum1==1){
      compGuess++;
    } if (compnum2==num||compnum2==1){
      compGuess++;
    } if (compnum3==num||compnum3==1){
      compGuess++;
    } if (compnum4==num||compnum4==1){
      compGuess++;
    } if (compnum5==num||compnum5==1){
      compGuess++;
    } if (compGuess>=(guess/2)) {
      compGuess ++; 
    } if (ran>0.8){
      compGuess ++;
    } if (guess>compGuess) {
      return true;
    } else {
      return false;
    }
  }
  
  public int[] newGuess(int n, int g, int comp1, int comp2, int comp3, int comp4, int comp5, int pDN, int cDN){
    compDiceNum = cDN;
    playerDiceNum = pDN;
    int[] guessArray = new int[2];
    int raise = 0;
    double ran1 = Math.random();
    System.out.println(ran1);//Test code
    num = n;
    guessArray[1] = num;
    guess = g;
    compnum1 = comp1;
    compnum2 = comp2;
    compnum3 = comp3;
    compnum4 = comp4;
    compnum5 = comp5;
    int compNum = 0;
      if (compnum1==num||compnum1==1){
      compNum++;
    } if (compnum2==num||compnum2==1){
      compNum++;
    } if (compnum3==num||compnum3==1){
      compNum++;
    } if (compnum4==num||compnum4==1){
      compNum++;
    } if (compnum5==num||compnum5==1){
      compNum++;
    } 
    if (compNum>guess) {
      raise = (compNum-guess);
      if ((compNum-guess)<=3&&compNum>1&&ran1>0.5) raise ++;
    } 
    else if (compNum==guess) {//fixed issue with computer not raising at all if it has the same number as the amount guessed
      raise ++;
    }
    else if (guess>compNum) {
      if (num==dieFaces) {
        raise++;
      } else {
        compNum = 0;
        num ++;
        System.out.println("Attempted number: " + num); //test code
        if (compnum1==num||compnum1==1){
          compNum++;
        } if (compnum2==num||compnum2==1){
          compNum++;
        } if (compnum3==num||compnum3==1){
          compNum++;
        } if (compnum4==num||compnum4==1){
          compNum++;
        } if (compnum5==num||compnum5==1){
          compNum++;
        }
        if (compNum>=3&&ran1>0.5) {
          raise ++;
        }
        if (compNum<=(compDiceNum/2)) {////change later so it adjusts
          num--;
          raise=1;
        }
      }
    } 
    guessArray[0] = raise + guess;
    guessArray[1] = num;
    return guessArray;
  }
}