package processapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import assets.UserInputHandler;

public class TestProcessAPI {

    @Test
    public void smokeTest() throws IOException {
        // Direct instantiation for Spoon
        ImplementProcessorAPI directInstance = new ImplementProcessorAPI();

        // Use Mockito to mock UserInputHandler
        try (MockedConstruction<UserInputHandler> mockedHandler =
                Mockito.mockConstruction(UserInputHandler.class, (mock, context) -> {
                    when(mock.promptValueA()).thenReturn(42);
                })) {

            ProcessorAPI realAPI = new ImplementProcessorAPI(); // Spoon sees this constructor
            ProcessorPrototype prototype = new ProcessorPrototype();

            HashMap<Integer, String> result = prototype.processAPI(realAPI);

            assertNotNull(result, "Returned map should not be null");

            // Verify mock behavior
            UserInputHandler handler = mockedHandler.constructed().get(0);
            verify(handler).promptValueA();
        }
    }
}
