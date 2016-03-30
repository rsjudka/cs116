import java.text.*;

public class Sphere extends Circle
{
	public Sphere()
	{
		super(0);
	}
	
	public Sphere(double startRadius)
	{
		super(startRadius);
	}
	
	public Sphere(Circle startCircle)
	{
		super(startCircle.getRadius());
	}
	
	public double volume()
	{
		double sphereVolume = ((double)4/3) * Math.PI * super.getRadius() * super.getRadius() * super.getRadius();
		return sphereVolume;
	}
	
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("0.0000");
		String volumeFormat = df.format(volume());
		return super.toString() +
				"\n\tVolume of the sphere: " + volumeFormat;
	}
}
	
	
	