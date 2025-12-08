package integrationtests;
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import implementations.ComputeImplement;
import implementations.UserImplement;

public class UserEngineIntegrationTest {

    @Test
    public void testUserAndEngineInteraction() {
        ComputeImplement engineApi = new ComputeImplement();
        InMemoryStorageAPI storageApi = new InMemoryStorageAPI();
        UserImplement userApi  = new UserImplement(engineApi, storageApi);

        String output = engineApi.calculatePrimes(10);
        assertNotNull(output);
        assertEquals("2, 3, 5, 7", output);
    }

    @Test
    public void testEngineComputeIntegration() {
        ComputeImplement engineApi = new ComputeImplement();
        InMemoryStorageAPI storageApi = new InMemoryStorageAPI();
        UserImplement userApi = new UserImplement(engineApi, storageApi);

        String result = engineApi.compute(5);
        assertNotNull(result);
    }
}