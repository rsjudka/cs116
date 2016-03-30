import java.text.*;

public class Circle
{
	private double radius;
	
	public Circle()
	{
		this.radius = 0;
	}
	
	public Circle(double startRadius)
	{
		this.radius = startRadius;
	}
	
	public double getRadius()
	{
		return this.radius;
	}
	
	public void setRadius(double passedRadius)
	{
		this.radius = passedRadius;
	}
	
	public double area()
	{
		double circleArea = Math.PI * this.radius * this.radius;
		return circleArea;
	}
	
	public double circumference()
	{
		double circleCircumference = 2 * Math.PI * this.radius;
		return circleCircumference;
	}
	
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("0.0000");
		String areaFormat = df.format(area());
		String circumferenceFormat = df.format(circumference());
		return "\tRadius of circle: " + getRadius() +
				"\n\tArea of circle: " + areaFormat +
				"\n\tCircumference of circle: " + circumferenceFormat;
	}
}
		
	