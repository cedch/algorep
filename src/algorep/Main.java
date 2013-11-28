/**
 * 
 * 
 */
package algorep;

/**
 * This class runs all thread
 * 
 * @author seb
 * 
 */
public class Main {

	public static int siteNb = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Mx mutex = new Mx();

		// Application's thread

		Thread tapp = new Thread(new App(mutex));
		tapp.start();

		// Mx's Thread

		Thread tsec = new Thread(mutex);
		tsec.start();

		// Network's Thread

		Thread tnet = new Thread(new Network(mutex));
		tnet.start();

	}

}
