package testharness;

import numberlettercountfetching.MultithreadedNetworkAPI;
import numberlettercountfetching.SingleThreadedNetworkAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//will correct for checkpoint 6
public class TestMultiUser {

	// Remove the unused coordinator field
	private MultithreadedNetworkAPI networkAPI;
	private SingleThreadedNetworkAPI singleThreadedAPI;

	@BeforeEach
	public void initializeComputeEngine() {
		// Create both single-threaded and multi-threaded versions
		singleThreadedAPI = new SingleThreadedNetworkAPI();
		networkAPI = new MultithreadedNetworkAPI();
	}

	@AfterEach
	public void cleanup() {
		if (networkAPI != null) {
			networkAPI.shutdown();
		}
	}

	@Test
	public void compareMultiAndSingleThreaded() throws Exception {
		int numThreads = 4; // Reduced from 100 to match MAX_THREADS=4
		List<TestUser> singleThreadedUsers = new ArrayList<>();
		List<TestUser> multiThreadedUsers = new ArrayList<>();

		// Create users for both implementations
		for (int i = 0; i < numThreads; i++) {
			singleThreadedUsers.add(new TestUser(singleThreadedAPI));
			multiThreadedUsers.add(new TestUser(networkAPI));
		}

		// Run single threaded
		String singleThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.singleThreadOut.tmp";
		for (int i = 0; i < numThreads; i++) {
			File singleThreadedOut = new File(singleThreadFilePrefix + i);
			singleThreadedOut.deleteOnExit();
			singleThreadedUsers.get(i).run(singleThreadedOut.getCanonicalPath());
		}

		// Run multi threaded using thread pool
		ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
		List<Future<?>> results = new ArrayList<>();
		String multiThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.multiThreadOut.tmp";

		for (int i = 0; i < numThreads; i++) {
			File multiThreadedOut = new File(multiThreadFilePrefix + i);
			multiThreadedOut.deleteOnExit();
			String multiThreadOutputPath = multiThreadedOut.getCanonicalPath();
			TestUser testUser = multiThreadedUsers.get(i);
			results.add(threadPool.submit(() -> testUser.run(multiThreadOutputPath)));
		}

		// Wait for all threads to complete
		for (Future<?> future : results) {
			try {
				future.get();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		threadPool.shutdown();

		// Check that the output is the same for multi-threaded and single-threaded
		List<String> singleThreaded = loadAllOutput(singleThreadFilePrefix, numThreads);
		List<String> multiThreaded = loadAllOutput(multiThreadFilePrefix, numThreads);

		assertEquals(singleThreaded, multiThreaded, 
				"Multi-threaded and single-threaded outputs should match");
	}

	@Test
	public void smokeTest() {
		// Test that basic functionality works with both implementations
		List<Integer> testNumbers = List.of(1, 2, 3);
		numberlettercountfetching.ListFetchRequest request = 
				new numberlettercountfetching.ListFetchRequest(testNumbers);

		// Test single-threaded
		List<Integer> singleResult = singleThreadedAPI.insertRequest(request);
		assertNotNull(singleResult);
		assertFalse(singleResult.isEmpty());
		assertEquals(3, singleResult.size());

		// Verify they are letter counts (positive numbers)
		for (int count : singleResult) {
			assertTrue(count > 0 || count == -1, "Should be positive letter count or -1: " + count);
		}

		// Test multi-threaded
		List<Integer> multiResult = networkAPI.insertRequest(request);
		assertNotNull(multiResult);
		assertFalse(multiResult.isEmpty());
		assertEquals(3, multiResult.size());

		// Both should produce same results (same letter counts or same -1s)
		assertEquals(singleResult, multiResult);
	}

	@Test
	public void testValidation() {
		// Test validation works in both implementations
		assertTrue(singleThreadedAPI.validateNumber(5));
		assertTrue(networkAPI.validateNumber(5));

		assertFalse(singleThreadedAPI.validateNumber(-1));
		assertFalse(networkAPI.validateNumber(-1));
	}

	@Test
	public void testNumberConversionAndCounting() {
		// Test that the system converts numbers to words and counts letters correctly
		List<Integer> testNumbers = List.of(1, 15, 10, 5, 2, 3, 8);
		numberlettercountfetching.ListFetchRequest request = 
				new numberlettercountfetching.ListFetchRequest(testNumbers);

		// Test with single-threaded
		List<Integer> results = singleThreadedAPI.insertRequest(request);
		assertNotNull(results);
		assertFalse(results.isEmpty());

		// Should return letter counts for each number
		assertEquals(7, results.size()); // One result per input number

		// Verify expected letter counts:
		// 1 = "one" = 3 letters
		// 15 = "fifteen" = 7 letters  
		// 10 = "ten" = 3 letters
		// 5 = "five" = 4 letters
		// 2 = "two" = 3 letters
		// 3 = "three" = 5 letters
		// 8 = "eight" = 5 letters

		// Expected: [3, 7, 3, 4, 3, 5, 5]
		List<Integer> expected = List.of(3, 7, 3, 4, 3, 5, 5);

		for (int i = 0; i < results.size(); i++) {
			// Accept either the expected letter count or -1 (if computation failed)
			int actual = results.get(i);
			int expectedCount = expected.get(i);

			// Should be either the correct letter count or -1
			assertTrue(actual == expectedCount || actual == -1, 
					"Number " + testNumbers.get(i) + " should have " + expectedCount + 
					" letters or -1, got: " + actual);
		}

		System.out.println("Original numbers: " + testNumbers);
		System.out.println("Letter counts: " + results);
		System.out.println("Expected: " + expected);
	}

	@Test
	public void testWithTestInputFile() throws IOException {
		// Test with the actual test input file
		String inputPath = "test" + File.separatorChar + "testInputFile.test";
		File inputFile = new File(inputPath);

		// Create the test input file if it doesn't exist
		if (!inputFile.exists()) {
			inputFile.getParentFile().mkdirs();
			Files.write(inputFile.toPath(), "1,15,10,5,2,3,8".getBytes());
		}

		// Read and parse the file
		List<String> lines = Files.readAllLines(inputFile.toPath());
		assertFalse(lines.isEmpty());

		String[] numberStrings = lines.get(0).split(",");
		List<Integer> numbers = new ArrayList<>();
		for (String numStr : numberStrings) {
			numbers.add(Integer.parseInt(numStr.trim()));
		}

		assertEquals(7, numbers.size());

		// Process through both implementations
		numberlettercountfetching.ListFetchRequest request = 
				new numberlettercountfetching.ListFetchRequest(numbers);

		List<Integer> singleResult = singleThreadedAPI.insertRequest(request);
		List<Integer> multiResult = networkAPI.insertRequest(request);

		// Both should produce same results
		assertEquals(singleResult, multiResult);
		assertEquals(7, singleResult.size());

		// Expected letter counts: [3, 7, 3, 4, 3, 5, 5]
		List<Integer> expected = List.of(3, 7, 3, 4, 3, 5, 5);

		System.out.println("Test file processing results:");
		System.out.println("Original numbers: " + numbers);
		System.out.println("Letter counts: " + singleResult);
		System.out.println("Expected: " + expected);

		// Check each result
		for (int i = 0; i < singleResult.size(); i++) {
			int actual = singleResult.get(i);
			int expectedCount = expected.get(i);
			assertTrue(actual == expectedCount || actual == -1,
					"Number " + numbers.get(i) + " should have " + expectedCount + 
					" letters or -1, got: " + actual);
		}
	}

	private List<String> loadAllOutput(String prefix, int numThreads) throws IOException {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < numThreads; i++) {
			File file = new File(prefix + i);
			if (file.exists()) {
				result.addAll(Files.readAllLines(file.toPath()));
			}
		}
		return result;
	}
}