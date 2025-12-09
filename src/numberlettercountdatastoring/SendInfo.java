package numberlettercountdatastoring;

public class SendInfo {

	private String data;
	private String destination;
	private String sendMethod;

	public SendInfo() {
		this.data = "";
		this.destination = "default";
		this.sendMethod = "file";
	}

	public SendInfo(String data, String destination, String sendMethod) {
		this.data = data;
		this.destination = destination;
		this.sendMethod = sendMethod;
	}

	// Getters and setters
	public String getData() {
		return data; 
	}
	public void setData(String data) { 
		this.data = data; 
	}

	public String getDestination() { 
		return destination; 
	}
	public void setDestination(String destination) {
		this.destination = destination; 
	}

	public String getSendMethod() { 
		return sendMethod; 
	}
	public void setSendMethod(String sendMethod) { 
		this.sendMethod = sendMethod; 
	}

	@Override
	public String toString() {
		return "SendInfo{data='" + data + "', destination='" + destination + "', method='" + sendMethod + "'}";
	}


}
