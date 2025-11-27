package network;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import conceptual.ComputeEngineAPI;
import process.DataValue;
import process.MultiDataValue;
import process.StorageComputeAPI;

public class UserComputeValidationTest {
	
	@Test
	void testConstructorThrowsExceptionOnNullArguments() {
		// all null mock values
		StorageComputeAPI mockStorage = new StorageComputeAPI() {
			@Override 
			public DataValue readInput(String inputSource) { 
				return null; 
				}
			@Override 
			public MultiDataValue readAllInputs(String inputSource) { 
				return null; 
				}
			@Override 
			public boolean writeAllOutputs(String outputDestination, List<Integer> results) { 
				return false; 
				}
			@Override 
			public boolean writeOutput(String filePath, DataValue value) { 
				return false; 
				}
		};
		ComputeEngineAPI mockEngine = (req) -> null;
		
		assertThrows(IllegalArgumentException.class, () -> new UserComputeImpl(null, mockStorage));
		assertThrows(IllegalArgumentException.class, () -> new UserComputeImpl(mockEngine, null));
	}
	
	@Test
	void testSubmitJobRejectsNullRequest() {
		
		StorageComputeAPI mockStorage = new StorageComputeAPI() {
			@Override 
			public DataValue readInput(String inputSource) { 
				return null; 
				}
			@Override 
			public MultiDataValue readAllInputs(String inputSource) { 
				return null; 
				}
			@Override 
			public boolean writeAllOutputs(String outputDestination, List<Integer> results) { 
				return false; 
				}
			@Override 
			public boolean writeOutput(String filePath, DataValue value) { 
				return false; 
				}
		};
		ComputeEngineAPI mockEngine = (req) -> null;
		
		UserComputeImpl impl = new UserComputeImpl(mockEngine, mockStorage);
		JobResponse response = impl.submitJob(null);
		
		assertFalse(response.isSuccess());
		assertTrue(response.getMessage().toLowerCase().contains("invalid job request"));
	}

}
