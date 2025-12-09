package numberlettercountfetching;

import java.util.List;

public class StringFetchRequest extends FetchRequest {
	private String text;

	public StringFetchRequest(String text) {
		super(convertStringToNumbers(text));
		this.text = text;
	}

	private static List<Integer> convertStringToNumbers(String text) {
		return text.chars()
				.boxed()
				.collect(java.util.stream.Collectors.toList());
	}

	public String getStringData() {
		return text;
	}
}