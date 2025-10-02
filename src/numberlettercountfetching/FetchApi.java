package numberlettercountfetching;


import project.annotations.NetworkAPI;

@NetworkAPI
public interface FetchApi {
	
	int insertRequest(FetchRequest fetchRequest);
	
	InputSource getInputSource();
	
	OutputResult getOutputResult();
	
	Delimiters getDelimiters();
	
	PassData inputSource();
	
	Display displayIt();
}