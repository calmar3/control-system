package utils.traffic;


import java.net.URLEncoder;
import java.util.Map;


import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import model.HashMapStreetTraffic;

public class ThreadCallTraffic extends Thread {
	
	private boolean stop = false;
	private int sleepTime=100;

		
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
				
		while(!stop) {
			
			for(Map.Entry<String, Double> tr:  HashMapStreetTraffic.getInstance().entrySet()){
			
				try {
					String url= "http://10.220.167.251:6007/street/"+URLEncoder.encode(tr.getKey());
					HttpResponse<String> response = Unirest.get(url)
							  .header("content-type", "application/json")
							  .asString();
					//System.out.println(response.getBody());
					JSONObject resp = new JSONObject(response.getBody());
					String street = (String) resp.get("street");
					Double traffic = resp.getDouble("traffic");
					tr.setValue(traffic);			
					
				} catch (UnirestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		try {	
			Thread.sleep(sleepTime*10);
		}
		catch(InterruptedException e) {
			Thread.currentThread().interrupt();
			}	
		}

		if(stop){
            // Close the connection between broker and producer
			Thread.currentThread().interrupt();
		}
	}
}
