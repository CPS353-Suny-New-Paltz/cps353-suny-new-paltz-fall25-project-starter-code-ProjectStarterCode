package integrationtests;

import org.junit.jupiter.api.Test;
import project.StorageAPIImpl;
import java.nio.file.Path;
import java.util.List;

public class TestStorageAPI {

    @Test
    public void testStorageApiInitialization() {
        StorageAPIImpl storage = new StorageAPIImpl();
        assert storage != null : "StorageAPIImpl should be created successfully";
    }

    @Test
    public void testWriteOutputCreatesFile() throws Exception {
        StorageAPIImpl storage = new StorageAPIImpl();

        Path tempFile = Path.of("test_output.txt");
        boolean result = storage.writeOutput(tempFile, "hello");

        assert result : "writeOutput should return true";
        assert java.nio.file.Files.exists(tempFile) : "output file should be created";

        java.nio.file.Files.deleteIfExists(tempFile);
    }

    @Test
    public void testParseInput() {
        StorageAPIImpl storage = new StorageAPIImpl();
        int parsed = storage.parseInput(List.of(42));
        assert parsed == 42 : "parseInput should return the first integer";
    }
}