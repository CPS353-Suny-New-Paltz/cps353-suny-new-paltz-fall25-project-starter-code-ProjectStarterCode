package numberlettercountcomputing;

public class PassData {
	private String data;
	private String fromComponent;
	private String toComponent;

	// Getters and setters
	public String getData() { 
		return data != null ? data : "no data"; 
	}

	public void setData(String data) { 
		this.data = data; 
	}

	public String getFromComponent() { 
		return fromComponent != null ? fromComponent : "unknown source"; 
	}

	public void setFromComponent(String fromComponent) { 
		this.fromComponent = fromComponent; 
	}

	public String getToComponent() { 
		return toComponent != null ? toComponent : "unknown destination"; 
	}

	public void setToComponent(String toComponent) { 
		this.toComponent = toComponent; 
	}

	// Implementation of the intToWordBackToInt method
	public int intToWordBackToInt(int number) {
		String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

		if (number >= 0 && number <= 9) {
			String word = numberWords[number];
			return word.length(); // Return the count of letters
		} else {
			// For numbers > 9, convert to string and return length
			return String.valueOf(number).length();
		}
	}

	@Override
	public String toString() {
		return "PassData{data='" + getData() + "', from='" + getFromComponent() + "', to='" + getToComponent() + "'}";
	}
}