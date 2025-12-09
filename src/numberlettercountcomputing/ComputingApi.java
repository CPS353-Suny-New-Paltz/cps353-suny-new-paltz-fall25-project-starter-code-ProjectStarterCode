package numberlettercountcomputing;

import java.util.List;

import project.annotations.ConceptualAPI;


@ConceptualAPI
public interface ComputingApi {
	PassData passData(int number);
	List<Integer> processPassData(PassData passData);  
}