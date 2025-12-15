package numberlettercountfetching;

import java.math.BigInteger;
import java.util.Arrays;

public class IntFetchRequest extends FetchRequest {
	private BigInteger data;

	public IntFetchRequest(int number) {
		super(Arrays.asList(BigInteger.valueOf(number)));
		this.data = BigInteger.valueOf(number);
	}

	public IntFetchRequest(BigInteger number) {
		super(Arrays.asList(number));
		this.data = number;
	}

	public IntFetchRequest(String largeNumber) {
		super(Arrays.asList(new BigInteger(largeNumber)));
		this.data = new BigInteger(largeNumber);
	}

	public BigInteger getBigIntegerData() {
		return data;
	}
}