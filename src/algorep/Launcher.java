package algorep;

public class Launcher {

	public static int siteJVM = 0;
	
	public static void main(String[] args){
		siteJVM = Integer.parseInt(args[0]);
		System.out.println("The JVM n°: " + siteJVM + " is launched");
	}
	
}
