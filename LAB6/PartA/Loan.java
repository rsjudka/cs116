import java.text.*;

public abstract class Loan implements LoanConstants
{
	private final double MAX_LOAN_SIZE = 100000;

	private String lastName;
	private double loanSize;
	private double interest;
	private int term;
	
	private static int idCounter = 1;
	private int id;
		
	public Loan()
	{
		this.lastName = "None";
		this.loanSize = 0;
		this.term = 0;
		this.id = idCounter++;
	}
	
	public Loan(String startLastName, double startLoanSize, int startTerm)
	{
		this.lastName = startLastName;
		
		if (startLoanSize > LoanConstants.MAX || startLoanSize <= 0)
		{
			this.loanSize = 0;
		}
		else
		{
			this.loanSize = startLoanSize;
			this.id = idCounter++;
		}
		
		if (startTerm == LoanConstants.SHORT || startTerm == LoanConstants.MEDIUM || startTerm == LoanConstants.LONG)
		{
			this.term = startTerm;
		}
		else
		{
			this.term = LoanConstants.SHORT;
		}
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public double getLoanSize()
	{
		return this.loanSize;
	}
	
	public double getInterest()
	{
		return this.interest;
	}
	
	public int getTerm()
	{
		return this.term;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public void setLastName(String passedLastName)
	{
		this.lastName = passedLastName;
	}
	
	public void setLoanSize(double passedLoanSize)
	{
		if (passedLoanSize > LoanConstants.MAX || passedLoanSize <= 0)
		{
			this.loanSize = 0;
		}
		else
		{
			this.loanSize = passedLoanSize;
		}
	}
	
	public void setInterest(double passedInterest)
	{
		this.interest = passedInterest;
	}
	
	public void setTerm(int passedTerm)
	{
		if (passedTerm == LoanConstants.SHORT || passedTerm == LoanConstants.MEDIUM || passedTerm == LoanConstants.LONG)
		{
			this.term = passedTerm;
		}
		else
		{
			this.term = LoanConstants.SHORT;
		}
	}
	
	public double loanDue()
	{
		double finalDue = getLoanSize() * (1 + (getInterest()/100) * getTerm());
		return finalDue;
	}	
	
	public String toString()
	{
		DecimalFormat money = new DecimalFormat("#.00");
		DecimalFormat rate = new DecimalFormat("#.0000");
		
		return "Loan number: " + getID() +
				"\nLast name: " + getLastName() +
				"\nLoan amount: $" + money.format(getLoanSize()) +
				"\nInterest rate: " + rate.format(getInterest()) + "%" +
				"\nTerm: " + getTerm() +
				"\nDue at end of loan: $" + money.format(loanDue()) +
				"\n";
	}
		
}