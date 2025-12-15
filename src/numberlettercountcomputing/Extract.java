package numberlettercountcomputing;

public class Extract {
	private String source;
	private String extractedData;
	private String extractionMethod;

	// Getters and setters
	public String getSource() { 
		return source != null ? source : "default source"; 
	}

	public void setSource(String source) { 
		this.source = source; 
	}

	public String getExtractedData() { 
		return extractedData != null ? extractedData : "no data extracted"; 
	}

	public void setExtractedData(String extractedData) { 
		this.extractedData = extractedData; 
	}

	public String getExtractionMethod() { 
		return extractionMethod != null ? extractionMethod : "default method"; 
	}

	public void setExtractionMethod(String extractionMethod) { 
		this.extractionMethod = extractionMethod; 
	}

	@Override
	public String toString() {
		return "Extract{source='" + getSource() + "', data='" + getExtractedData() + "', method='" + getExtractionMethod() + "'}";
	}

}
