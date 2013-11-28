package algorep;

import java.util.ArrayList;
import java.util.List;

public class EMR implements Runnable {

	private List<MyMessage> view;
	private static int timer = 0;
	
	public EMR(){
		view = new ArrayList<MyMessage>();
	}

	public synchronized ArrayList<MyMessage> getView() {
		return (ArrayList<MyMessage>) view;
	}

	public synchronized void setView(ArrayList<MyMessage> vue) {
		this.view = vue;
	}

	public synchronized static int getTimer() {
		return timer;
	}

	public synchronized void setTimer(int timer) {
		EMR.timer = timer;
	}

	public void run() {

		System.out.println("Mutex is running\n  ");

		// Initialize the "view" table.

		view.add(new MyMessage(' ', -1));
		view.add(new MyMessage(' ', -1));
		view.add(new MyMessage(' ', -1));

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

		view.set(0, new MyMessage(Constantes.RECEIVE, timer));
		this.broadcast('r', timer);
		this.setTimer(EMR.getTimer() + 1);

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
		if (timer > EMR.timer)
			EMR.timer = timer;

		EMR.timer++;
		if (this.view.get(siteNb).getMessage() != Constantes.RECEIVE) {
			this.view.get(siteNb).setMessage(Constantes.ACKNOWLEDGEMENT);
			this.view.get(siteNb).setClock(timer);
		}

	}

	/**
	 * This method check in the view to see if
	 * 
	 * @return
	 */

	private boolean checkScAvailable() {

		int count = 0;
		int timeValue = this.view.get(Launcher.siteNb).getClock();
		for (MyMessage obj : this.view) {

			if ((timeValue > obj.getClock()) && (Launcher.siteNb != count)) {
				return false;
			}
			count++;
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

		broadcast(Constantes.FREE, EMR.timer);
		EMR.timer++;
		this.view.set(siteNb, new MyMessage(Constantes.FREE, timer));

	}

	/**
	 * This method is called when the Network's thread receive a request R
	 * 
	 * @param siteLue
	 * @param timer
	 */
	public void rReceive(int siteLue, int timer) {

		if (timer > EMR.timer)
			EMR.timer = timer;
		EMR.timer++;
		this.aSend(siteLue);
		this.view.set(siteLue, new MyMessage(Constantes.RECEIVE, timer));

	}

	/**
	 * This method is called when the Mx send an acknowledgment
	 * 
	 * @param siteLue
	 * @param timer
	 */
	private void aSend(int siteLue) {

		if (this.view.get(siteLue).getMessage() != Constantes.RECEIVE)
			this.view.set(Launcher.siteNb, new MyMessage(Constantes.ACKNOWLEDGEMENT, EMR.timer));

	}

	/**
	 * This method is called when the network's thread receive a request L
	 * 
	 * @param siteLue
	 * @param timer
	 */
	public void lReceive(int siteLue, int timer) {

		if (timer > EMR.timer)
			EMR.timer = timer;
		EMR.timer++;

		this.view.set(siteLue, new MyMessage(Constantes.FREE, timer));

	}

}
