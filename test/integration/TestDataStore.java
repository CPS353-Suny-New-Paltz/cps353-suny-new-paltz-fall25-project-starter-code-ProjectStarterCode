package integration;
import java.util.List;

import assets.UserInputHandler;
import processapi.ConvertData;
import processapi.ProcessorAPI;
import processapi.WriteData;

public class TestDataStore implements ProcessorAPI {
    private final List<Integer> input;
    private final TestOutputCollector output;

    public TestDataStore(List<Integer> input, TestOutputCollector output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public List<Integer> input() {
        return input;
    }

    @Override
    public WriteData writer(List<String> data) {
        for (String item : data) {
            output.write(item);
        }
        return new WriteData(); // stub
    }

   

	@Override
	public ConvertData read(UserInputHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}
}