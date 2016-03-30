import java.text.*;

public class Cylinder extends CircleVolume
{
	private float height;
	
	public Cylinder()
	{
		super(0);
		this.height = 0;
	}
	
	public Cylinder(double startRadius, float startHeight)
	{
		super(startRadius);
		this.height = startHeight;
	}
	
	public Cylinder(CircleVolume startCircleVolume, float startHeight)
	{
		super(startCircleVolume.getRadius());
		this.height = startHeight;
	}
	
	public float getHeight()
	{
		return this.height;
	}
	
	public void setHeigth(float passedHeight)
	{
		this.height = passedHeight;
	}
	
	public double volume()
	{
		double cylinderVolume = Math.PI * super.getRadius() * super.getRadius() * getHeight();
		return cylinderVolume;
	}
	
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("0.0000");
		String volumeFormat = df.format(volume());
		return super.toString() +
				"\n\tHeight of the cylinder: " + getHeight() +
				"\n\tVolume of the cylinder: " + volumeFormat;
	}
}