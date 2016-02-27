package library;

import library.service.classes.BookGenre;
import library.service.classes.BookRecord;

import java.util.*;
import java.io.*;

public class BookRecordClient
{
	final int defaultSize = 5;
	BookRecord[] bookAttributes = new BookRecord[defaultSize];
	int numOfRecords = 0;
		
	public static void main(String [] args) throws IOException
	{
		BookRecordClient readArray = new BookRecordClient();
		BookRecord[] bookAttributes = readArray.bookAttributes;
		BookRecord[] clientBookAttributes = readArray.bookAttributes;
		int numOfRecords = readArray.numOfRecords;
		
		//DoS user input
		String fileInput = args[0];
		int expansionFactor = Integer.parseInt(args[1]); ////amount to increase bookAttributes[]
		
		//variables to hold .txt data
		String title;
		BookGenre genre = null;
		String[] authors = null;
	
		try
		{
			BookRecord saveAttributes = null;
			
			//read file from .txt
			File readFile = new File(fileInput);
			Scanner saveFile = new Scanner(readFile);
			
			String readLine;
			String[] saveData = null;
			while (saveFile.hasNextLine())
			{	
				readLine = saveFile.nextLine();
				saveData = readLine.split(":");
				
				title = saveData[0];
				genre = BookGenre.valueOf(saveData[1]);
				authors = saveData[2].split(",");
				
				//saves to array if not duplicate
				saveAttributes = new BookRecord(title, authors, genre);
			
				//checks to see if array needs to be resized
				if (numOfRecords < bookAttributes.length)
				{
					//notin
				}
				else
				{
					int oldLength = bookAttributes.length; 
					bookAttributes = readArray.expand(bookAttributes, expansionFactor);
					clientBookAttributes = readArray.expand(bookAttributes, expansionFactor);
					System.out.println("Resized the array from " + oldLength + " to " + bookAttributes.length); 
				}
				
				bookAttributes[numOfRecords] = saveAttributes;
				
				boolean duplicate = false;
				//checks current records against all other records for duplicate
				for (int i = numOfRecords; i >= 0; i--)
				{
					duplicate = false;
					if (saveAttributes.equal(bookAttributes[i]) && i != numOfRecords)
					{
						//System.out.println(saveAttributes.equal(bookAttributes[i]));
						duplicate = true;
						System.out.println("");
						System.out.println("FOUND DUPLICATE");
						System.out.println("----------------------------------------------------------");
						System.out.println(saveAttributes.toString());
						System.out.println("----------------------------------------------------------\n");
						numOfRecords--;
					}
				}
				numOfRecords++;
			}
		}
		catch (IOException ioe)
		{
			System.out.println("The file can not be read");
		}
		
		//user input
		Scanner userInput = new Scanner(System.in);
		
		boolean noQuit = true;
		while (noQuit)
		{
			System.out.println("\nSelect an option:");
			System.out.println("\tType \"S\" to list books of all genre");
			System.out.println("\tType \"P\" to print out all of the book records");
			System.out.println("\tType \"Q\" to quit");
			
			System.out.print("Gimme your option mate: ");
			char userChoice = userInput.next().charAt(0);
			
			switch (userChoice)
			{
				case 'S': 
					System.out.println("The genres are:");
					for (BookGenre d : BookGenre.values())
					{
						System.out.println(d);
					}
					System.out.print("NOW CHOOSE: ");
					String genreOption = userInput.next();
					BookGenre userGenre = BookGenre.valueOf(genreOption);
					for (int i = 0; i < numOfRecords; i++)
					{
						if (bookAttributes[i].getGenre().equals(userGenre))
						{
							System.out.println("\n----------------------------------------------------------");
							System.out.println(bookAttributes[i]);
							System.out.println("----------------------------------------------------------");
						}
					}
					break;
				case 'P': 
					for (int i = 0; i < numOfRecords; i++)
					{
						System.out.println("\n----------------------------------------------------------");
						System.out.println(bookAttributes[i]);
						System.out.println("----------------------------------------------------------");
					}
					break;
				case 'Q':
					System.out.println("Now quiting. Goodbye.");
					noQuit = false;
					break;
				default:
					System.out.println("Uh oh, seems like you don't follow directions too well");
					System.out.println("Try again!!");	
			}
		}
	}
	
	public BookRecord[] expand(BookRecord[] expandArray, int expansionNum)
	{
		int newLength = expandArray.length + expansionNum;
		BookRecord[] tempArray = new BookRecord[newLength];
		
		for (int i = 0; i < expandArray.length; i++)
		{
			tempArray[i] = expandArray[i];
		}
		expandArray = tempArray;
		
		return expandArray;
	}			
}