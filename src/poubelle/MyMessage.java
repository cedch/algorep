package poubelle;

public class MyMessage {

	private int messageId;
	private int timeStampPerso;
	private String request;
	
	public MyMessage(int pId, int pTime, String pRequest){
		messageId= pId;
		timeStampPerso = pTime;
		request = pRequest;
	}
	
	public int getMessageId(){
		return messageId;
	}
	
	public int getTimeStamp(){
		return timeStampPerso;
	}
	
	public String getRequest(){
		return request;
	}

}
