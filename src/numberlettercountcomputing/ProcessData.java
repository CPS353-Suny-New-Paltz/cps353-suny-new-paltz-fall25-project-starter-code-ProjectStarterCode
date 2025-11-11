package numberlettercountcomputing;

public class ProcessData {

	private String inputData;
	private String outputData;
	private String processingType;

	// Getters and setters
	public String getInputData() { 
		return inputData != null ? inputData : "no input data"; 
	}

	public void setInputData(String inputData) { 
		this.inputData = inputData; 
	}

	public String getOutputData() { 
		return outputData != null ? outputData : "no output data"; 
	}

	public void setOutputData(String outputData) { 
		this.outputData = outputData; 
	}

	public String getProcessingType() { 
		return processingType != null ? processingType : "default processing"; 
	}

	public void setProcessingType(String processingType) { 
		this.processingType = processingType; 
	}

	@Override
	public String toString() {
		return "ProcessData{input='" + getInputData() + "', output='" + getOutputData() + "', type='" + getProcessingType() + "'}";
	}

}
