public class CylinderClient
{
	public static void main(String[] args)
	{
		System.out.println("---------------------------------------------------------------------------------------------------------------");	
		System.out.println("1. Create a Circle object circle1. Print out the object.");
		Circle circle1 = new Circle(3);	
		System.out.println(circle1.toString());
		System.out.println("---------------------------------------------------------------------------------------------------------------");	
	
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");		
		System.out.println("2. Create a Cylinder object cylinder1.  Print out the object.");
		Cylinder cylinder1 = new Cylinder(4, 2);
		System.out.println(cylinder1.toString());
		System.out.println("---------------------------------------------------------------------------------------------------------------");	
	
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");				
		System.out.println("3. Create a Cylinder object cylinder2 from the circle object circle. Print out the object.");
		Cylinder cylinder2 = new Cylinder(circle1, 5); 
		System.out.println(cylinder2.toString());
		System.out.println("---------------------------------------------------------------------------------------------------------------");	
	
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");				
		System.out.println("4. Change the radius of circle1.  Print out the object. ");
		circle1.setRadius(10);
		System.out.println(circle1.toString());
		System.out.println("---------------------------------------------------------------------------------------------------------------");	
	
		System.out.println("\n---------------------------------------------------------------------------------------------------------------");				
		System.out.println("5. Call Circle methods on Cylinder1 and print out their outputs.");
		System.out.println("\tArea of cylinder1: " + cylinder1.area());
		System.out.println("\tCircumference of cylinder1: " + cylinder1.circumference());
		System.out.println("---------------------------------------------------------------------------------------------------------------");	

	}
}

		
		