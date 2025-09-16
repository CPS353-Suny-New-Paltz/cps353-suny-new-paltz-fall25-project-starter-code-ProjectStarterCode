package API;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {

	String getInputSource();

	String getOutputDestination();

	String getDelimiters();

	void setInputSource(String inputSource);

	void setOutputDestination(String outputDestination);

	void setDelimiters(String delimiters);

	void useDefaultDelimiters();

}
