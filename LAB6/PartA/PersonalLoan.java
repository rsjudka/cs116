public class PersonalLoan extends Loan
{	
	private final double pInterest = 2;

	public PersonalLoan()
	{
		super("None", 0, 0);
		super.setInterest(0 + pInterest);
	}
	
	public PersonalLoan(String startLastName, double startLoanSize, int startTerm, double interest)
	{
		super(startLastName, startLoanSize, startTerm);
		super.setInterest(interest + pInterest);
	}
}
	
	