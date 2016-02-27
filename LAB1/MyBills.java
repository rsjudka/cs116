package Client.Services.Classes;

import Client.Services.BillsType;
import java.util.*;

public class MyBills
{
	private String month;
	private BillsType expenseType; 
	private double[] expenses;
	private int numOfDays;
	
	private static int currentID = 0;
	private int id = 0;
	
	
	public MyBills()
	{
		month = "January";
		expenseType = null;
		expenses = null;
		numOfDays = 0;
		currentID++;
		id = currentID;
	}
	
	public MyBills(String passedMonth, BillsType passedExpenseType, double[] passedExpenses, int passedNumOfDays)
	{
		month = passedMonth;
		expenseType = passedExpenseType;
		numOfDays = passedNumOfDays;
		expenses = passedExpenses;
		currentID++;
		
		id = currentID;
	}
	
	public String getMonth()
	{
		return month;
	}
	
	public BillsType getExpenseType()
	{
		return expenseType;
	}
	
	public double[] getExpenses()
	{
		return expenses;
	}
	
	public int getNumOfDays()
	{
		return numOfDays;
	}
	
	public int getCurrentID()
	{
		return currentID;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setMonth(String passedMonth)
	{
		month = passedMonth;
	}
	
	public void setExpenseType(BillsType passedExpenseType)
	{
		expenseType = passedExpenseType;
	}
	
	public void setExpenses(double[] passedExpenses)
	{
			expenses = passedExpenses;
	}
	
	public void setNumOfDays(int passedNumOfDays)
	{
		numOfDays = passedNumOfDays;
	}
	
	public void setCurrentID(int passedCurrentID)
	{
		currentID = passedCurrentID;
	}
	
	public void setID(int passedID)
	{
		id = passedID;
	}
	
	public boolean equals(MyBills bill)
	{
		boolean equals = false;
		if (expenseType.equals(bill.getExpenseType()))
		{
			if (numOfDays == (bill.getNumOfDays()))
			{
				equals = true;
			}
		}
		
		if (equals)
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		String output;
		String readExpense = "";
		for (int i = 0; i < expenses.length; i++)
		{
			readExpense += " " + String.valueOf(expenses[i]);
		}
		
		return "The month is " + getMonth() + ", " +
				"the expense type is " + getExpenseType() + ", " +
				"the expenses are" + readExpense + ", " +
				"the number of expenses for the month is " + getNumOfDays() + ", " +
				"the expense object id is " + id + ", " +
				"the static id value is " + currentID;
	}
}