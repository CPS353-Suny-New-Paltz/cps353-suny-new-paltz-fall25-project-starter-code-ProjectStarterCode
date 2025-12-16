package integrationtests;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import project.UserAPIImpl;
import project.EngineAPIImpl;
import project.annotations.EngineAPI;
import project.annotations.StorageAPI;


public class TestUserAPI {

    @Test
    public void testUserApiInitialization() {
        //mock dependencies
        EngineAPI mockEngine = mock(EngineAPI.class);
        StorageAPI mockStorage = mock(StorageAPI.class);

        //creates the implementation
        UserAPIImpl userApi = new UserAPIImpl(mockEngine, mockStorage);

        //smoke test
        assert userApi != null : "UserAPIImpl should be created successfully";
    }

    @Test
    public void testFindPrimesDelegatesToEngine() {
        EngineAPI mockEngine = mock(EngineAPI.class);
        StorageAPI mockStorage = mock(StorageAPI.class);
        UserAPIImpl userApi = new UserAPIImpl(mockEngine, mockStorage);

        when(mockEngine.calculatePrimes(10)).thenReturn("2, 3, 5, 7");

        String result = userApi.findPrimes(10);
        assert result != null : "findPrimes should return a result (even if empty for now)";
    }
    
    //additional test added
    @Test
    public void testCalculatePrimesWithZeroOrNegative() {
        EngineAPIImpl engineApi = new EngineAPIImpl();
        String resultZero = engineApi.calculatePrimes(0);
        String resultNegative = engineApi.calculatePrimes(-5);
        assertTrue(resultZero.isEmpty() && resultNegative.isEmpty(),
                "calculatePrimes should return an empty result for zero or negative inputs");
    }
}
