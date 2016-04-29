public class Room extends Appliance
{
    private static int numOfLocs = 1;
    public Room()
    {
        super(0, "Name", true, 0, 0, 0);
    }
    
    public Room(int startLoc, String startName, boolean startType,  int startOnWatts, float startProbOn, int startOffWatts)
    {
        super(startLoc, startName, startType, startOnWatts, startProbOn, startOffWatts);
    }
    
    public Room(Appliance startAppliance)
    {
        super(startAppliance.getLoc(), startAppliance.getName(), startAppliance.getType(), startAppliance.getOnWatts(), startAppliance.getProbOn(), startAppliance.getOffWatts());
    }
    
    public int getNumOfLocs()
    {
        return this.numOfLocs;
    }
    
    public boolean equals(int currentLoc)
    {
        if (getLoc() == currentLoc)
        {
            return true;
        }
        else
        {
            this.numOfLocs++;
            return false;
        }
    }
    
    public float threshold(float totalPower, int locApps)
    {
        float perApp = totalPower/super.getAppCounter();
        float powerInRoom = perApp * locApps;
        return powerInRoom;
    }
}