public class CylinderSphereClient
{
	public static void main(String[] args)
	{
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");	
		System.out.println("1. Create objects of both classes and output.");
		Cylinder cylinder1 = new Cylinder(80, 3);
		Sphere sphere1 = new Sphere(9);
		System.out.println("Cylinder object");
		System.out.println(cylinder1);
		System.out.println("Sphere object");
		System.out.println(sphere1);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");	
	
		System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------");		
		System.out.println("2. Assign each object to a reference of the abstract class and test both abstract and non-abstract methods.");
		CircleVolume circleVolume1;
		circleVolume1 = cylinder1;
		System.out.println("cylinder1 assigned to circleVolume1");
		System.out.println("\tCalling non-abstract method area(): " + circleVolume1.area());
		System.out.println("\tCalling non-abstract method circumference(): " + circleVolume1.circumference());
		System.out.println("\tCalling abstract method volume(): " + circleVolume1.volume());
		circleVolume1 = sphere1;
		System.out.println("sphere1 assigned to circleVolume1");
		System.out.println("\tCalling non-abstract method area(): " + circleVolume1.area());
		System.out.println("\tCalling non-abstract method circumference(): " + circleVolume1.circumference());
		System.out.println("\tCalling abstract method volume(): " + circleVolume1.volume());
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
		
		System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("3. Create an array of the abstract class of size 4, and insert 2 objects of Sphere and Cylinder class each.\n4. Demonstrate polymorphism by printing out the objects in the array.");
		CircleVolume[] circleVolumeArray = new CircleVolume[4];
		Sphere sphere2 = new Sphere(20);
		circleVolumeArray[0] = sphere2;
		Sphere sphere3 = new Sphere(25);
		circleVolumeArray[1] = sphere3;
		Cylinder cylinder2 = new Cylinder(3, 4);
		circleVolumeArray[2] = cylinder2;
		Cylinder cylinder3 = new Cylinder(4, 3);
		circleVolumeArray[3] = cylinder3;
		for (int i = 0; i < circleVolumeArray.length; i++)
		{
			System.out.println("circleVolumeArray[" + i + "]:");
			Object printObject = circleVolumeArray[i];
			CircleVolume printObject1 = (CircleVolume)printObject;
			
			Cylinder cylinder4 = null;
			Sphere sphere4 = null;
			if (printObject instanceof Cylinder)
			{
				cylinder4 = (Cylinder)printObject;
				System.out.println(cylinder4.toString());
			}
			else if (printObject instanceof Sphere)
			{
				sphere4 = (Sphere)printObject;
				System.out.println(sphere4.toString());
			}
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
	}
}