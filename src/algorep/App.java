package algorep;

public class App implements Runnable {

	private Mx mutex;

	public App(Mx tsec) {

		mutex = tsec;
	}

	public void run() {

		System.out.println("-- Starting App's Thread --- \n  ");

		while (true) {

			// This code is executed before the critical section
			
			System.out.println("[A] Code before the critical section   ");
			System.out.println("[A] Asking for critical section   ");

			// The application send a R request to the Mutex

			mutex.rSend(Main.siteNb, Mx.getTimer());

			// IF the mutex allow the application to execute the code inside the
			// critical section.

			System.out.println("[A] Code inside the critical section  ");

			// The application free the critical section

			mutex.lSend(Main.siteNb, Mx.getTimer());
			System.out.println("[A] Free the critical section ");

			try {

				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}