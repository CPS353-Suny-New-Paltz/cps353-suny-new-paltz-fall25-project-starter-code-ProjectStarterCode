package project.checkpointtests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import project.checkpoint4.CoordinationComponent;

public class ExceptionHandlingIntegrationTest {

    @Test
    public void testExceptionIsCaughtAndConvertedToError() {
        CoordinationComponent coord = new CoordinationComponent();

        Path badInput = Path.of("this_file_does_not_exist.txt");
        Path out = Path.of("output.txt");

        String result = coord.startComputation(badInput, out);

        assertTrue(result.startsWith("ERROR"));
    }
}