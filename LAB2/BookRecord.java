package library.service.classes;

import library.service.classes.BookGenre;

public class BookRecord
{
	//initializing variables
	private String title;
	private String[] authors;
	private BookGenre genre;
	
	private static int id = 10000;
	private int recordID = 10000;
	
	//default constructor
	public BookRecord()
	{
		title = "NULL";
		authors  = null;
		genre = null;
		recordID = id;
		id++;
	}
	
	//non default constructor
	public BookRecord(String startTitle, String[] startAuthors, BookGenre startGenre)
	{
		title = startTitle;
		authors = startAuthors;
		genre = startGenre;
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
		
		if (title.equals(passedBook.getTitle()) && readAuthors.equals(readPassedAuthors) && genre.equals(passedBook.getGenre()))
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
				"\nTitle: " + getTitle() +
				"\nAuthor(s): " + readAuthors +
				"\nGenre: " + getGenre();
	}
}