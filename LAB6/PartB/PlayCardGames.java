import java.util.*;

//TO RUN THE AWESOMER VERSION OF THIS LAB, REMOVE ALL THE "/*" and "*/"!!!!!

public class PlayCardGames
{
	public static void main(String[] args)
	{
		//just for the lolz
		/*UNCOMMENT THIS SECTION FOR AN AWESOME PROGRAM
		System.out.println("WELCOME TO TROPHIES CASINO! THIS PROGRAM DOESN'T DO MUCH, BUT IT HAS GREAT POTENTIAL");
		System.out.println("\tSO EVEN IF YOU SUCH AT GAMBLING YOU CAN'T LOSE MONEY! YAY\n");
		Scanner scan = new Scanner(System.in);
		System.out.print("Gambling problem?! Type q to quit, anything else to continue: ");
		String user = scan.nextLine();
		char input;
		if (!(user.isEmpty()))
		{
			input = user.toLowerCase().charAt(0);
		}
		else
		{
			input = ' ';
		}
		
		if (input != 'q')
		{
					//System.out.println("Entering card games");
		*/
			//the real stuff
			Poker poker = new Poker();
			Bridge bridge = new Bridge();
		
			//test to see if all cards have been added to ArrayList
			CardGame cgPoker = (CardGame)poker;
			CardGame cgBridge = (CardGame)bridge;
			System.out.println("\nDeck of Cards:");
			System.out.println(cgPoker.toString());
		
			//testing shuffle method with poker class
			System.out.println("STARTING GAME OF POKER\nPoker Prelude:");
			System.out.println(poker.displayDescription());
			System.out.println("\nLETS PLAY!\nDealing 5 cards to first player:");
			System.out.println(poker.deal());
			
			/* UNCOMMENT THIS FOR AN EVEN AWESOMER PROGRAM
			System.out.println("Dealing 5 cards to second player:"); //tests if shuffle method works multiple times
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to third player:");
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to fourth player:"); //tests if shuffle method works multiple times
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to fifth player:");
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to sixth player:"); //tests if shuffle method works multiple times
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to seventh player:");
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to eight player:"); //tests if shuffle method works multiple times
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to ninth player:");
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to tenth player:"); //tests if shuffle method works multiple times
			System.out.println(poker.deal());
			System.out.println("Dealing 5 cards to eleventh player:"); //test for insufficient amount of cards
			System.out.println(poker.deal());
			*/
		
			System.out.println("==========================================================================================");
		
			//test to see if all cards have been added to ArrayList after cards have been shuffled once
			System.out.println("\nDeck of Cards:");
			System.out.println(cgBridge.toString());
			
			//testing shuffle method with bridge class
			System.out.println("STARTING GAME OF BRIDGE\nBridge Prelude:");
			System.out.println(bridge.displayDescription());
			System.out.println("\nLETS PLAY!\nDealing 13 cards to first player:");
			System.out.println(bridge.deal());
			
			/* UNCOMMENT THIS FOR THE AWESOMEST PROGRAM
			System.out.println("Dealing 13 cards to second player:"); //tests if shuffle method works multiple times
			System.out.println(bridge.deal());
			System.out.println("Dealing 13 cards to third player:"); //tests if shuffle method works multiple times
			System.out.println(bridge.deal());
			System.out.println("Dealing 13 cards to fourth player:"); //tests if shuffle method works multiple times
			System.out.println(bridge.deal());
			System.out.println("Dealing 13 cards to fifth player:"); //test for insufficient amount of cards
			System.out.println(bridge.deal());
			*/
			
		/* ALSO UNCOMMENT THIS SO THE FIRST AWESOME PROGRAM CAN WORK
		}
		else
		{
					//System.out.println("Exiting the program");
			System.out.println("\nSorry to see you go :( Here's the number to the gambling addiction hotline: 1-800-522-4700");
			System.exit(0);
		}
		*/
	}
}