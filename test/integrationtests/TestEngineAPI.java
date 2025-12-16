package integrationtests;
import org.junit.jupiter.api.Test;
import project.EngineAPIImpl;


public class TestEngineAPI {

    @Test
    public void testEngineApiInitialization() {
        EngineAPIImpl engineApi = new EngineAPIImpl();
        assert engineApi != null : "EngineAPIImpl should be created successfully";
    }

    @Test
    public void testCalculatePrimesHandlesNegativeInput() {
        EngineAPIImpl engineApi = new EngineAPIImpl();
        String result = engineApi.calculatePrimes(-5);
        assert result != null : "calculatePrimes should not return null for negative input";
    }
}
