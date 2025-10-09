package project.intercompute; 

import java.util.List;

import project.annotations.ConceptualAPI; 

@ConceptualAPI 
public interface InterComputeAPI { 
	
	void processRequest(InterRequest request); 
	
	List<String> computeAll(List<Integer> ns);
}