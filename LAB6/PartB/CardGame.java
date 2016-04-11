import java.util.*;

public abstract class CardGame
{
	//constants for:
	//number of suits in a deck
	//number of faces ina deck
	//total number of cards in a deck
	private final int NUM_SUITS = 4;
	private final int NUM_VALUES = 13;
	private final int NUM_DECK = 52;
	
	private ArrayList<Card> deck = new ArrayList<Card>(NUM_DECK);
	private int deals;
	
	//constructor to initialize deck of cards
	//parameter of number of cards to deal
	public CardGame(int startDeals)
	{
		this.deals = startDeals;
		
		//loop to full deck ArrayList with suits
		for (int i = 1; i <= NUM_SUITS; i++)
		{
			String suit = null;
			String value = null;
			switch (i)
			{
				case 1:
					suit = "Clubs";
					break;
				case 2:
					suit = "Diamonds";
					break;
				case 3:
					suit = "Hearts";
					break;
				case 4:
					suit = "Spades";
					break;
				default:
					break;
			}
			
			//loop to fill suit with faces of cards
			for (int j = 1; j <= NUM_VALUES; j++)
			{
				//using ACE value of 1
				if (j == 1)
				{
					value = "Ace";
				}
				else if (j > 1 && j <= 10)
				{
					value = Integer.toString(j);
				}
				else if (j == 11)
				{
					value = "Jack";
				}
				else if (j == 12)
				{
					value = "Queen";
				}
				else if (j == 13)
				{
					value = "King";
				}
				
				Card card = new Card(suit, value);
				this.deck.add(card); //saves card object into the ArrayList
			}
		}
	}
	
	public ArrayList<Card> getDeck()
	{
		return this.deck;
	}
	
	public void setDeck(ArrayList<Card> passedDeck)
	{
		this.deck = passedDeck;
	}
	
	//tests if all 52 possible cards have been saved
	public String toString()
	{
				//System.out.println("Creating deck of cards");
		String hand = "";
		for (int i = 0; i < NUM_VALUES; i++)
		{
			for (int j = 0; j < NUM_DECK; j += 13)
			{
				hand += this.deck.get(i).getValue() + " of " + this.deck.get(j).getSuit() + "\t\t";
			}
			hand += "\n";
		}
		return hand;
	}

	//shuffles deck of cards aloooooot
	//shuffles a clone of the deck to aloow for multiple games to be played in a single application
	public void shuffle(ArrayList<Card> passedDeck, int size)
	{
		System.out.println("Shuffling cards");
		Random rand = new Random();
		
		for (int i = 0; i < 1000; i++)
		{
			int num = rand.nextInt(size);
			Card temp = passedDeck.get(num);
			passedDeck.remove(num);
			passedDeck.add(temp);
		}	
	}
	
	//abstract methods
	public abstract String displayDescription();
	public abstract String deal();
}