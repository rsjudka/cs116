package library.client.classes;

import library.service.classes.BookGenre;
import library.service.classes.BookRecord;

import java.util.*;
import java.io.*;

public class BookRecordClient
{
	ArrayList<BookRecord> bookAttributes;
	int numOfRecords = 0;
	
	BookRecordClient()
	{
		this.bookAttributes = new ArrayList<BookRecord>();
	}
		
	public static void main(String [] args) throws IOException
	{
		//DoS user input
		String fileInput = args[0];
		
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
				
				//saves to arraylist
				loadAttributes = new BookRecord(title, authors, genre, tag, numOfPages);
				saveBookAttributes.bookAttributes.add(loadAttributes);
				
				boolean duplicate = false;
				int currentIndex = saveBookAttributes.numOfRecords;
				//checks current records against all other records for duplicate
				for (int i = saveBookAttributes.numOfRecords; i >= 0; i--)
				{
					duplicate = false;
					if (loadAttributes.equal(saveBookAttributes.bookAttributes.get(i)) && i != saveBookAttributes.numOfRecords)
					{
						duplicate = true;
						System.out.println("");
						System.out.println("FOUND DUPLICATE");
						System.out.println("--------------------------------------------------------------------------------------------------------------------");
						System.out.println(loadAttributes.toString());
						System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
						saveBookAttributes.numOfRecords--;
						saveBookAttributes.bookAttributes.remove(currentIndex);
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
					
					ArrayList<BookRecord> sortPages = new ArrayList<BookRecord>();
					sortPages = saveBookAttributes.sortPages(saveBookAttributes.bookAttributes);
					for (int i = 0; i < saveBookAttributes.numOfRecords; i++)
					{
						if (sortPages.get(i).getGenre().equals(userGenre))
						{
							System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
							System.out.println(sortPages.get(i));
							System.out.println("--------------------------------------------------------------------------------------------------------------------");
						}
					}
					break;
				case 'P': 
					ArrayList<BookRecord> sortTags = new ArrayList<BookRecord>();
					sortTags = saveBookAttributes.sortString(saveBookAttributes.bookAttributes);
					for (int i = 0; i < saveBookAttributes.numOfRecords; i++)
					{
						System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
						System.out.println(sortTags.get(i));
						System.out.println("--------------------------------------------------------------------------------------------------------------------");
					}
					break;
				case 'T':
					System.out.print("Please enter book tag: ");
					String userTag = userInput.next();
					
					ArrayList<BookRecord> sortTags2 = new ArrayList<BookRecord>(saveBookAttributes.numOfRecords);
					sortTags2 = saveBookAttributes.sortString(saveBookAttributes.bookAttributes);
					
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

	public ArrayList<BookRecord> sortString(ArrayList<BookRecord> bookAttributes)
	{
		boolean swapped = true;
		BookRecord temp;
		while (swapped)
		{
			swapped = false;
			for (int j = 0; j < bookAttributes.size() - 1; j++)
			{
				if (bookAttributes.get(j).getTag().compareTo(bookAttributes.get(j + 1).getTag()) > 0)
				{
					temp = bookAttributes.get(j);
					bookAttributes.add(temp);
					bookAttributes.remove(j);
					swapped = true;
				}
			}
		}
		return bookAttributes;
	}
	
	public ArrayList<BookRecord> sortPages(ArrayList<BookRecord> bookAttributes)
	{
		boolean swapped = true;
		BookRecord temp;
		while (swapped)
		{
			swapped = false;
			for (int j = 0; j < bookAttributes.size() - 1; j++)
			{
				if (bookAttributes.get(j).getNumOfPages() > bookAttributes.get(j + 1).getNumOfPages())
				{
					temp = bookAttributes.get(j);
					bookAttributes.add(temp);
					bookAttributes.remove(j);
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
			int x = tag.compareTo(bookAttributes.get(middle).getTag());
			if (x == 0)
			{
				System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
				System.out.println(bookAttributes.get(middle));
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