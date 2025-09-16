package usercomputeapi;

public class ComputeRequest {
//	get input and delimiters for result
	private final DataSource source;
	private final String delimiter;
	
	public ComputeRequest(DataSource source, String delimiter) {
		this.source = source;
		if (delimiter == null || delimiter.isBlank()) {
			this.delimiter = ";";
		} else {
			this.delimiter = delimiter;
		}
	}
	
	public DataSource getSource() {
		return source;
	}
	
	public String getDelimiter() {
		return delimiter;
	}
	

}
