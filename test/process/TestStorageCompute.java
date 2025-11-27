package process;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStorageCompute {

	@Test
	void testReadWrite(@TempDir File tempDir) throws IOException {
		File inputFile = new File(tempDir, "input.txt");
		try (FileWriter writer = new FileWriter(inputFile)) {
			writer.write("-1");
		}
		
		StorageComputeAPI storage = new StorageComputeImpl();
		
		// Test read
		DataValue input = storage.readInput(inputFile.getAbsolutePath());
		assertNotNull(input);
		assertEquals(-1, input.getValue());
		
		//Test write
		File outputFile = new File(tempDir, "output.txt");
		boolean success = storage.writeOutput(outputFile.getAbsolutePath(), input);
		assertTrue(success);
		
		DataValue output = storage.readInput(outputFile.getAbsolutePath());
		assertEquals(-1, output.getValue());
	}

}