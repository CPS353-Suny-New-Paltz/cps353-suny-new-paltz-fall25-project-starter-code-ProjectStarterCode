package configuration;

import java.util.List;
import java.util.ArrayList;

public class TestInputConfiguration {
	private List<Integer> inputNumbers;

	public TestInputConfiguration() {
		this.inputNumbers = new ArrayList<>();
	}

	public TestInputConfiguration(List<Integer> numbers) {
		this.inputNumbers = new ArrayList<>(numbers);
	}

	public List<Integer> getInputNumbers() {
		return inputNumbers;
	}

	public void setInputNumbers(List<Integer> numbers) {
		this.inputNumbers = new ArrayList<>(numbers);
	}

}
