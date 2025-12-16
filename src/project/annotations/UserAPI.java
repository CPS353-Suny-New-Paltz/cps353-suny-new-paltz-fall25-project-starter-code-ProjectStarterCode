package project.annotations;

@NetworkAPI
public interface UserAPI {
	void setInput(String sourceUri);

	void setOutput(String sinkUri);

	void setDelimiters(String pairDelimiter, String kvDelimiter);

	void useDefaultDelimiters();
	
	String run();
}