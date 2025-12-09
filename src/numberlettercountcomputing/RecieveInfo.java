package numberlettercountcomputing;

public class RecieveInfo {
	private String data;
	private String source;

	public String getData() { 
		return data != null ? data : "no data"; 
	}

	public void setData(String data) { 
		this.data = data; 
	}

	public String getSource() { 
		return source != null ? source : "unknown source"; 
	}

	public void setSource(String source) { 
		this.source = source; 
	}

	@Override
	public String toString() {
		return "RecieveInfo{data='" + getData() + "', source='" + getSource() + "'}";
	}
}