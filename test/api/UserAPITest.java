package api;
import static org.junit.jupiter.api.Assertions.assertTrue; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import implementations.UserImplement;
import processapi.StorageAPI;
import implementations.ComputeImplement;
import conceptualapi.ComputeAPI;


public class UserAPITest {

    @Test
    public void testUserApiInitialization() {
        //mock dependencies
        ComputeAPI mockEngine = mock(ComputeAPI.class);
        StorageAPI mockStorage = mock(StorageAPI.class);

        //creates the implementation
        UserImplement userApi = new UserImplement(mockEngine, mockStorage);

        //smoke test
        assert userApi != null : "UserAPIImpl should be created successfully";
    }

    @Test
    public void testFindPrimesDelegatesToEngine() {
    	ComputeAPI mockEngine = mock(ComputeAPI.class);
        StorageAPI mockStorage = mock(StorageAPI.class);
        UserImplement userApi = new UserImplement(mockEngine, mockStorage);

        when(mockEngine.calculatePrimes(10)).thenReturn("2, 3, 5, 7");

        String result = userApi.findPrimes(10);
        assert result != null : "findPrimes should return a result (even if empty for now)";
    }
    
    //additional test added
    @Test
    public void testCalculatePrimesWithZeroOrNegative() {
        ComputeImplement engineApi = new ComputeImplement();
        String resultZero = engineApi.calculatePrimes(0);
        String resultNegative = engineApi.calculatePrimes(-5);
        assertTrue(resultZero.isEmpty() && resultNegative.isEmpty(),
                "calculatePrimes should return an empty result for zero or negative inputs");
    }
}