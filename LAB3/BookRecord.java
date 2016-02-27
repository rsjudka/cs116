package library.service.classes;

import library.service.classes.BookGenre;

public class BookRecord
{
	//initializing variables
	private String title;
	private String[] authors;
	private BookGenre genre;
	private String tag;
	private int numOfPages;
	
	private static int id = 10000;
	private int recordID = 10000;
	
	//default constructor
	public BookRecord()
	{
		title = "NULL";
		authors  = null;
		genre = null;
		tag = "00000000";
		numOfPages = 0;
		recordID = id;
		id++;
	}
	
	//non default constructor
	public BookRecord(String startTitle, String[] startAuthors, BookGenre startGenre, String startTag, int startNumOfPages)
	{
		title = startTitle;
		authors = startAuthors;
		genre = startGenre;
		tag = startTag;
		numOfPages = startNumOfPages;
		recordID = id;
		id++;
	}

//********************ACCESOR METHODS********************//	
	public String getTitle()
	{
		return title;
	}
	
	public String[] getAuthors()
	{
		return authors;
	}
	
	public BookGenre getGenre()
	{
		return genre;
	}
	
	public String getTag()
	{
		return tag;
	}
	
	public int getNumOfPages()
	{
		return numOfPages;
	}
	
	public int getID()
	{
		return id;
	}
	
	public int getRecordID()
	{
		return recordID;
	}
	
//********************MUTATOR METHODS********************//
	public void setTitle(String passedTitle)
	{
		title = passedTitle;
	}
	
	public void setAuthors(String[] passedAuthors)
	{
		authors = passedAuthors;
	}
	
	public void setGenre(BookGenre passedGenre)
	{
		genre = passedGenre;
	}
	
	public void setTag(String passedTag)
	{
		tag = passedTag;
	}
	
	public void setNumOfPages(int passedNumOfPages)
	{
		numOfPages = passedNumOfPages;
	}
	
	//********************SUPP METHODS********************//
	public boolean equal(BookRecord passedBook)
	{
		String readAuthors = "";
		for (int i = 0; i < authors.length; i++)
		{
			if (i+1 != authors.length)
			{
				readAuthors += String.valueOf(authors[i]) + ", ";
			}
			else
			{
				readAuthors += String.valueOf(authors[i]);
			}
		}
		
		String readPassedAuthors = "";
		for (int i = 0; i < passedBook.getAuthors().length; i++)
		{
			if (i+1 != passedBook.getAuthors().length)
			{
				readPassedAuthors += String.valueOf(passedBook.getAuthors()[i]) + ", ";
			}
			else
			{
				readPassedAuthors += String.valueOf(passedBook.getAuthors()[i]);
			}
		}
		
		if (title.equals(passedBook.getTitle()) && readAuthors.equals(readPassedAuthors) && genre.equals(passedBook.getGenre()) && tag.equals(passedBook.getTag()) && numOfPages == passedBook.getNumOfPages())
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		String readAuthors = "";
		for (int i = 0; i < authors.length; i++)
		{
			if (i+1 != authors.length)
			{
				readAuthors += String.valueOf(authors[i]) + ", ";
			}
			else
			{
				readAuthors += String.valueOf(authors[i]);
			}
		}
		
		return "Record No: " + recordID +
				"\nTag: " + getTag() +
				"\nTitle: " + getTitle() +
				"\nAuthor(s): " + readAuthors +
				"\nGenre: " + getGenre() +
				"\nNo. of Pages: " + getNumOfPages();
	}
}