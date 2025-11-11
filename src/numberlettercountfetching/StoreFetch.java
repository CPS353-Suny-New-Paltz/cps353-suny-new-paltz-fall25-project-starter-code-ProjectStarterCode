package numberlettercountfetching;

import java.util.List;

public class StoreFetch {
	private List<Integer> storedValues;

	public StoreFetch(List<Integer> values) {
		this.storedValues = values;
	}

	public List<Integer> getStoredValues() {
		return storedValues;
	}
}