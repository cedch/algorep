package algorep;

public class Application implements Runnable {

	private EMR mutex;

	public Application(EMR tsec) {

		mutex = tsec;
	}

	public void run() {

		System.out.println("-- Starting App's Thread --- \n  ");

		while (true) {

			// This code is executed before the critical section
			
			System.out.println("[A] Code before the critical section   ");
			System.out.println("[A] Asking for critical section   ");

			// The application send a R request to the Mutex

			mutex.rSend(Launcher.siteNb, EMR.getTimer());

			// IF the mutex allow the application to execute the code inside the
			// critical section.

			System.out.println("[A] Code inside the critical section  ");

			// The application free the critical section

			mutex.lSend(Launcher.siteNb, EMR.getTimer());
			System.out.println("[A] Free the critical section ");

			try {

				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}