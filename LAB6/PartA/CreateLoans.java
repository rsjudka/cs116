import java.util.*;
import java.text.*;

public class CreateLoans
{
	public static void main(String[] args)
	{
		Loan[] loanArray = new Loan[5];
		double interest = 0;
		String loanType;
		String lastName;
		double loanSize;
		int term;
		
		Scanner scan = new Scanner(System.in);
		
		boolean userInput = true;
		do
		{
			try
			{
				System.out.print("Enter the current prime interest rate: ");
				interest = scan.nextDouble();
				if (interest < 0)
				{
					System.out.println("Invalid input. Try again.\n");
				}
				else
				{
					userInput = false;
					DecimalFormat rate = new DecimalFormat("#.0000");
					System.out.println("Prime interest rate set to -> " + rate.format(interest) + "%\n");
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Invalid input. Try again.\n");
				scan.next();
			}
		}
		while (userInput);
		
		for (int i = 0; i < loanArray.length; i++)
		{
			userInput = true;
			while (userInput)
			{
				try
				{
					System.out.print("Enter your loan type (Business or Personal): ");
					loanType = scan.next().toLowerCase();
					System.out.print("Last name: ");
					scan.nextLine(); //skips new line
					lastName = scan.nextLine();
							//System.out.println("Hey " + lastName + "!"); //test case
					System.out.print("Loan amount: ");
					loanSize = scan.nextDouble();
					System.out.print("Term: ");
					term = scan.nextInt();
			
					if (loanType.equals("personal"))
					{
								//System.out.println("Personal loan"); //test case
						PersonalLoan loanP = new PersonalLoan(lastName, loanSize, term, interest);
						if (loanP.getLoanSize() == 0)
						{
							System.out.println("No loan created");
						}
						else
						{
									//System.out.println(loanSize + " loan saved"); //test case
									//System.out.println(loanP.getTerm() + " year term"); //test case
							loanArray[i] = loanP;
							userInput = false;
						}
					}
					else if (loanType.equals("business"))
					{
								//System.out.println("Business loan"); //test case
						BusinessLoan loanB = new BusinessLoan(lastName, loanSize, term, interest);
						if (loanB.getLoanSize() == 0)
						{
							System.out.println("No loan created");
						}
						else
						{
									//System.out.println(loanSize + " loan saved"); //test case
									//System.out.println(loanB.getTerm() + " year term"); //test case
							loanArray[i] = loanB;
							userInput = false;
						}
					}
					else 
					{
						System.out.println("Not a loan type");
					}
					System.out.println();
				}
				catch (InputMismatchException e)
				{
					System.out.println("Wrong information entered\n");
					scan.next();
				}
			}
		}	
		
		for (int j = 0; j < loanArray.length; j++)
		{
			Object loanObj = loanArray[j];
			Loan loan = (Loan)loanObj;
			
			PersonalLoan Ploan = null;
			BusinessLoan Bloan = null;
			if (loanObj instanceof PersonalLoan)
			{
				Ploan = (PersonalLoan)loanObj;
				System.out.println("*PERSONAL LOAN*");
				System.out.println(loanArray[j].toString());
			}
			else if (loanObj instanceof BusinessLoan)
			{
				Bloan = (BusinessLoan)loanObj;
				System.out.println("*BUSINESS LOAN*");
				System.out.println(loanArray[j].toString());
			}
		}
	}
}