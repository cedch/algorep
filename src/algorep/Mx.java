package algorep;

import java.util.ArrayList;

public class Mx implements Runnable {

	private ArrayList<Line> view = new ArrayList<Line>();
	private static int timer = 0;

	public synchronized ArrayList<Line> getView() {
		return view;
	}

	public synchronized void setView(ArrayList<Line> vue) {
		this.view = vue;
	}

	public synchronized static int getTimer() {
		return timer;
	}

	public synchronized void setTimer(int timer) {
		Mx.timer = timer;
	}

	public void run() {

		System.out.println("-- Starting the Mutex's thread --- \n  ");

		// Initialize the "view" table.

		view.add(new Line(' ', -1));
		view.add(new Line(' ', -1));
		view.add(new Line(' ', -1));

		while (true) {

		}

	}

	/**
	 * This method is called when the Application's thread send a Request
	 * 
	 * @param siteNb
	 * @param timer
	 */
	public void rSend(int siteNb, int timer) {

		view.set(0, new Line('r', timer));
		this.broadcast('r', timer);
		this.setTimer(Mx.getTimer() + 1);

		while (!checkScAvailable()) {

			// do nothing
		}

	}

	/**
	 * This Method is called when the Network's thread receive an acknowledgment
	 * 
	 * @param siteNb
	 * @param timer
	 */
	public void aReceive(int siteNb, int timer) {
		if (timer > Mx.timer)
			Mx.timer = timer;

		Mx.timer++;
		if (this.view.get(siteNb).message != 'r') {
			this.view.get(siteNb).message = 'a';
			this.view.get(siteNb).timer = timer;
		}

	}

	/**
	 * This method check in the view to see if
	 * 
	 * @return
	 */

	private boolean checkScAvailable() {

		int compteur = 0;
		int timeValue = this.view.get(Main.siteNb).timer;
		for (Line obj : this.view) {

			if ((timeValue > obj.timer) && (Main.siteNb != compteur)) {
				return false;
			}
			compteur++;
		}
		return true;

	}

	/**
	 * This method broadcast a message to all other sites.
	 * 
	 * @param message
	 * @param timer
	 */
	private void broadcast(char message, int timer) {

	}

	/**
	 * This method is called when the Application's thread free the critical
	 * section
	 * 
	 * @param siteNb
	 * @param timer
	 */
	public void lSend(int siteNb, int timer) {

		broadcast('l', Mx.timer);
		Mx.timer++;
		this.view.set(siteNb, new Line('l', timer));

	}

	/**
	 * This method is called when the Network's thread receive a request R
	 * 
	 * @param siteLue
	 * @param timer
	 */
	public void rReceive(int siteLue, int timer) {

		if (timer > Mx.timer)
			Mx.timer = timer;
		Mx.timer++;
		this.aSend(siteLue);
		this.view.set(siteLue, new Line('r', timer));

	}

	/**
	 * This method is called when the Mx send an acknowledgment
	 * 
	 * @param siteLue
	 * @param timer
	 */
	private void aSend(int siteLue) {

		if (this.view.get(siteLue).message != 'r')
			this.view.set(Main.siteNb, new Line('a', Mx.timer));

	}

	/**
	 * This method is called when the network's thread receive a request L
	 * 
	 * @param siteLue
	 * @param timer
	 */
	public void lReceive(int siteLue, int timer) {

		if (timer > Mx.timer)
			Mx.timer = timer;
		Mx.timer++;

		this.view.set(siteLue, new Line('l', timer));

	}

}
