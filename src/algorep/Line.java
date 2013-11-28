package algorep;

/**
 * This class represent the structure of a line inside the "view" of the queue
 * 
 * @author seb
 * 
 */
public class Line {

	public Line(char message, int horloge) {
		super();

		this.message = message;
		this.timer = horloge;
	}

	public char message;
	public int timer;

	@Override
	public String toString() {
		return "Line [message=" + message + ", timer=" + timer + "]";
	}

}
