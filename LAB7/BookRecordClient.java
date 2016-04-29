package library.client.classes;

import library.service.classes.BookGenre;
import library.service.classes.BookRecord;

import java.util.*;
import java.io.*;

public class BookRecordClient
{
	ArrayList<BookRecord> bookAttributes;
	int numOfRecords = 0;
	
	//variables to hold .txt data
	String title;
	BookRecord loadAttributes;
	BookGenre genre = null;
	String[] authors = null;
	String tag;
	int numOfPages;
	
	BookRecordClient()
	{
		this.bookAttributes = new ArrayList<BookRecord>();
	}
		
	public static void main(String [] args)
	{	
		//DoS user input
		String fileInput = "default";
		int fileType = 1;
		try
		{
			fileInput = args[0];
			fileType = Integer.parseInt(args[1]);
		}
		catch ( IllegalArgumentException e)
		{
			System.out.println("\"" + args[1] + "\" is not a type of file extension\nRun the program again and use either \"1\" or \"2\"");
			System.exit(0);
		}
		
		BookRecordClient saveBookAttributes = new BookRecordClient();
		
		if (fileType == 1)
		{
			saveBookAttributes.addTxt(fileInput);
		}
		else if (fileType == 2)
		{
			saveBookAttributes.addObj(fileInput);
		}
		else
		{
			System.out.println("\"" + fileType + "\" is not a file type\nNo file saved into database");
		}
			
		
		//user input
		Scanner userInput = new Scanner(System.in);
		
		boolean noQuit = true;
		while (noQuit)
		{
			System.out.println("\nSelect an option:");
			System.out.println("\tType \"A\" to add books from a .txt file");
			System.out.println("\tType \"B\" to add books from an object file");
			System.out.println("\tType \"S\" to list books of all genre");
			System.out.println("\tType \"P\" to print out all of the book records");
			System.out.println("\tType \"T\" to search for a record with a specific tag");
			System.out.println("\tType \"Q\" to quit");
			
			System.out.print("Gimme your option mate: ");
			char userChoice = userInput.next().charAt(0);
			
			switch (userChoice)
			{
				case 'A':
					System.out.print(".txt file name: ");
					String addTxtFile = userInput.next();
					saveBookAttributes.addTxt(addTxtFile);
					break;
				case 'B':
					System.out.print("object file name: ");
					String addObjFile = userInput.next();
					saveBookAttributes.addObj(addObjFile);
					break;
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
		
		//save data to txt file
		//clears any data in file
		try
		{
			FileOutputStream fos = new FileOutputStream("output.txt", false);
			PrintWriter pw = new PrintWriter(fos);
		}
		catch (FileNotFoundException fnfe)
		{
			System.exit(0);
		}
		
		for (int i = 0; i < saveBookAttributes.bookAttributes.size(); i++)
		{
			saveBookAttributes.bookAttributes.get(i).txtSave(i, saveBookAttributes.bookAttributes.size());
		}
		
		//save objects to object file
		saveBookAttributes.objSave(saveBookAttributes.bookAttributes);
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
	
	public void objSave(ArrayList<BookRecord> bookAttributes)
	{	
		try
		{
			FileOutputStream fos = new FileOutputStream("output.ser", false);
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			
			for (int i = 0; i < bookAttributes.size(); i++)
			{
				oos.writeObject(bookAttributes.get(i));
			}
			oos.close();
			System.out.println("\n\nSaving file \"output.ser\"");
			System.out.println(".\nFile save successful!");
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println("Unable to write to objects");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}		
	}	
	
	public void addTxt(String fileInput)
	{
		fileInput += ".txt";
		System.out.println("\nSaving " + fileInput + " into database..."); 
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
			
				this.title = saveData[0];
				this.genre = BookGenre.valueOf(saveData[1]);
				this.authors = saveData[2].split(",");
				this.tag = saveData[3];
				this.numOfPages = Integer.parseInt(saveData[4]);
			
				//saves to arraylist
				this.loadAttributes = new BookRecord(this.title, this.authors, this.genre, this.tag, this.numOfPages);
				this.bookAttributes.add(this.loadAttributes);
				
				boolean duplicate = false;
				int currentIndex = this.numOfRecords;
				//checks current records against all other records for duplicate
				for (int i = this.numOfRecords; i >= 0; i--)
				{
					duplicate = false;
					if (this.loadAttributes.equal(this.bookAttributes.get(i)) && i != this.numOfRecords)
					{
						duplicate = true;
						System.out.println("");
						System.out.println("FOUND DUPLICATE");
						System.out.println("--------------------------------------------------------------------------------------------------------------------");
						System.out.println(this.loadAttributes.toString());
						System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
						this.numOfRecords--;
						this.bookAttributes.remove(currentIndex);
					}
				}
				this.numOfRecords++;
			}
		}
		catch (IOException ioe)
		{
			System.out.println("Unable to find file\nNo file saved into database");
		}
	}
	
	public void addObj(String fileInput)
	{
		fileInput += ".ser";
		System.out.println("\nSaving " + fileInput + " into database..."); 
		try
		{
			FileInputStream fis = new FileInputStream(fileInput);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			try
			{
				while (true)
				{
					BookRecord loadAttributesObj = null;
					BookRecord temp = (BookRecord)ois.readObject();
					
					loadAttributesObj = temp;
					this.bookAttributes.add(loadAttributesObj);
					BookRecord recordIncr = new BookRecord();
					loadAttributesObj.setRecordID(recordIncr.getRecordID());
				
					boolean duplicate = false;
					int currentIndex = this.numOfRecords;
					//checks current records against all other records for duplicate
					for (int i = this.numOfRecords; i >= 0; i--)
					{
						duplicate = false;
						if (loadAttributesObj.equal(this.bookAttributes.get(i)) && i != this.numOfRecords)
						{
							duplicate = true;
							System.out.println("");
							System.out.println("FOUND DUPLICATE");
							System.out.println("--------------------------------------------------------------------------------------------------------------------");
							System.out.println(loadAttributesObj.toString());
							System.out.println("--------------------------------------------------------------------------------------------------------------------\n");
							this.numOfRecords--;
							this.bookAttributes.remove(currentIndex);
						}
					}
				this.numOfRecords++;
				}
			}
			catch (EOFException eofe)
			{
			}
			catch (ClassNotFoundException cnfe)
			{
				System.out.println(cnfe.getMessage());
			}
			finally
			{
				ois.close();
			}
		}
		catch (FileNotFoundException cnfe)
		{
				System.out.println("Unable to find file\nNo file saved into database");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
} 