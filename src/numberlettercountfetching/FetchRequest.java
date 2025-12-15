package numberlettercountfetching;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FetchRequest {
	private List<BigInteger> data;
	private static final Logger logger = Logger.getLogger(FetchRequest.class.getName());

	public FetchRequest() { 
		this.data = new ArrayList<>();
	}

	public FetchRequest(List<BigInteger> numbers) {
		this.data = numbers != null ? new ArrayList<>(numbers) : new ArrayList<>();
	}

	public List<BigInteger> getData() {
		return new ArrayList<>(this.data);
	}

	public void setData(List<BigInteger> data) {
		this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
	}

	// Helper method to add individual numbers
	public void addNumber(BigInteger number) {
		if (number != null) {
			this.data.add(number);
		}
	}

	// Helper method to add string representation of large numbers
	public void addNumberFromString(String numberStr) {
		try {
			BigInteger number = new BigInteger(numberStr);
			this.data.add(number);
		} catch (NumberFormatException e) {
			logger.warning("Invalid number format: " + numberStr);
		}
	}

	public BigInteger getSum() {
		BigInteger sum = BigInteger.ZERO;
		for (BigInteger num : data) {
			sum = sum.add(num);
		}
		return sum;
	}

	public int size() {
		return data.size();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public String toString() {
		String preview = data.size() <= 5 ? 
				data.toString() : 
					"[" + data.get(0) + ", " + data.get(1) + ", ... (" + data.size() + " items)]";
		return "FetchRequest{data=" + preview + "}";
	}
}