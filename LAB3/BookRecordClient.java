package library.client.classes;

import library.service.classes.BookGenre;
import library.service.classes.BookRecord;

import java.util.*;
import java.io.*;

public class BookRecordClient
{
	final int defaultSize = 5;
	BookRecord[] bookAttributes;
	int numOfRecords = 0;
	
	BookRecordClient()
	{
		this.bookAttributes = new BookRecord[defaultSize];
	}
		
	public static void main(String [] args) throws IOException
	{
		//DoS user input
		String fileInput = args[0];
		int expansionFactor = Integer.parseInt(args[1]); ////amount to increase bookAttributes[]
		
		BookRecordClient saveBookAttributes = new BookRecordClient();
		
		//variables to hold .txt data
		String title;
		BookRecord loadAttributes;
		BookGenre genre = null;
		String[] authors = null;
		String tag;
		int numOfPages;
	
		try
		{
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
				tag = saveData[3];
				numOfPages = Integer.parseInt(saveData[4]);
				
				//saves to array if not duplicate
				loadAttributes = new BookRecord(title, authors, genre, tag, numOfPages);

				//checks to see if array needs to be resized
				if (saveBookAttributes.numOfRecords < saveBookAttributes.bookAttributes.length)
				{
					//notin
				}
				else
				{
					int oldLength = saveBookAttributes.bookAttributes.length; 
					saveBookAttributes.expand(expansionFactor);
					System.out.println("Resized the array from " + oldLength + " to " + saveBookAttributes.bookAttributes.length); 
				}
				
				saveBookAttributes.bookAttributes[saveBookAttributes.numOfRecords] = loadAttributes;
				
				boolean duplicate = false;
				//checks current records against all other records for duplicate
				for (int i = saveBookAttributes.numOfRecords; i >= 0; i--)
				{
					duplicate = false;
					if (loadAttributes.equal(saveBookAttributes.bookAttributes[i]) && i != saveBookAttributes.numOfRecords)
					{
						duplicate = true;
						System.out.println("");
						System.out.println("FOUND DUPLICATE");
						System.out.println("--------------------------------------------------------------------------------------------------------------------");
						System.out.println(loadAttributes.toString());
						System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
						saveBookAttributes.numOfRecords--;
					}
				}
				saveBookAttributes.numOfRecords++;
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
			System.out.println("\tType \"T\" to search for a record with a specific tag");
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
					
					//BookRecordClient sortPageNums = new BookRecordClient();
					BookRecord[] sortPages = new BookRecord[saveBookAttributes.numOfRecords];
					sortPages = saveBookAttributes.sortPages(saveBookAttributes.bookAttributes, saveBookAttributes.numOfRecords);
					for (int i = 0; i < saveBookAttributes.numOfRecords; i++)
					{
						if (sortPages[i].getGenre().equals(userGenre))
						{
							System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
							System.out.println(sortPages[i]);
							System.out.println("--------------------------------------------------------------------------------------------------------------------");
						}
					}
					break;
				case 'P': 
					for (int i = 0; i < saveBookAttributes.numOfRecords; i++)
					{
						//BookRecordClient sortAndSearchTags = new BookRecordClient();
						BookRecord[] sortTags = new BookRecord[saveBookAttributes.numOfRecords];
						sortTags = saveBookAttributes.sortString(saveBookAttributes.bookAttributes, saveBookAttributes.numOfRecords);
					
						System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
						System.out.println(sortTags[i]);
						System.out.println("--------------------------------------------------------------------------------------------------------------------");
					}
					break;
				case 'T':
					System.out.print("Please enter book tag: ");
					String userTag = userInput.next();
					
					//BookRecordClient sortAndSearchTags = new BookRecordClient();
					BookRecord[] sortTags = new BookRecord[saveBookAttributes.numOfRecords];
					sortTags = saveBookAttributes.sortString(saveBookAttributes.bookAttributes, saveBookAttributes.numOfRecords);
					
					saveBookAttributes.searchTag(userTag);
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
	
	public BookRecord[] expand(int expansionNum)
	{
		int newLength = bookAttributes.length + expansionNum;
		BookRecord[] tempArray = new BookRecord[newLength];
		
		for (int i = 0; i < bookAttributes.length; i++)
		{
			tempArray[i] = bookAttributes[i];
		}
		bookAttributes = tempArray;
		
		return bookAttributes;
	}

	public BookRecord[] sortString(BookRecord[] bookAttributes, int numOfRecords)
	{
		boolean swapped = true;
		int i = 0;
		BookRecord temp;
		while (swapped)
		{
			swapped = false;
			i++;
			for (int j = 0; j < numOfRecords - i; j++)
			{
				if (bookAttributes[j].getTag().compareTo(bookAttributes[j + 1].getTag()) > 0)
				{
					temp = bookAttributes[j];
					bookAttributes[j] = bookAttributes[j + 1];
					bookAttributes[j + 1] = temp;
					swapped = true;
				}
			}
		}
		return bookAttributes;
	}
	
	public BookRecord[] sortPages(BookRecord[] bookAttributes, int numOfRecords)
	{
		boolean swapped = true;
		int i = 0;
		BookRecord temp;
		while (swapped)
		{
			swapped = false;
			i++;
			for (int j = 0; j < numOfRecords - i; j++)
			{
				if (bookAttributes[j].getNumOfPages() > bookAttributes[j + 1].getNumOfPages())
				{
					temp = bookAttributes[j];
					bookAttributes[j] = bookAttributes[j + 1];
					bookAttributes[j + 1] = temp;
					swapped = true;
				}
			}
		}
		return bookAttributes;
	}
	
	public void searchTag(String tag)
	{
		int start = 0;
		int end = numOfRecords;
		int middle = 0;
		boolean noRecord = true;
		
		while (start < end)
		{
			middle = (start + end) / 2;
			int x = tag.compareTo(bookAttributes[middle].getTag());
			if (x == 0)
			{
				System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
				System.out.println(bookAttributes[middle]);
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				end = 0;
				noRecord = false;
			}
			if (x < 0)
			{
				end = middle;
			}
			else if (x > 0)
			{
				start = middle + 1;
			}
		}
		
		if (noRecord)
		{
			System.out.println("\nSorry, tag " + tag + " not found in library");
		}
	}
}