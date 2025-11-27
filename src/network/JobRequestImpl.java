package network;

public class JobRequestImpl implements JobRequest {

	private final String inputSource;
	private final String outputDestination;
	private final String delimiter;
	
	public JobRequestImpl(String inputSource, String outputDestination, String delimiter) {
		this.inputSource = inputSource;
		this.outputDestination = outputDestination;
		this.delimiter = delimiter;
	}

	@Override
	public String getInputSource() {
		return inputSource;
	}

	@Override
	public String getOutputDestination() {
		return outputDestination;
	}

	@Override
	public String getDelimiter() {
		return delimiter;
	}
	
	@Override
	public String toString() {
		return "JobRequestImpl{" +
				"inputSource='" + inputSource + '\'' +
				", outputDestination='" + outputDestination + '\'' +
				", delimiter='" + delimiter + '\'' +
				'}';
	}
}
