package numberlettercount.computing;

import java.util.List;


import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputingApi {
	
	int initalize(List<Integer> inputData);
	
	List<Integer> compute();
	
	String writeResult(String result, String delimiters);
	
	public void insertRequest();
	
	Extract extractData();
	
	SendInfo sendInfo();
	
	RecieveInfo recieveInfo();
	
	ProcessData processData() ;
	
	PassData passData() ;
	
}
