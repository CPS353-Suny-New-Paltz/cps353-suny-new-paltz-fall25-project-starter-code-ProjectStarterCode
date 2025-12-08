package api;

import org.junit.jupiter.api.Test; 

import implementations.StorageImplement;

public class StorageAPITest {

	   @Test
	    public void testStorageApiInitialization() {
	        StorageImplement storageApi = new StorageImplement();
	        assert storageApi != null : "StorageAPIImpl should be created successfully";
	    }

	    @Test
	    public void testSaveDataReturnsFalseByDefault() {
	        StorageImplement storageApi = new StorageImplement();
	        boolean result = storageApi.saveData("test");
	        assert !result : "saveData should return false until implemented";
	    }
	}