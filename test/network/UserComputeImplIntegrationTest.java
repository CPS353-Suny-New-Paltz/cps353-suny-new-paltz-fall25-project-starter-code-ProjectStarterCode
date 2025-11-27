package network;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import conceptual.ComputeEngineImpl;
import integrationinfra.InMemoryDataStore;
import process.StorageComputeAPI;

public class UserComputeImplIntegrationTest {

    @Test
    public void testIntegrationWithTestInfrastructure() {
        ComputeEngineImpl engine = new ComputeEngineImpl();

        MultithreadedNetworkAPI networkAPI = new MultithreadedNetworkAPI();

        StorageComputeAPI testStorage = new InMemoryDataStore();

        UserComputeImpl userCompute = new UserComputeImpl(networkAPI);

        assertNotNull(engine, "Compute engine should be instantiated");
        assertNotNull(networkAPI, "Network API should be instantiated");
        assertNotNull(testStorage, "Test storage should be instantiated");
        assertNotNull(userCompute, "User compute impl should be instantiated");
    }
}
