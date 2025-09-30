package processapi;

import java.util.List;

import assets.UserInputHandler;

public class ImplementDataStore {
private final InputConfig<Integer> inputConfig;
private final OutputConfig<String> outputConfig;

public ImplementDataStore(InputConfig<Integer> inputConfig, OutputConfig<String> outputConfig) {
	super();
	this.inputConfig = inputConfig;
	this.outputConfig = outputConfig;
}
	
	
	private WriteData writeData() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Integer> input() {
		// TODO Auto-generated method stub
		return inputConfig.getInput();
	}
	public InputConfig<Integer> getInputConfig() {
		return inputConfig;
	}

	public void output(List<String>data){
		outputConfig.writer(data);
	}


	public WriteData writer(List<String> data) {
		// TODO Auto-generated method stub
		return new WriteData();
	}

	public ConvertData read(UserInputHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
