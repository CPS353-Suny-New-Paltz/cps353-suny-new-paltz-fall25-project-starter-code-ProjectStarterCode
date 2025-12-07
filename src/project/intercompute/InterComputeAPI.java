package project.intercompute; 

import project.annotations.ConceptualAPI; 

@ConceptualAPI 
public interface InterComputeAPI {
    
    int processRequest(InterRequest req);
}