public abstract class Power
{
    private int onWatts;
    private float probOn;
    private int offWatts;
    
    public Power()
    {
        this.onWatts = 0;
        this.probOn = 0;
        this.offWatts = 0;
    }
    
    public Power(int startOnWatts, float startProbOn, int startOffWatts)
    {
        this.onWatts = startOnWatts;
        this.probOn = startProbOn;
        this.offWatts = startOffWatts;
    }
    
    public int getOnWatts()
    {
        return this.onWatts;
    }
    
    public float getProbOn()
    {
        return this.probOn;
    }
    
    public int getOffWatts()
    {
        return this.offWatts;
    }
    
    public void setOnWatts(int passedOnWatts)
    {
        this.onWatts = passedOnWatts;
    }
    
    public void setProbOn(float passedProbOn)
    {
        this.probOn = passedProbOn;
    }
    
    public void setOffWatts(int passedOffWatts)
    {
        this.offWatts = passedOffWatts;
    }
    
    public String toString()
    {
        return "Max Watts: " + getOnWatts() +
                "\nOff Watts: " + getOffWatts() +
                "\nOn Probability: " + getProbOn();
    }
    
    public abstract float threshold(float totalPower, int totalApp, int locApp);
   
   //sort by amount of objects in room
   //
   //turn all smart appliances to low power mode
   //
}