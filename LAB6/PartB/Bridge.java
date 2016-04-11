import java.util.*;

public class Bridge extends CardGame
{
	private static final int DEALS = 13;
	
	ArrayList<Card> dealer = super.getDeck(); //clone ArrayList in Bridge to be able to deal multiple hands with same deck
	
	public Bridge()
	{
		super(DEALS);
				//System.out.println("Dealer will deal 13 cards");
	}
	
	public String displayDescription()
	{
				//System.out.println("displayDescription method called");
		return "According to Wikipedia, \"Contract bridge, or simply bridge, is a trick-taking game using a standard 52-card deck.\n" +
				"It is played by four players in two competing partnerships, with partners sitting opposite each other around a table.\n" +
				"Millions of people play bridge worldwide in clubs, tournaments, online and with friends at home, making it one of the \n" +
				"world's most popular card games, particularly among seniors. The World Bridge Federation is the governing body for \n" +
				"international competitive bridge.\"";
	}
	
	public String deal()
	{	
				//System.out.println("deal method called");
		String hand;
		int currSize = this.dealer.size();
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