// File: DataRequest.java (IMPLEMENTED)
package numberlettercountdatastoring;

public class DataRequest {
	private int requestId;
	private String dataSource;
	private String dataContent;

	public DataRequest(int requestId) {
		this.requestId = requestId;
		this.dataSource = "default";
		this.dataContent = "";
	}

	public DataRequest(int requestId, String dataSource, String dataContent) {
		this.requestId = requestId;
		this.dataSource = dataSource;
		this.dataContent = dataContent;
	}


	public int getRequestId() {
		return requestId; 
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId; 
	}

	public String getDataSource() {
		return dataSource; 
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource; 
	}

	public String getDataContent() { 
		return dataContent; 
	}
	public void setDataContent(String dataContent) {
		this.dataContent = dataContent; 
	}

	@Override
	public String toString() {
		return "DataRequest{id=" + requestId + ", source='" + dataSource + "', content='" + dataContent + "'}";
	}
}