package assignment_one;
import java.util.Random;
import java.util.Scanner;


public class assignmentone_17al2 {
	//Assignment 1  Anne Liu 17al2
	
	static Random generator = new Random(System.currentTimeMillis());
	
	//plays the game by calling the game turns and isGameOver to decide if the game is finished
	public static void main(String[] args) {
		
		System.out.print( "Welcome to the game of Pig!" );
		Scanner reader= new Scanner(System.in);
		int humanTotalScore=0;
		int computerTotalScore = 0;
		int turnTimes=0;
		
		while(true){
			System.out.println("\n============================================");
			turnTimes++;
			System.out.println("it's your turn");
			humanTotalScore = humanTurn(humanTotalScore, reader);
			System.out.print( "\n" );
			System.out.println("your score is "+humanTotalScore+" and the total score for computer is "+computerTotalScore);
			System.out.print( "\n\n" );
			System.out.println("it's the computer's turn");	
			computerTotalScore = computerTurn(computerTotalScore);
			System.out.print( "\n" );
			System.out.println("The computer's score is "+computerTotalScore+" and the total score for computer is "+computerTotalScore);
			//check if game is over
			if (isGameOver(humanTotalScore,computerTotalScore)) {
				break;
			}
			//otherwise prompt if the next turn should be executed
			System.out.println("TURN #"+turnTimes+" is over! please press <any key> and <enter key> to start turn #"+(turnTimes+1));
			reader.next();
		} 
		
	}//end main
	
	/*takes in the total score to add to it for each roll.
	Takes in a reader to ask whether the user wants to roll again when calling rollAgain
	Returns the total score after the turn*/ 
	public static int humanTurn(int humanTotalScore, Scanner reader) {
		int turnScore=0;
		int rollNumber=0;
		boolean play= true;
		do {
			rollNumber+=1;
			System.out.println("roll Number: "+ rollNumber);
			//roll dice
			int dieOne= randomNum();
			int dieTwo= randomNum();
			
			//check for special combinations
			int diceCombination= diceChecker(dieOne,dieTwo);
			
			if (diceCombination==1)
				return 0;
			else if (diceCombination==2)
				return humanTotalScore;
			else if(diceCombination==3) {
				turnScore += 4*dieOne;
				play= true;
				System.out.println("The total score is: "+ humanTotalScore+" and the turn score is: "+turnScore);
			}
			else {
				turnScore+= dieOne+dieTwo;
				System.out.println("The total score is: "+ humanTotalScore+" and the turn score is: "+turnScore);
				play= rollAgain(reader);
			}
			
		}while(play);
		
		return(humanTotalScore+turnScore);
		
	}//end humanTurn
	
	/*takes in the total score to add to it for each roll.
	 Calls maxComputerScore to generate a random number for maximum score
	Returns the total score after the turn*/ 
	public static int computerTurn(int computerTotalScore) {
		
		int dieOne, dieTwo, turnScore=0, rollNumber=0;
		boolean play= true;
		
		do {
			//rolling dice
			dieOne= randomNum();
			dieTwo= randomNum();
	
			//check combinations
			int diceCombination= diceChecker(dieOne,dieTwo);
	
			if (diceCombination==1)
				return 0;
			else if (diceCombination==2)
				return computerTotalScore;
			else if(diceCombination==3) {
				turnScore += 4*dieOne;
				play= true;
				System.out.println("The total score is: "+ computerTotalScore+" and the turn score is: "+turnScore);
			}
			else {
				turnScore+= dieOne+dieTwo;
				System.out.println("The total score is: "+ computerTotalScore+" and the turn score is: "+turnScore);
				
				if (turnScore > maxComputerScore()){
					play= false;	
				}
			}
			
			if (turnScore+computerTotalScore >= 100) {	
			}
				
		}while(play);
		

		return (turnScore+ computerTotalScore);
		
	}//end computerTurn
	
	
	
	/* takes in the two dice rolls. checks whether they are two sixes, If it includes one six, if it is a double other than a six.
	Returns an integer to represent roll combination*/
	public static int diceChecker(int diceRollOne, int diceRollTwo) {
		
		//two sixes
		if (diceRollOne==6 && diceRollTwo==6) {
			System.out.println("two sixes were rolled!");
			return 1;
		}
		//one 6 rolled 	
		else if (diceRollOne==6 && diceRollTwo!=6 || diceRollTwo==6 && diceRollOne!=6) {
			System.out.println("a "+diceRollOne+" was rolled and a "+diceRollTwo+ " was rolled");
			return 2;
		}
		// doubles
		else if (diceRollOne==diceRollTwo) {
			System.out.println("two "+ diceRollOne+ " rolled");
			return 3;
		}
		else {
			System.out.println("a "+diceRollOne+" was rolled and a "+diceRollTwo+ " was rolled");
			return 4;
		}	
	}//end diceChecker		
	
	// return a random number between 1-6 as a dice roll
	public static int randomNum() {
		//print what is rolled
		return generator.nextInt(6)+1;
		
	}//end randomNum
	
	//generates a computer score 
	public static int maxComputerScore() {
		//random number between 20-30
		return generator.nextInt(30) + 20;
	}//end maxComputerScore
	
	//asks user if they want to roll again. Takes in a reader to read user input
	public static boolean rollAgain(Scanner reader) {

		System.out.println("Roll again? (Enter 'y' or 'Y' for again, any other key is not again)");
		String keyString = reader.next();
		if(keyString.equals("Y") || keyString.equals("y"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}//end rollAgain
	
	//determine if game is over and who has won
	public static boolean isGameOver(int humanTotalScore,int computerTotalScore){
		if((humanTotalScore>=100) ||(computerTotalScore>=100)) {
			
			System.out.println("Game over! \n"+" player score: "+humanTotalScore+ "  computer score:"+ computerTotalScore);
			if(humanTotalScore>computerTotalScore){
				System.out.println("Player Win !");
			}
			else if(humanTotalScore<computerTotalScore){
				System.out.println("Computer Win !");
			}
			else{
				System.out.println("Player equals Computer ");
			}
			return true;
		}
		return false;
	}// end isGameOver
}
