package algorep;

/**
 * This class is intented to 
 * represent the structure of a line inside the "view" of the queue
 */
public class MyMessage {

	private char message;
	private int clock;
	
	public MyMessage(char pMessage, int pClock) {
		super();

		message = pMessage;
		clock = pClock;
	}
	
	public char getMessage(){
		return message;
	}
	
	public void setMessage(char toChange){
		message = toChange;
	}
	
	public void setClock(int pClock){
		clock = pClock;
	}
	
	public int getClock(){
		return clock;
	}

	@Override
	public String toString() {
		return "Line [message=" + message + ", timer=" + clock + "]";
	}

}
