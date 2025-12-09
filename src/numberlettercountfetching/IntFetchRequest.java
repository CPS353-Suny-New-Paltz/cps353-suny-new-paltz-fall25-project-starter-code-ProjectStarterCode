package numberlettercountfetching;

import java.util.Arrays;

public class IntFetchRequest extends FetchRequest {
	private int data;

	public IntFetchRequest(int number) {
		super(Arrays.asList(number));
		this.data = number;
	}

	public int getIntData() {
		return data;
	}
}