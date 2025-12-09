package numberlettercountcomputing;

public class SendInfo {
	private String data;
	private String destination;

	public String getData() { 
		return data != null ? data : "no data"; 
	}

	public void setData(String data) { 
		this.data = data; 
	}

	public String getDestination() { 
		return destination != null ? destination : "unknown destination"; 
	}

	public void setDestination(String destination) { 
		this.destination = destination; 
	}

	@Override
	public String toString() {
		return "SendInfo{data='" + getData() + "', destination='" + getDestination() + "'}";
	}
}