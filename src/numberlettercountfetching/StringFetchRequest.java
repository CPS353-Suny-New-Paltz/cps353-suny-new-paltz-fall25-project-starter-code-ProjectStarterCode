package numberlettercountfetching;


import java.util.List;

public class StringFetchRequest extends FetchRequest {
	private String text;

	public StringFetchRequest(String text) {
		super(convertStringToNumbers(text)); // Convert string to numbers
		this.text = text;
	}

	private static List<Integer> convertStringToNumbers(String text) {
		// Convert each character to its ASCII value
		return text.chars()
				.boxed()
				.collect(java.util.stream.Collectors.toList());
	}

	public String getStringData() {
		return text;
	}
}