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

	// Updated intToWordBackToInt method to handle any number
	public int intToWordBackToInt(int number) {
		if (number < 0) {
			return -1; // Error for negative numbers
		}

		// Convert number to words and count letters
		String[] units = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", 
				"sixteen", "seventeen", "eighteen", "nineteen"};
		String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", 
				"sixty", "seventy", "eighty", "ninety"};
		String[] thousands = {"", "thousand", "million", "billion"};

		if (number == 0) {
			return "zero".length(); // 4 letters
		}

		String word = "";
		int num = number;
		int thousandIndex = 0;

		while (num > 0) {
			if (num % 1000 != 0) {
				// Convert hundreds part
				int hundredsPart = num % 1000;
				String partWord = "";

				// Hundreds
				if (hundredsPart >= 100) {
					partWord += units[hundredsPart / 100] + "hundred";
					hundredsPart %= 100;
					if (hundredsPart > 0) {
						partWord += "and";
					}
				}

				// Tens and units
				if (hundredsPart >= 20) {
					partWord += tens[hundredsPart / 10];
					hundredsPart %= 10;
					if (hundredsPart > 0) {
						partWord += units[hundredsPart];
					}
				} else if (hundredsPart >= 10) {
					partWord += teens[hundredsPart - 10];
				} else if (hundredsPart > 0) {
					partWord += units[hundredsPart];
				}

				// Add thousand word if needed
				if (thousandIndex > 0 && !partWord.isEmpty()) {
					partWord += thousands[thousandIndex];
				}

				word = partWord + word;
			}

			num /= 1000;
			thousandIndex++;
		}

		// Count letters (remove any spaces or hyphens from the word)
		return word.replaceAll("[^a-zA-Z]", "").length();
	}

	@Override
	public String toString() {
		return "PassData{data='" + getData() + "', from='" + getFromComponent() + "', to='" + getToComponent() + "'}";
	}
}