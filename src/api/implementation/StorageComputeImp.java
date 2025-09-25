package api.implementation;

import api.StorageComputeAPI;
import api.ComputeEngineAPI; // Dependency from system design

public class StorageComputeImp implements StorageComputeAPI {
    
    private ComputeEngineAPI computeEngine;
    
    public StorageComputeImp(ComputeEngineAPI computeEngine) {
        this.computeEngine = computeEngine;
    }
    
    @Override
    public int[] readNumbers(String source) {
        // return empty array
        return new int[0];
    }
    
    @Override
    public String[] computeFactorial(int[] number) {
        
        if (computeEngine != null) {
            return computeEngine.computeFactorial(number);
        }
        return null; // Failure value if dependency not available
    }
    
    public void writeResult(String destination, int[] results, String delimiter) {
       
    }

	@Override
	public void writeResult(String destination, String[] results, String delimiter) {
		// TODO Auto-generated method stub
		
	}
}