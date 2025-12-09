package configuration;

import java.util.ArrayList;
import java.util.List;

public class TestOutputConfiguration {
	private List<String> outputStrings;

	public TestOutputConfiguration() {
		this.outputStrings = new ArrayList<>();
	}

	public List<String> getOutputStrings() {
		return outputStrings;
	}

	public void addOutputString(String result) {
		outputStrings.add(result);
	}

	public void clearOutput() {
		outputStrings.clear();
	}


}
