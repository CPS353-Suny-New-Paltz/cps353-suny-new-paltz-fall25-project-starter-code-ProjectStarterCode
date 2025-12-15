package numberlettercountdatastoring;

public class FormatData {

	private String formatType;
	private Delimiters delimiters;
	private boolean includeSpaces;
	private boolean capitalizeWords;

	public FormatData() {
		this.formatType = "text";
		this.delimiters = new Delimiters();
		this.includeSpaces = true;
		this.capitalizeWords = false;
	}

	public FormatData(String formatType, Delimiters delimiters, boolean includeSpaces, boolean capitalizeWords) {
		this.formatType = formatType;
		this.delimiters = delimiters;
		this.includeSpaces = includeSpaces;
		this.capitalizeWords = capitalizeWords;
	}

	// Getters and setters
	public String getFormatType() { 
		return formatType; 
	}
	public void setFormatType(String formatType) {
		this.formatType = formatType; 
	}

	public Delimiters getDelimiters() {
		return delimiters; 
	}
	public void setDelimiters(Delimiters delimiters) {
		this.delimiters = delimiters; 
	}

	public boolean isIncludeSpaces() {
		return includeSpaces; 
	}
	public void setIncludeSpaces(boolean includeSpaces) { 
		this.includeSpaces = includeSpaces; 
	}

	public boolean isCapitalizeWords() { 
		return capitalizeWords; 
	}
	public void setCapitalizeWords(boolean capitalizeWords) { 
		this.capitalizeWords = capitalizeWords; 
	}

	@Override
	public String toString() {
		return "FormatData{type='" + formatType + "', delimiters=" + delimiters + 
				", spaces=" + includeSpaces + ", capitalize=" + capitalizeWords + "}";
	}

}
