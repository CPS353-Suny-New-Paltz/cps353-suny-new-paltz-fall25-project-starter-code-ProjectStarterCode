package computing;

import org.junit.jupiter.api.Test;

import numberlettercountcomputing.ComputingApi;
import numberlettercountcomputing.ComputingApiImpl;
import numberlettercountcomputing.PassData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class TestComputingApi {

	@Test
	public void testPassDataWithNumber() {
		ComputingApi computingApi = new ComputingApiImpl();

		PassData passData = computingApi.passData(5);
		assertNotNull(passData);
		assertEquals("five", passData.getData());
	}

	@Test
	public void testPassDataWithNumberOutOfRange() {
		ComputingApi computingApi = new ComputingApiImpl();

		PassData passData = computingApi.passData(15);
		assertNotNull(passData);
		assertTrue(passData.getData().contains("fifteen"));
	}

	@Test
	public void testPassDataWithLargeNumber() {
		ComputingApi computingApi = new ComputingApiImpl();

		PassData passData = computingApi.passData(134);
		assertNotNull(passData);
		System.out.println("134 in words: " + passData.getData());

		List<Integer> results = computingApi.processPassData(passData);
		assertNotNull(results);
		assertTrue(results.size() >= 1);

		// Verify it returns a reasonable number
		int letterCount = results.get(0);
		System.out.println("134 letter count: " + letterCount);
		assertTrue(letterCount > 0);

		// 134 should be 20 letters (ignoring "and", spaces, and hyphens)
		assertEquals(20, letterCount, 
				"134 should have 20 letters (ignoring 'and', spaces, and hyphens), got: " + letterCount);
	}

	@Test
	public void testProcessPassDataLetterCounts() {
		ComputingApi computingApi = new ComputingApiImpl();

		// Test some known values
		testNumberLetterCount(computingApi, 0, 4); // "zero" = 4 letters
		testNumberLetterCount(computingApi, 1, 3); // "one" = 3 letters
		testNumberLetterCount(computingApi, 5, 4); // "five" = 4 letters
		testNumberLetterCount(computingApi, 10, 3); // "ten" = 3 letters
		testNumberLetterCount(computingApi, 21, 9); // "twenty-one" = 9 letters

		// 100 = "one hundred" = 10 letters (one=3 + hundred=7)
		testNumberLetterCount(computingApi, 100, 10);

		// 134 = "one hundred thirty-four" = one(3) + hundred(7) + thirty(6) + four(4) = 20 letters
		// OR "one hundred and thirty-four" = same 20 letters (ignoring "and")
		testNumberLetterCount(computingApi, 134, 20); 
	}

	private void testNumberLetterCount(ComputingApi computingApi, int number, int expectedCount) {
		PassData passData = computingApi.passData(number);
		List<Integer> results = computingApi.processPassData(passData);

		String word = passData.getData();
		int actualCount = results.get(0);

		System.out.println(number + " = '" + word + "' = " + actualCount + " letters");

		if (expectedCount >= 0) {
			assertEquals(expectedCount, actualCount, 
					"Number " + number + " should have " + expectedCount + " letters");
		}
	}

	@Test
	public void testSpecificNumberFormats() {
		ComputingApi computingApi = new ComputingApiImpl();

		// Test that spaces and hyphens are NOT counted in letter count
		PassData passData1 = computingApi.passData(21);
		List<Integer> results1 = computingApi.processPassData(passData1);
		// "twenty-one" has letters: twenty=6, one=3, total=9 (hyphen ignored)
		assertEquals(9, results1.get(0));

		// Test 101 - should have "and"
		PassData passData2 = computingApi.passData(101);
		List<Integer> results2 = computingApi.processPassData(passData2);
		System.out.println("101 = '" + passData2.getData() + "' = " + results2.get(0) + " letters");
		// "one hundred and one" ignoring "and" = one(3) + hundred(7) + one(3) = 13 letters
		assertEquals(13, results2.get(0), 
				"101 should have 13 letters (ignoring 'and'), got: " + results2.get(0));

		// Test that letter counting ignores spaces and hyphens
		// Get the word and manually calculate what the letter count should be
		String word = passData1.getData(); // Should be "twenty-one"
		String lettersOnly = word.replaceAll("[^a-zA-Z]", "");
		int manualLetterCount = lettersOnly.length();

		// Verify the ComputingApi returns the same count we calculated manually
		assertEquals(manualLetterCount, results1.get(0), 
				"ComputingApi should return letter count ignoring spaces/hyphens");

		// Also test with a number that has spaces
		PassData passData3 = computingApi.passData(100);
		String word100 = passData3.getData(); // Should be "one hundred" or similar
		String lettersOnly100 = word100.replaceAll("[^a-zA-Z]", "");
		List<Integer> results3 = computingApi.processPassData(passData3);
		assertEquals(lettersOnly100.length(), results3.get(0),
				"Should ignore spaces when counting letters for 100");
	}

	@Test
	public void testProcessPassData() {
		ComputingApi computingApi = new ComputingApiImpl();

		PassData passData = new PassData();
		passData.setData("test data");
		passData.setFromComponent("source");
		passData.setToComponent("destination");

		List<Integer> result = computingApi.processPassData(passData);
		assertNotNull(result);
		assertEquals(2, result.size()); // Letter count and word length
		assertEquals(8, result.get(0)); // "test data" has 8 letters (space ignored)
	}

	@Test
	public void testProcessPassDataWithNull() {
		ComputingApi computingApi = new ComputingApiImpl();

		List<Integer> result = computingApi.processPassData(null);
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(-1, result.get(0));
	}

	@Test
	public void testNumbersFromTestFile() {
		ComputingApi computingApi = new ComputingApiImpl();

		// Test all numbers from the test input file: 1,15,10,5,2,3,8
		int[] testNumbers = {1, 15, 10, 5, 2, 3, 8};
		int[] expectedMinCounts = {3, 7, 3, 4, 3, 5, 5}; // Minimum expected counts

		for (int i = 0; i < testNumbers.length; i++) {
			PassData passData = computingApi.passData(testNumbers[i]);
			List<Integer> results = computingApi.processPassData(passData);

			int letterCount = results.get(0);
			System.out.println(testNumbers[i] + " = '" + passData.getData() + 
					"' = " + letterCount + " letters");

			assertTrue(letterCount >= expectedMinCounts[i],
					"Number " + testNumbers[i] + " should have at least " + 
							expectedMinCounts[i] + " letters, got: " + letterCount);
		}
	}
}