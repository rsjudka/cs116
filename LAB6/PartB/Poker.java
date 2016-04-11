import java.util.*;

public class Poker extends CardGame
{
	private static final int DEALS = 5;
	
	ArrayList<Card> dealer = super.getDeck(); //clone ArrayList in Bridge to be able to deal multiple hands with same deck
	
	public Poker()
	{
		super(DEALS);
				//System.out.println("Dealer will deal 5 cards");
	}
	
	public String displayDescription()
	{
				//System.out.println("displayDescription method called");
		return "According to Wikipedia, \"Poker is a family of gambling card games. All poker variants involve betting as an\n" +
							"intrinsic part of play, and determine the winner of each hand according to the combinations of players' cards,\n" +
							"at least some of which remain hidden until the end of the hand. Poker games vary in the number of cards dealt, \n" +
							"the number of shared or \"community\" cards, the number of cards that remain hidden, and the betting procedures.\"";
	}
	
	public String deal()
	{	
				//System.out.println("deal method called");
		String hand;
		int currSize = this.dealer.size(); //used to tell user how many cards are left
		try
		{
			//stuff to make a fancy program
			super.shuffle(this.dealer, this.dealer.size());
			hand = "";
			int start = this.dealer.size() - 1;
			int end = start - DEALS;
			//removes cards from deck as they are given to players
			for (int i = start; i > end; i--)
			{
				String s = dealer.get(i).getSuit();
				String v = dealer.get(i).getValue();
				hand += v + " of " + s;
				hand += "\n";
				this.dealer.remove(i);
				this.dealer.trimToSize();
			}
		}
		catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e)
		{
			hand = "Not enough cards for next player. " + currSize + " cards left.";
		}
		return hand;
	}
}