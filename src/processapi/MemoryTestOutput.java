package processapi;

import java.util.ArrayList;
import java.util.List;

public class MemoryTestOutput implements OutputConfig {
	private final List<String> output = new ArrayList<>();

	@Override
	public WriteData writer(List data) {
		// TODO Auto-generated method stub
		return new WriteData();
	}

	public void write(String value) {
		output.add(value);
	}

	public List<String> getOutput(){
		return output;
	}

}
