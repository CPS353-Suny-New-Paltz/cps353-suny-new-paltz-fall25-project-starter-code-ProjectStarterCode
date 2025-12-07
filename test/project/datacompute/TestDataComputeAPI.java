package project.datacompute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestDataComputeAPI {

    private final DataComputeAPI api = new DataComputeAPIImpl();

    @Test
    void writeOutputcreatesCsvFile() throws IOException {
        Path outputFile = Files.createTempFile("data-output-", ".csv");
        outputFile.toFile().deleteOnExit();

        api.writeOutput(List.of(1, 2, 3, 4), outputFile.toString());

        String content = Files.readString(outputFile, StandardCharsets.UTF_8).trim();
        assertEquals("1,2,3,4", content, "Output file should contain comma-separated numbers");
    }

    @Test
    void writeOutputignoresEmpty() throws IOException {
        Path outputFile = Files.createTempFile("data-empty-", ".csv");
        outputFile.toFile().deleteOnExit();

        api.writeOutput(List.of(), outputFile.toString());
        api.writeOutput(null, outputFile.toString());

        String content = Files.readString(outputFile, StandardCharsets.UTF_8);
        assertTrue(content.isEmpty() || content.isBlank(),
            "File should remain empty when no data is provided");
    }

    @Test
    void readInputreturnsEmptyList() {
        assertTrue(api.readInput(null).isEmpty(), "Null path should return empty list");
        assertTrue(api.readInput("  ").isEmpty(), "Blank path should return empty list");
        assertTrue(api.readInput("nonexistent-file.txt").isEmpty(),
            "Missing file should return empty list");
    }

    @Test
    void readInputparsesIntegers() throws IOException {
        Path inputFile = Files.createTempFile("data-input-", ".txt");
        inputFile.toFile().deleteOnExit();
        Files.writeString(inputFile, "10\nabc\n21\n \n-3\nxyz\n", StandardCharsets.UTF_8);

        List<Integer> numbers = api.readInput(inputFile.toString());

        assertEquals(List.of(10, 21, -3), numbers,
            "readInput should parse valid integers and skip invalid lines");
    }
}
