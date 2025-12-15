package numberlettercountfetching;

import java.math.BigInteger;
import java.util.List;

public class StoreFetch {
	private List<BigInteger> storedValues;

	public StoreFetch(List<BigInteger> values) {
		this.storedValues = values;
	}

	public List<BigInteger> getStoredValues() {
		return storedValues;
	}

	public BigInteger getTotal() {
		BigInteger total = BigInteger.ZERO;
		for (BigInteger value : storedValues) {
			total = total.add(value);
		}
		return total;
	}

	public int getCount() {
		return storedValues.size();
	}

	public BigInteger getMax() {
		if (storedValues.isEmpty()) {
			return BigInteger.ZERO;
		}
		BigInteger max = storedValues.get(0);
		for (BigInteger value : storedValues) {
			if (value.compareTo(max) > 0) {
				max = value;
			}
		}
		return max;
	}

	public BigInteger getMin() {
		if (storedValues.isEmpty()) {
			return BigInteger.ZERO;
		}
		BigInteger min = storedValues.get(0);
		for (BigInteger value : storedValues) {
			if (value.compareTo(min) < 0) {
				min = value;
			}
		}
		return min;
	}
}