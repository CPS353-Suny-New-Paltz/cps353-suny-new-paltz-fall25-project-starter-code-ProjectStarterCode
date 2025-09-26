package project.intercompute; 

import project.annotations.ConceptualAPI; 

@ConceptualAPI 
public interface InterComputeAPI { 
	
	void processRequest(InterRequest request); 
}