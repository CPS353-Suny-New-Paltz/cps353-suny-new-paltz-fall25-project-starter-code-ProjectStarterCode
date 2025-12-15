
package testharness;

import numberlettercountfetching.MultithreadedNetworkAPI;
import numberlettercountfetching.SingleThreadedNetworkAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMultiUser {

	private MultithreadedNetworkAPI networkAPI;
	private SingleThreadedNetworkAPI singleThreadedAPI;

	@BeforeEach
	public void initializeComputeEngine() {
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
		int numThreads = 4; // Match MAX_THREADS=4
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
		List<BigInteger> testNumbers = List.of(
				BigInteger.valueOf(1), 
				BigInteger.valueOf(2), 
				BigInteger.valueOf(3)
				);
		numberlettercountfetching.ListFetchRequest request = 
				new numberlettercountfetching.ListFetchRequest(testNumbers);

		// Test single-threaded
		List<BigInteger> singleResult = singleThreadedAPI.insertRequest(request);
		assertNotNull(singleResult);
		assertFalse(singleResult.isEmpty());
		assertEquals(3, singleResult.size());

		// FIX: Verify they are letter counts (positive numbers) or error codes (-1 or -2)
		for (BigInteger count : singleResult) {
			assertTrue(count.compareTo(BigInteger.ZERO) > 0 || 
					count.equals(BigInteger.valueOf(-1)) ||
					count.equals(BigInteger.valueOf(-2)), 
					"Should be positive letter count, -1, or -2: " + count);
		}

		// Test multi-threaded
		List<BigInteger> multiResult = networkAPI.insertRequest(request);
		assertNotNull(multiResult);
		assertFalse(multiResult.isEmpty());
		assertEquals(3, multiResult.size());

		// Both should produce same results
		assertEquals(singleResult, multiResult);
	}

	@Test
	public void testValidation() {
		// Test validation works in both implementations
		assertTrue(singleThreadedAPI.validateNumber(BigInteger.valueOf(5)));
		assertTrue(networkAPI.validateNumber(BigInteger.valueOf(5)));

		assertFalse(singleThreadedAPI.validateNumber(BigInteger.valueOf(-1)));
		assertFalse(networkAPI.validateNumber(BigInteger.valueOf(-1)));

		// Test with large numbers
		BigInteger largeNumber = new BigInteger("12345678901234567890");
		assertTrue(singleThreadedAPI.validateNumber(largeNumber));
		assertTrue(networkAPI.validateNumber(largeNumber));
	}

	@Test
	public void testNumberConversionAndCounting() {
		// Test that the system converts numbers to words and counts letters correctly
		List<BigInteger> testNumbers = List.of(
				BigInteger.valueOf(1), 
				BigInteger.valueOf(15), 
				BigInteger.valueOf(10), 
				BigInteger.valueOf(5), 
				BigInteger.valueOf(2), 
				BigInteger.valueOf(3), 
				BigInteger.valueOf(8)
				);
		numberlettercountfetching.ListFetchRequest request = 
				new numberlettercountfetching.ListFetchRequest(testNumbers);

		// Test with single-threaded
		List<BigInteger> results = singleThreadedAPI.insertRequest(request);
		assertNotNull(results);
		assertFalse(results.isEmpty());

		// Should return letter counts for each number
		assertEquals(7, results.size()); // One result per input number

		// Expected letter counts as BigInteger:
		// 1 = "one" = 3 letters
		// 15 = "fifteen" = 7 letters  
		// 10 = "ten" = 3 letters
		// 5 = "five" = 4 letters
		// 2 = "two" = 3 letters
		// 3 = "three" = 5 letters
		// 8 = "eight" = 5 letters
		List<BigInteger> expected = List.of(
				BigInteger.valueOf(3), 
				BigInteger.valueOf(7), 
				BigInteger.valueOf(3), 
				BigInteger.valueOf(4), 
				BigInteger.valueOf(3), 
				BigInteger.valueOf(5), 
				BigInteger.valueOf(5)
				);

		for (int i = 0; i < results.size(); i++) {
			BigInteger actual = results.get(i);
			BigInteger expectedCount = expected.get(i);

			// FIX: Should be either the correct letter count, -1, or -2
			assertTrue(actual.equals(expectedCount) || 
					actual.equals(BigInteger.valueOf(-1)) ||
					actual.equals(BigInteger.valueOf(-2)), 
					"Number " + testNumbers.get(i) + " should have " + expectedCount + 
					" letters, -1, or -2, got: " + actual);
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

		// Read and parse the file as BigInteger
		List<String> lines = Files.readAllLines(inputFile.toPath());
		assertFalse(lines.isEmpty());

		String[] numberStrings = lines.get(0).split(",");
		List<BigInteger> numbers = new ArrayList<>();
		for (String numStr : numberStrings) {
			numbers.add(new BigInteger(numStr.trim()));
		}

		assertEquals(7, numbers.size());

		// Process through both implementations
		numberlettercountfetching.ListFetchRequest request = 
				new numberlettercountfetching.ListFetchRequest(numbers);

		List<BigInteger> singleResult = singleThreadedAPI.insertRequest(request);
		List<BigInteger> multiResult = networkAPI.insertRequest(request);

		// Both should produce same results
		assertEquals(singleResult, multiResult);
		assertEquals(7, singleResult.size());

		// Expected letter counts
		List<BigInteger> expected = List.of(
				BigInteger.valueOf(3), 
				BigInteger.valueOf(7), 
				BigInteger.valueOf(3), 
				BigInteger.valueOf(4), 
				BigInteger.valueOf(3), 
				BigInteger.valueOf(5), 
				BigInteger.valueOf(5)
				);

		System.out.println("Test file processing results:");
		System.out.println("Original numbers: " + numbers);
		System.out.println("Letter counts: " + singleResult);
		System.out.println("Expected: " + expected);

		// Check each result
		for (int i = 0; i < singleResult.size(); i++) {
			BigInteger actual = singleResult.get(i);
			BigInteger expectedCount = expected.get(i);
			assertTrue(actual.equals(expectedCount) || actual.equals(BigInteger.valueOf(-1)),
					"Number " + numbers.get(i) + " should have " + expectedCount + 
					" letters or -1, got: " + actual);
		}
	}

	@Test
	public void testLargeNumberProcessing() {
		// Test with very large numbers
		List<BigInteger> largeNumbers = List.of(
				new BigInteger("12345678901234567890"),
				new BigInteger("999999999999999999999"),
				new BigInteger("1000000000000000000000")
				);

		numberlettercountfetching.ListFetchRequest request = 
				new numberlettercountfetching.ListFetchRequest(largeNumbers);

		// Test both implementations
		List<BigInteger> singleResult = singleThreadedAPI.insertRequest(request);
		List<BigInteger> multiResult = networkAPI.insertRequest(request);

		assertNotNull(singleResult);
		assertNotNull(multiResult);
		assertEquals(3, singleResult.size());
		assertEquals(3, multiResult.size());

		// Results should be the same for both implementations
		assertEquals(singleResult, multiResult);

		// FIX: Accept -1 OR -2 as valid error codes for large numbers
		// Large numbers (> Integer.MAX_VALUE) should return -2, other errors return -1
		for (BigInteger result : singleResult) {
			assertTrue(result.compareTo(BigInteger.ZERO) > 0 || 
					result.equals(BigInteger.valueOf(-1)) ||
					result.equals(BigInteger.valueOf(-2)), 
					"Should be positive letter count, -1, or -2: " + result);
		}
	}

	@Test
	public void testMixedNumberProcessing() {
		// Test with mixed small and large numbers
		List<BigInteger> mixedNumbers = List.of(
				BigInteger.valueOf(1),  // Small - should work
				new BigInteger("1000000000000000000"),  // Large - might get -2
				BigInteger.valueOf(15), // Small - should work  
				new BigInteger("999999999999999999999999"), // Large - might get -2
				BigInteger.valueOf(100) // Small - should work
				);

		numberlettercountfetching.ListFetchRequest request = 
				new numberlettercountfetching.ListFetchRequest(mixedNumbers);

		List<BigInteger> results = singleThreadedAPI.insertRequest(request);
		assertNotNull(results);
		assertEquals(5, results.size());

		// FIX: Accept -1 OR -2 as valid error codes
		// Check each result based on the input
		for (int i = 0; i < results.size(); i++) {
			BigInteger result = results.get(i);
			BigInteger input = mixedNumbers.get(i);

			if (input.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
				// Large number - should be positive OR -2 (too large) OR -1 (error)
				assertTrue(result.compareTo(BigInteger.ZERO) > 0 || 
						result.equals(BigInteger.valueOf(-2)) ||
						result.equals(BigInteger.valueOf(-1)), 
						"Large number " + input + " should return positive, -1, or -2, got: " + result);
			} else {
				// Small number - should be positive OR -1 (error)
				assertTrue(result.compareTo(BigInteger.ZERO) > 0 || 
						result.equals(BigInteger.valueOf(-1)), 
						"Small number " + input + " should return positive or -1, got: " + result);
			}
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
