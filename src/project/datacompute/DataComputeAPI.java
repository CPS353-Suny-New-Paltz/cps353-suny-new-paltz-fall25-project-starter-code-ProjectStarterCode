package project.datacompute;
import project.annotations.ProcessAPI; 
@ProcessAPI 
public interface DataComputeAPI { 
	void insertRequest(DataRequest dataRequest);
}