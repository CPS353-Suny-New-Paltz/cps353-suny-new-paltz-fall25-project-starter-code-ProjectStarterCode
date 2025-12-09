package numberlettercountdatastoring;

public class ResultOfCountLetter {
	private int letterCount;
	private String originalData;
	private String processedData;

	public ResultOfCountLetter() {
		this.letterCount = 0;
		this.originalData = "";
		this.processedData = "";
	}

	public ResultOfCountLetter(int letterCount, String originalData, String processedData) {
		this.letterCount = letterCount;
		this.originalData = originalData;
		this.processedData = processedData;
	}

	// Getters and setters
	public int getLetterCount() {
		return letterCount; 
	}
	public void setLetterCount(int letterCount) {
		this.letterCount = letterCount; 
	}

	public String getOriginalData() {
		return originalData; 
	}
	public void setOriginalData(String originalData) {
		this.originalData = originalData; 
	}

	public String getProcessedData() {
		return processedData; 
	}
	public void setProcessedData(String processedData) {
		this.processedData = processedData; 
	}

	@Override
	public String toString() {
		return "ResultOfCountLetter{count=" + letterCount + ", original='" + originalData + "', processed='" + processedData + "'}";
	}
}
