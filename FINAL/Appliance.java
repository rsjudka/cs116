public class Appliance extends Power
{
    private int loc;
    private String name;
    private boolean type;
    
    private int appCounter = 0;
    private static int counter = 0;
    
    public Appliance()
    {
        super(0, 0, 0);
        this.loc = 0;
        this.name = "Name";
        this.type = true;
        this.appCounter = ++counter;
    }
    
    public Appliance(int startLoc, String startName, boolean startType, int startOnWatts, float startProbOn, int startOffWatts)
    {
        super(startOnWatts, startProbOn, startOffWatts);
        this.loc = startLoc;
        this.name = startName;
        this.type = startType;
        this.appCounter = ++counter;
    }
    
    public int getLoc()
    {
        return this.loc;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public boolean getType()
    {
        return this.type;
    }
    
    public int getAppCounter()
    {
        return this.appCounter;
    }
    
    public void setLoc(int passedLoc)
    {
        this.loc = passedLoc;
    }
    public void setName(String passedName)
    {
        this.name = passedName;
    }
    
    public void setType(boolean passedType)
    {
        this.type = passedType;
    }
    
    public String toString()
    {
        String type = "";
        if (getType())
        {
            type = "Smart";
        }
        else
        {
            type = "Non-smart";
        }
        
        return "Location: " + getLoc() +
                "\nAppliance: " + getName() +
                "\nType: " + type +
                "\n" + super.toString() + "\n";
    }
    
    public float threshold(float totalPower, int totalApp, int locApp)
    {
        System.out.println("This is threshold");
        return 0;
    }
}