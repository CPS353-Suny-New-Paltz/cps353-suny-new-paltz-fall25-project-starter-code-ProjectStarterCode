package numberlettercountfetching;


import java.util.List;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface FetchApi {
	
	List<Integer> insertRequest(FetchRequest fetchRequest);
	
	InputSource getInputSource();
	
	OutputResult getOutputResult();
	
	Delimiters getDelimiters();
	
	PassData inputSource();
	
	Display displayIt();
}