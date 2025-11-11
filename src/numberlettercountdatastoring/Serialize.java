package numberlettercountdatastoring;

public class Serialize {

	private String serializationFormat;
	private String data;
	private boolean compressed;

	public Serialize() {
		this.serializationFormat = "json";
		this.data = "";
		this.compressed = false;
	}

	public Serialize(String serializationFormat, String data, boolean compressed) {
		this.serializationFormat = serializationFormat;
		this.data = data;
		this.compressed = compressed;
	}

	// Getters and setters
	public String getSerializationFormat() { 
		return serializationFormat; 
	}
	public void setSerializationFormat(String serializationFormat) {
		this.serializationFormat = serializationFormat; 
	}

	public String getData() { 
		return data; 
	}
	public void setData(String data) {
		this.data = data; 
	}

	public boolean isCompressed() { 
		return compressed;
	}
	public void setCompressed(boolean compressed) {
		this.compressed = compressed; 
	}

	@Override
	public String toString() {
		return "Serialize{format='" + serializationFormat + "', data='" + data + "', compressed=" + compressed + "}";
	}

}
