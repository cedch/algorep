/**
 * 
 */
package algorep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is intended to simulate the network layer
 */
public class Network implements Runnable {

	private EMR mutex;

	public Network(EMR tmut) {

		mutex = tmut;
	}

	public void run() {

		try {

			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		// This thread simulate the network. It allows the program to send or
		// receive messages from another site.

		System.out.println("-- Starting Network's thread --- \n  ");
		System.out.println(" Other sites simulation");
		System.out
				.println(" What do you want to simulate ?  ? (<Number of the site>,<Message>,<timer>)  \n "
						+ "ex:1,a,4");
		while (true) {

			// The user says that another site sent a message to this one.

			String ligne_lue = null;
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			try {
				ligne_lue = entree.readLine();

				// If the user type "view" , The program display the view that
				// represent the queue of a site

				if (ligne_lue.equals("view")) {
					System.out.println(mutex.getView().toString());
				} 
				else if (!ligne_lue.equals("")) {
					String str[] = ligne_lue.split(",");
					int SiteLue = Integer.parseInt(str[0]);
					char MessageLue = str[1].charAt(0);
					int HorlogeLue = Integer.parseInt(str[2]);

					// The network calls the method of mX depends on what the
					// user simulated.

					switch(MessageLue){
					case Constantes.RECEIVE:
						mutex.rReceive(SiteLue, HorlogeLue);
						break;
					case Constantes.FREE:
						mutex.lReceive(SiteLue, HorlogeLue);
						break;
					case Constantes.ACKNOWLEDGEMENT:
						mutex.aReceive(SiteLue, HorlogeLue);
						break;
					}
				}
			} catch (IOException e) {

				System.err.println("Incorrect format");
			}

		}
	}

}
