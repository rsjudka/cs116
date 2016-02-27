package Client;

import Client.Services.Classes.MyBills;
import Client.Services.BillsType;

import java.util.*;
import java.io.*;
import java.text.NumberFormat;

public class MyBillsClient
{
	public static void main(String[] args) throws IOException
	{
		MyBills[] attributes = null;
		int counter = 0;
		
		try
		{
			File readFile = new File("expenseReport.txt");
			Scanner lineCounter = new Scanner(readFile);
			while(lineCounter.hasNextLine())
			{
				//finds size of array to save data into
				String readLine;				
				readLine = lineCounter.nextLine();
			
				if(readLine.indexOf("MONTH") == 0)
				{
					counter++;
				}
			}
			
			//saves attributes into array
			attributes = new MyBills[counter];
			BillsType expenseType = null;
			
			//variable arrays to save data into
			String month = " ";
			double[] expenses = null;
			double parseNum = 0;
			int numOfDays = 0;
			
			//needed to read data from file and split
			Scanner saveFile = new Scanner(readFile);
			String splitLine;
			String[] saveData = null;
			for (int i = 0; i < counter; i++)
			{
				splitLine = saveFile.nextLine();
				saveData = splitLine.split(":");
				
				//save month and expesneType data
				month = saveData[1];
				//saving value to the enum
				if (saveData[3].equals("School"))
				{
					expenseType = BillsType.School;
				}
				else if (saveData[3].equals("Restaurant"))
				{
					expenseType = BillsType.Restaurant;
				}
				else if (saveData[3].equals("Clothing"))
				{
					expenseType = BillsType.Clothing;
				}
				else
				{
					expenseType = BillsType.Other;
				}
				
				splitLine = saveFile.nextLine();
				saveData = splitLine.split(",");
				
				//save expenses and amount of expenses
				int numOfExpenses = saveData.length;
				numOfDays = numOfExpenses;
				expenses = new double[numOfExpenses];
				for (int j = 0; j < numOfExpenses; j++)
				{
					parseNum = Double.parseDouble(saveData[j]);
					expenses[j] = parseNum;
					
				}

				MyBills saveAttributes = new MyBills(month, expenseType, expenses, numOfDays);	
				attributes[i] = saveAttributes; 
			} //end of reading data from file

				for (int i = 0; i < counter; i++)
			{
				System.out.println(attributes[i].toString());
			}
			
			System.out.println();
			MyBillsClient expenseSums = new MyBillsClient();
			String[] printSums = new String[expenseSums.totalExpensesPerMonth(attributes).length];
			printSums = expenseSums.totalExpensesPerMonth(attributes);
			for (int i = 0; i < printSums.length; i++)
			{
				System.out.println(printSums[i]);
			}
		}
		catch (IOException ioe)
		{
			System.out.println("Error: File \"expenseReport.txt\" not found");
		}
			
	}
	
	public String[] totalExpensesPerMonth(MyBills[] attributes)
	{
		String[] sums = null;
		String month;
		String[] saveMonth = new String[attributes.length];
		int[] saveMonth2 = new int[(attributes.length) + 1];
		int multMonths = 0;
		
		String previousMonth = attributes[0].getMonth();
		saveMonth[0] = attributes[0].getMonth();
		saveMonth2[0] = multMonths;
		int numOfMonths = 1;
		
		int z;
		for (z = 1; z < attributes.length; z++)
		{
			multMonths++;
			month = attributes[z].getMonth();
			
			if (!(month.equals(previousMonth)))
			{
				saveMonth[z] = month;
				numOfMonths++;
				previousMonth = month;
				saveMonth2[z]++;
			}
			else
			{
				previousMonth = month;
				saveMonth[z] = "null";
				saveMonth2[z] = saveMonth2[z];
			}
				
		}
		
		sums = new String[numOfMonths];
		
		double[] totalExpenses = new double[numOfMonths];
		int x = 0;
		int y = -1;
		int loopCounter = 0;
		
		for (int h = 0; h < numOfMonths; h++)
		{
			do
			{	
				loopCounter++;
				y++;
				for (int j = 0; j < attributes[x].getExpenses().length; j++)
				{
					double[] callExpenses = attributes[x].getExpenses();
					totalExpenses[h] += callExpenses[j];
				}
				x++;
			}
			while ((saveMonth2[y] >= saveMonth2[y+1]) && loopCounter < attributes.length);
		}
		
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		
		//setting month value to each month
		int j = 0;
		int i = 0;
		String cashhhh = currency.format(totalExpenses[i]);
		sums[i] = "The total of all the expenses for the month of " + saveMonth[j] + " is: " + cashhhh;
		j++;
		i++;
		for (i = 1; i < numOfMonths; i++)
		{
			while (saveMonth[j].equals("null"))
			{
				j++;
			}
			if (!(saveMonth[j].equals("null")))
			{
				cashhhh = currency.format(totalExpenses[i]);
				sums[i] = "The total of all the expenses for the month of " + saveMonth[j] + " is: " + cashhhh;
				j++;
			}
		}
		return sums;
	}	
}