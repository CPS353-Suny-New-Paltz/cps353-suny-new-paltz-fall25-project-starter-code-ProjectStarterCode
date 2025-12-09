package numberlettercountdatastoring;

public class RecieveInfo {

	private String data;
	private String source;
	private String receiveMethod;

	public RecieveInfo() {
		this.data = "";
		this.source = "default";
		this.receiveMethod = "file";
	}

	public RecieveInfo(String data, String source, String receiveMethod) {
		this.data = data;
		this.source = source;
		this.receiveMethod = receiveMethod;
	}


	public String getData() { 
		return data; 
	}
	public void setData(String data) { 
		this.data = data; 
	}

	public String getSource() { 
		return source; 
	}
	public void setSource(String source) {
		this.source = source; 
	}

	public String getReceiveMethod() { 
		return receiveMethod; 
	}
	public void setReceiveMethod(String receiveMethod) { 
		this.receiveMethod = receiveMethod; 
	}

	@Override
	public String toString() {
		return "RecieveInfo{data='" + data + "', source='" + source + "', method='" + receiveMethod + "'}";
	}

}
