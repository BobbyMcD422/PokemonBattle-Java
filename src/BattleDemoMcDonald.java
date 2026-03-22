import java.io.*;
import java.util.*;
/**
 * 
 * <b>Prog11</b> <br>
 * <ul>
 * <li>Due Date and Time: 4/17/25 before 9:00AM</li> <br>
 * 
 * <li>Purpose: Two Players, each with a play and discard stack, play
 * 				a battle game in which they <br>compare their cards and attempt
 * 				to capture each other's Pokemon Cards <br>
 * 
 * </li><br>
 * <li>
 * Input: Reads a given text file of Pokemon Card details which <br>
 * 		  are turned into Cards and added to their Respective Stacks<br>
 * </li><br>
 * <li>
 * Output: Prints out a game summary of the starting cards, plays, <br>
 * 		   total ending cards, and winning player.<br>
 * </li><br>
 * <li>
 * Certification of Authenticity: <br>
 * 		I certify that this lab is entirely my own work <br>
 * </li><br>
 * </ul>
 * @author <i>Robert McDonald</i> <br>
 */
public class BattleDemoMcDonald {

	//Keyboard
	static Scanner keyboard = new Scanner(System.in);
	static final boolean DEBUG = false;
	
	public static void main(String[] args) {
		//Initialize Variables
		StackMcDonald p1 = new StackMcDonald();
		StackMcDonald p1Discard = new StackMcDonald();
		StackMcDonald p2 = new StackMcDonald();
		StackMcDonald p2Discard = new StackMcDonald();
		int winner = 0;
		PokemonCardMcDonald card1 = null;
		PokemonCardMcDonald card2 = null;
		PokemonCardMcDonald roundWinner = null;
		int startAmount = 0;
		int plays = 0;
		//Greet User 
	  	System.out.println("Welcome To The Pokemon Battle Program!\n");
	  		
	  	//Describe Program and Open Menu
	  	System.out.println("This Program will Deal out Pokemon Cards from a \nFile and Play the Battle Game with those Cards!\n");
	  	System.out.println("You will be dealt from your input text file!");
	    //File Input
	    startAmount = deal(p1, p2);
	    //Prevents Unplayable Games
	    if(startAmount > 1)
	    	do {
	    		if(p1.isEmpty())
	    			if(!p1Discard.isEmpty())
	    				copy(p1Discard, p1);
	    			else
	    				winner = 2;
	    		else if(p2.isEmpty())
	    			if(!p1Discard.isEmpty())
	    				copy(p2Discard, p2);
	    			else
	    				winner = 1;
	    		else
	    		{
	    			//Get Cards
	    			card1 = play(p1);
	    			card2 = play(p2);
	    			//Find Winner
	    			roundWinner = compare(card1, card2);
	    			if(roundWinner != null)
	    			{
	    				//Debug Info / Tools
	    				if(DEBUG)
	    				{
	    					System.out.println(card1.toString());
	    					System.out.println(card2.toString());
	    					System.out.println(roundWinner.getPokemon() + " wins!\n");
	    				}//if
	    				//Player 1 Wins the Cards
	    				if(roundWinner.equals(card1))
	    					winPlay(p1Discard, card1, card2);
	    				//Player 2 Wins the Cards
	    				else
	    					winPlay(p2Discard, card2, card1);
	    			}//if
	    			//Cards have the same Stats
	    			else
	    			{
	    				p1Discard.push(card1);
	    				p2Discard.push(card2);
	    			}//else
	    			//Increment Plays
	    			plays++;
	    		}//else
	    	}while(plays < 1000 && winner == 0);
	    //Put the Cards left into the main Stacks
	    copy(p1Discard, p1);
	    copy(p2Discard, p2);
	    //Print Out Game Results
	    printResults(p1, p2, startAmount, plays, winner);
	}//main
	/**
	 * Deals cards into 2 given players' Stacks / Decks <br>
	 * (Alternating Deal read from input file of Card Details)
	 * 
	 * @param deck1 Player 1's Deck / Stack
	 * @param deck2 Player 2's Deck / Stack
	 */
	public static int deal(StackMcDonald deck1, StackMcDonald deck2)
	{
		//Initialize Variables
		PokemonCardMcDonald newCard = null;
		String name;
		int multiplier = 0;
		double power = 0;
		String fileName = null;
		File inputFile = null;
		int startCards = 0;
		System.out.print("\nEnter a Filename: ");
	    fileName = keyboard.next();
	    System.out.println();
	    
	    //Create File Reference
	    inputFile = new File(fileName);
	    int a = 0;
	    
		
	    //Try to Open and Use the File, if possible
	    try(Scanner input = new Scanner(inputFile))
	    {
	      
	      //Create Scanner object for reading from the file
	      
	      //Get Lines of Data File
	      startCards = input.nextInt();
	      
	      //Loop Through and Add Items
	      for(a = 0; a < startCards; a++)
	      {
	        // Read Input
	    	name = input.next();
	        multiplier = input.nextInt();
	        power = input.nextDouble();
	        newCard = new PokemonCardMcDonald(name, multiplier, power);
	        if(a % 2 == 0)
	        	deck1.push(newCard);
	        else
	        	deck2.push(newCard);
	      }//for loop
	      
	      //Close Inputs
	      input.close();
	    }//try
	    //Error Catching for File Input
	    catch(FileNotFoundException ex)
	    {
	      System.out.println("Failed to find file: " + inputFile.getAbsolutePath()); 
	    }//catch
	    catch(InputMismatchException ex)
	    {
	    	System.out.println("Type mismatch for the number I just tried to read.");
	        System.out.println(ex.getMessage());
	    }//catch
	    catch(NumberFormatException ex)
	    {
	      System.out.println("Failed to convert String text into an integer value.");
	      System.out.println(ex.getMessage());
	    }//catch
	    catch(NullPointerException ex)
	    {
	      System.out.println("Null pointer exception.");
	      System.out.println(ex.getMessage());
	    }//catch
	    catch(Exception ex)
	    {
	      // General Exception
	    	System.out.println("Something went wrong");
	      ex.printStackTrace();
	    }//catch
	    return a;
	}//deal
	/**
	 * Gives the cards to the pile in the correct order
	 * 
	 * @param pile Pile to stack cards into
	 * @param winner The better card which goes in first
	 * @param loser The worse card which goes in second
	 */
	public static void winPlay(StackMcDonald pile, PokemonCardMcDonald winner, PokemonCardMcDonald loser)
	{
		pile.push(winner);
		pile.push(loser);
	}//winPlay
	/**
	 * Gets a card from a given Stack
	 * 
	 * @param stack Stack of Pokemon Cards
	 * @return Pokemon Card from the Stack
	 */
	public static PokemonCardMcDonald play(StackMcDonald stack)
	{
		return stack.pop();
	}//play
	/**
	 * Copies a given stack into another stack
	 * 
	 * @param first Stack to take cards from
	 * @param second Stack to give cards to
	 */
	public static void copy(StackMcDonald first, StackMcDonald second)
	{
		StackMcDonald temp = new StackMcDonald();
		while(!first.isEmpty())
			temp.push(first.pop());
		while(!temp.isEmpty())
			second.push(temp.pop());
	}//copy
	/**
	 * Compare two given cards and determine the winner
	 * 
	 * @param c1 Player 1's Card
	 * @param c2 Player 2's Card
	 * @return The winning Card
	 */
	public static PokemonCardMcDonald compare(PokemonCardMcDonald c1, PokemonCardMcDonald c2)
	{
		PokemonCardMcDonald winner = null;
		if(c1.getPower() > c2.getPower())
			winner = c1;
		else if(c1.getPower() < c2.getPower())
			winner = c2;
		else
		{
			if(c1.getMultiplier() > c2.getMultiplier())
				winner = c1;
			else if(c1.getMultiplier() < c2.getMultiplier())
				winner = c2;
		}
		return winner;
	}//compare
	/**
	 * Counts the Cards in a given Stack
	 * 
	 * @param stack The stack with cards to count
	 * @return Amount of Cards Counted in the Stack
	 */
	public static int countCards(StackMcDonald stack)
	{
		int ans = 0;
		StackMcDonald temp = new StackMcDonald();
		while(!stack.isEmpty())
		{
			temp.push(stack.pop());
			ans++;
		}//while
		while(!temp.isEmpty())
			stack.push(temp.pop());
		return ans;
	}//countCards
	/**
	 * Prints out the Battle Game Results
	 * 
	 * @param player1 Player 1's deck
	 * @param player2 Player 2's deck
	 * @param cardsInDeck Cards that Started out in the Deck
	 * @param playCount Total Amount of Plays During Battle Game
	 * @param winPlayer The number representing the winning player
	 */
	public static void printResults(StackMcDonald player1, StackMcDonald player2, int cardsInDeck, int playCount, int winPlayer)
	{
		int a = 0;
		System.out.println("Battle Card Game Summary");
		for(a = 0; a < 25; a++)
			System.out.print("=");
		System.out.println();
		System.out.println("The game started with " + cardsInDeck + " cards.");
		System.out.println("There were " + playCount + " plays in the game.");
		if(winPlayer == 0)
			if(cardsInDeck > 1)
				System.out.println("The game took too long.");
			else
				System.out.println("The game was unplayable.");
		else
			System.out.println("The game ended with a clear winner.");
		System.out.println("Player 1 ended with " + countCards(player1) + " cards.");
		System.out.println("Player 2 ended with " + countCards(player2) + " cards.");
		System.out.print("The winner was ");
		if(winPlayer == 0)
			System.out.println("no one :(");
		else
			System.out.println("Player " + winPlayer + ".");
	}//printResults
}//BattleDemoMcDonald
