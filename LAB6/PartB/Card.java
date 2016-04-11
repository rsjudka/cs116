public class Card
{
	private String suit;
	private String value;
	
	public Card()
	{
		this.suit = "Null";
		this.value = "Null";
	}
	
	public Card(String startSuit, String startValue)
	{
		this.suit = startSuit;
		this.value = startValue;
	}
	
	public String getSuit()
	{
		return this.suit;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public void setSuit(String passedSuit)
	{
		this.suit = passedSuit;
	}
	
	public void setValue(String passedValue)
	{
		this.value = passedValue;
	}
}