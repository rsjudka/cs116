public class BusinessLoan extends Loan
{	
	private final double bInterest = 1; 
	
	public BusinessLoan()
	{
		super("None", 0, 0);
		super.setInterest(0 + bInterest);
	}
	
	public BusinessLoan(String startLastName, double startLoanSize, int startTerm, double startInterest)
	{
		super(startLastName, startLoanSize, startTerm);
		super.setInterest(startInterest + bInterest);
	}
}