package numberlettercountfetching;

import java.math.BigInteger;
import java.util.List;

public class StringFetchRequest extends FetchRequest {
	private String text;

	public StringFetchRequest(String text) {
		super(convertStringToNumbers(text));
		this.text = text;
	}

	private static List<BigInteger> convertStringToNumbers(String text) {
		return text.chars()
				.mapToObj(ch -> BigInteger.valueOf(ch))
				.collect(java.util.stream.Collectors.toList());
	}

	// Alternative constructor for large numeric strings
	public StringFetchRequest(String numericString, boolean isNumeric) {
		super();
		if (isNumeric) {
			try {
				BigInteger bigNumber = new BigInteger(numericString);
				addNumber(bigNumber);
			} catch (NumberFormatException e) {
				// Handle invalid numeric string
			}
		}
		this.text = numericString;
	}

	public String getStringData() {
		return text;
	}
}