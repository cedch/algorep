/**
 * 
 * 
 */
package algorep;

/**
 * This class is intented to runs all threads
 * 
 * 
 */
public class Launcher {

	public static int siteNb = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EMR mutex = new EMR();

		// Application's thread

		Thread tapp = new Thread(new Application(mutex));
		tapp.start();

		// Mx's Thread

		Thread tsec = new Thread(mutex);
		tsec.start();

		// Network's Thread

		Thread tnet = new Thread(new Network(mutex));
		tnet.start();

	}

}
