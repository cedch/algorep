package algorep.reseau;

import algorep.MyMessage;

public class ReseauSite extends Thread{

	private int siteId;
	
	public ReseauSite(int pId){
		siteId = pId;
	}
	
	public void run(){
		//seek the medium connection
		
	}
	
	public void sendTo(int recipient, MyMessage message){
		
	}
	
	public int getSiteId(){
		return siteId;
	}
}
