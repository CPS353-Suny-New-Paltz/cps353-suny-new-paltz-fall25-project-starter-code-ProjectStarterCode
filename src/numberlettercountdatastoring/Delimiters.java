package numberlettercountdatastoring;

public class Delimiters {
	private String fieldDelimiter;
	private String recordDelimiter;
	private String outputDelimiter;

	public Delimiters() {
		this.fieldDelimiter = ",";
		this.recordDelimiter = "\n";
		this.outputDelimiter = " ";
	}

	public Delimiters(String fieldDelimiter, String recordDelimiter, String outputDelimiter) {
		this.fieldDelimiter = fieldDelimiter;
		this.recordDelimiter = recordDelimiter;
		this.outputDelimiter = outputDelimiter;
	}

	// Getters and setters
	public String getFieldDelimiter() { 
		return fieldDelimiter; 
	}

	public void setFieldDelimiter(String fieldDelimiter) { 
		this.fieldDelimiter = fieldDelimiter; 
	}

	public String getRecordDelimiter() { 
		return recordDelimiter; 
	}
	public void setRecordDelimiter(String recordDelimiter) { 
		this.recordDelimiter = recordDelimiter; 
	}

	public String getOutputDelimiter() {
		return outputDelimiter; 
	}
	public void setOutputDelimiter(String outputDelimiter) { 
		this.outputDelimiter = outputDelimiter;
	}

	@Override
	public String toString() {
		return "Delimiters{field='" + fieldDelimiter + "', record='" + recordDelimiter + "', output='" + outputDelimiter + "'}";
	}
}
