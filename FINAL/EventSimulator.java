//find threshhold for individual locations
//find max power in each locations
	//if too much power at one location
		//turn smart appliances to low
		//find most power consuming objects (sorting)
		//turn off highest/lowest first
		//check if wattage is no longer too much
	//if nothing works
		//brown out room and go to the next room

import java.util.*;
import java.io.*;
public class EventSimulator {
	
	int loc = 0;
	String name = "";
	int onWatts = 0;
	float probOn = 0;
	boolean type = true;
	int offWatts = 0;
	
	int smartCounter = 0;
	int nSmartCounter = 0;
	
	float power = 0;
	
	Room room;
	ArrayList<Room> rooms = new ArrayList<Room>();
	ArrayList<Room> tempRooms = new ArrayList<Room>();
	
	public static void main(String[] args) {
		final int ARRIVAL_MEAN=5;
		int simulationLength=0, minute=0, nextArrivalTime, callCount=0; 
		Scanner input = new Scanner(System.in);
	
		while (simulationLength<=0)	{
			System.out.print("How many minutes long is the simulation? "); 

			while (!input.hasNextInt()) {
				input.next();
				System.out.print("Please enter an integer: ");
			}
			simulationLength = input.nextInt();
		}

		nextArrivalTime = minute + getRandInt('E', ARRIVAL_MEAN, 0);
		
		while(minute<=simulationLength) {
			while ((minute == nextArrivalTime) && (minute<=simulationLength)) {
				callCount++;
				System.out.println("Minute:"+minute+" Event#"+callCount);
				nextArrivalTime=minute+getRandInt('E', ARRIVAL_MEAN, 0);
			}
			minute++;
		} 
		System.out.println("Number of events = " + callCount);
		
		EventSimulator client = new EventSimulator();
		client.addFile("output");
		
		Scanner scan = new Scanner(System.in);
		boolean questions = true;
		while (questions)
		{
			System.out.print("Do you wnat to find an appliance? Y/N ");
			char find = scan.next().toLowerCase().charAt(0);
			if (find == 'y')
			{
				System.out.print("What is the appliance name? ");
				String name = scan.nextLine();
				System.out.print("What is the appliance location? ");
				int loc = scan.nextInt();
				client.searchLoc(name, loc);
			}
			
			System.out.print("Do you want to add an appliance? Y/N ");
			find = scan.next().toLowerCase().charAt(0);
			if (find == 'y')
			{
				System.out.print("What is the appliance name? ");
				String name = scan.nextLine();
				System.out.print("What is the appliance location? ");
				int loc = scan.nextInt();
				System.out.print("What is the appliance type? ");
				boolean type = Boolean.parseBoolean(scan.next());
				System.out.print("What is the appliance on wattage? ");
				int watts = scan.nextInt();
				System.out.print("What is the appliance on probability? ");
				float prob = scan.nextFloat();
				System.out.print("What is the appliance off wattage? ");
				int off = scan.nextInt();
				client.addApp(loc, name, type, watts, prob, off);
			}
			
			System.out.print("Do you want to add an appliance file? Y/N ");
			find = scan.next().toLowerCase().charAt(0);
			if (find == 'y')
			{
				System.out.print("What is the appliance file name? ");
				String file = scan.next();
				client.addFile(file);
			}	
			
			System.out.print("Do you want to see all the appliances for a location? Y/N ");
			find = scan.next().toLowerCase().charAt(0);
			if (find == 'y')
			{
				System.out.print("What is the location? ");
				int location = scan.nextInt();
				client.viewLoc(location);
			}
			
			System.out.print("Do you want to see all the appliances for a type? Y/N ");
			find = scan.next().toLowerCase().charAt(0);
			if (find == 'y')
			{
				System.out.print("DO you wan to see the smart appliances? (or else non-smart appliances) Y/N ");
				String userType = scan.next();
				client.viewType(userType);
			}
				
			System.out.print("Ready to move on? Y/N ");
			find = scan.next().toLowerCase().charAt(0);
			if (find == 'y')
			{
				questions = false;
			}
		}
		
		for (int i = 1; i <= callCount; i++)
		{
			System.out.println("Simulation " + i + ":");
			System.out.print("What is the max wattage of the grid? ");
			int wat = scan.nextInt();
			System.out.print("What is the wattage warning level of the grid? ");
			int watWar = scan.nextInt();
			client.powerSimulation(wat, watWar);
			client.rooms = client.tempRooms;
		}
		
		
		

		/*client.sortLocSize();
		for (int i = 0; i < client.rooms.size(); i++)
		{
			System.out.println(client.rooms.get(i).getLoc());
		}
		System.out.println(client.rooms.size());*/
	}
	public static int getRandInt(char type, int x, int y) {
		int num;
		switch (type) { 
		case 'U': case 'u':		// Uniform Distribution
			num = (int)(x + (Math.random()*(y+1-x))); 
			break;
		case 'E': case 'e':		// Exponential Distribution
			num = (int)(-1*x*Math.log(Math.random()));  
			break;	
		case 'N': case 'n':		// Normal Distribution
			num = (int)( x +
                (y * Math.cos(2 * Math.PI * Math.random()) *
                Math.sqrt(-2 * Math.log(Math.random()))));
			break;			
		default:				// Uniform Distribution
			num = (int)(x + (Math.random()*(y+1-x))); 
		}
		return num;
	}
	
	public void addFile(String txtFile)
	{
		//read in appliances created from ApplianceGenerator
		
		txtFile = txtFile + ".txt";
		try
		{
			String readLine;
			String[] saveData = null;
			File readFile = new File(txtFile);
			
			//saves attributes of appliance
			Scanner saveFile = new Scanner(readFile);
			boolean first = true;
			int i = 0;
			while (saveFile.hasNextLine())
			{
				readLine = saveFile.nextLine();
				saveData = readLine.split(",");
				
				this.loc = Integer.parseInt(saveData[0]);
				this.name = saveData[1];
				this.onWatts = Integer.parseInt(saveData[2]);
				this.probOn = Float.parseFloat(saveData[3]);
				this.type = Boolean.parseBoolean(saveData[4]);
				this.offWatts = (int)(Float.parseFloat(saveData[5]) * Integer.parseInt(saveData[2]));
				
				this.room = new Room(this.loc, this.name, this.type, this.onWatts, this.probOn, this.offWatts);
				this.rooms.add(room);
				this.tempRooms.add(room);
				if (!first)
				{
					this.room.equals(this.rooms.get(i++).getLoc());
				}
				first = false;
			}
		}
		catch (IOException ioe)
		{
			System.out.println("The file can not be read");
		}
	}
	
	public void viewLoc(int userLoc)
	{
		for (int i = 0; i < this.rooms.size(); i++)
		{
			if (this.rooms.get(i).getLoc() == userLoc)
			{
				System.out.println(this.rooms.get(i).toString());
			}
		}
	}
	
	public void viewType(String userSmart)
	{
		userSmart = userSmart.toLowerCase();
		if (!userSmart.equals("y"))
		{
			userSmart = "n";
		}
		
		for (int i = 0; i < this.rooms.size(); i++)
		{
			String appType = "n"; 
			if (this.rooms.get(i).getType())
			{
				appType = "y";
			}
			
			if (appType.equals(userSmart))
			{
				System.out.println(this.rooms.get(i).toString());
			}
		}
	}
	
	public void typeCounter()
	{
		for (int i = 0; i < this.rooms.size(); i++)
		{
			if (this.rooms.get(i).getType())
			{
				smartCounter++;
			}
			else
			{
				nSmartCounter++;
			}
		}
	}
	
	public void searchLoc(String userAppliance, int loc)
	{
		boolean foundApp = false;
		for (int i = 0; i < this.rooms.size(); i++)
		{
			if (this.rooms.get(i).getName().equals(userAppliance))
			{
				if (this.rooms.get(i).getLoc() == loc)
				{
					System.out.println(this.rooms.get(i).toString());
					foundApp = true;
				}
			}
		}
		
		if (!foundApp)
		{
			System.out.println("Sorry, no appliance found");
		}
	}
	
	public void addApp(int userLoc, String userName, boolean userType, int userOnWatts, float userProbOn, int userOffWatts)
	{
		this.room = new Room(userLoc, userName, userType, userOnWatts, userProbOn, userOffWatts);
		this.rooms.add(room);
		this.tempRooms.add(room);
	}
	
	public void smartOff()
	{
		for (int i = 0; i < this.rooms.size(); i++)
		{
			if (this.rooms.get(i).getType())
			{
				this.rooms.get(i).setOnWatts(this.rooms.get(i).getOffWatts());
			}
		}
		System.out.println("Turning smart appliances to low power");
	}
	
	public int appsInLoc(int loc)
	{
		int locApps = 0;
		for (int i = this.rooms.size() - 1; i >= 0; i--)
		{
			if (this.rooms.get(i).getLoc() == loc)
			{
				locApps++;
			}
		}
		return locApps;
	}
	
	public void brownOut(float totalWatts, int locApps, int loc)
	{
		//System.out.println("This room has been browned out: loc " + loc);
		float maxWattsInRoom = this.room.threshold(totalWatts, locApps);
		float wattsPerRoom = totalWatts/this.room.getNumOfLocs();
		int i = 0;
		boolean firstLoc = true;
		while (this.rooms.get(i).getLoc() == loc)
		{
			if (firstLoc)
			{
				System.out.println("browning out loc " + loc);
				firstLoc = false;
			}
			this.rooms.get(i).setOnWatts(0);
			i++;
		}
	}
	
	public void sortOnWatts()
	{
		boolean swapped = true;
		Room temp;
		while (swapped)
		{
			swapped = false;
			for (int j = 0; j < this.rooms.size() - 1; j++)
			{
				if (this.rooms.get(j).getOnWatts() < this.rooms.get(j + 1).getOnWatts())
				{
					temp = this.rooms.get(j);
					this.rooms.add(temp);
					this.rooms.remove(j);
					swapped = true;
				}
			}
		}
	}
	
	public void sortLocSize()
	{
		this.sortLoc();
		boolean swapped = true;
		ArrayList<Room> tempRoom = new ArrayList<Room>();
		for (int y = 0; y < this.rooms.size(); y++)
		{
			tempRoom.add(this.rooms.get(y));
		}
		ArrayList<Room> tempLoc = new ArrayList<Room>();
		int loc = this.rooms.get(0).getLoc();
		int nextLoc = loc + 1;
		while (swapped)
		{
			swapped = false;
			int locApps = this.appsInLoc(loc);
			for (int j = 0; j < this.rooms.size() - 1; j++)
			{
				if (this.rooms.get(j).getLoc() == loc)
				{
					tempLoc.add(this.rooms.get(j));
				}
				else
				{
					locApps = this.appsInLoc(loc);
					//System.out.println(loc + " to " + nextLoc);
					//System.out.println(locApps + " vs " + this.appsInLoc(nextLoc));
					if (locApps > this.appsInLoc(nextLoc))
					{
						//System.out.println(locApps + " vs " + this.appsInLoc(nextLoc));
						//System.out.println("sorting");
						for (int i = 0; i < this.rooms.size(); i++)
						{
							if ((this.rooms.get(i).getLoc() == tempLoc.get(0).getLoc()) && (this.rooms.get(i).getName().equals(tempLoc.get(0).getName())))
							{
								this.rooms.add(tempLoc.get(0));
								//System.out.println(tempRoom.get(i).getLoc());
								this.rooms.remove(i);
								swapped = true;
								//System.out.println();
							}
							else
							{
								tempLoc.remove(0);
								tempLoc.add(this.rooms.get(0));
							}
							/*else
							{
								i = this.rooms.size();
							}*/							
						}
					}
					loc++;
					nextLoc++;
					tempLoc.clear();
				}
			}
		}
		System.out.println(tempRoom.size());
		/*this.rooms.clear();
		for (int z = 0; z < tempRoom.size(); z++)
		{
			this.rooms.add(tempRoom.get(z));
		}*/
	}
	
	public void sortLoc()
	{
		boolean swapped = true;
		Room temp;
		while (swapped)
		{
			swapped = false;
			for (int j = 0; j < this.rooms.size() - 1; j++)
			{
				if (this.rooms.get(j).getLoc() > this.rooms.get(j + 1).getLoc())
				{
					temp = this.rooms.get(j);
					this.rooms.add(temp);
					this.rooms.remove(j);
					swapped = true;
				}
			}
		}
	}
	
	public boolean prob(Room checkRoom)
	{
		double rand = Math.random();
		if (rand <= checkRoom.getProbOn())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public float totalPowerReq()
	{
		this.power = 0;
		for (int i = 0; i < this.rooms.size(); i++)
		{
			this.power += this.rooms.get(i).getOnWatts();
		}
		return this.power;
	}

	public void powerSimulation(float totalWatts, float wattWarning)
	{
		for (int i = 0; i < this.rooms.size(); i++)
		{
			if (!prob(this.rooms.get(i)))
			{
				this.rooms.get(i).setOnWatts(this.rooms.get(i).getOffWatts());
			}
		}
		
		
		int i = 0;
		boolean smartOff = false;
		boolean done = true;
		float totalPower = this.totalPowerReq();
		while (done)
		{
			int loc = this.rooms.get(i).getLoc();
			int locApps = this.appsInLoc(loc);	
			
			float maxWatts = this.room.threshold(totalWatts, locApps) * this.room.getNumOfLocs();
		
			if (totalPower <= wattWarning)
			{
				System.out.println("Total Wattage below wattage warning: " + totalPower);
				done = false;
			}
			else
			{
				if (!smartOff)
				{
					this.smartOff();
					smartOff = true;
				}			
				if (totalPower <= wattWarning)
				{
					System.out.println("Total Wattage below wattage warning");
					done = false;
				}
				else 
				{
					this.sortOnWatts();
					this.brownOut(totalWatts, locApps, loc);
					if (totalPower <= wattWarning)
					{
						System.out.println("Total Wattage below wattage warning");
						done = false;
					}
				}
			}
			i++;
			if (i >= this.rooms.size())
			{
				i = 0;
			}
			totalPower = this.totalPowerReq();
		}
	}
}