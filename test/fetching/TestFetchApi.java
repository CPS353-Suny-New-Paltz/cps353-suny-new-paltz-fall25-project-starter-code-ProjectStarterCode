package fetching;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;


import numberlettercountdatastoring.DataStoreApi;
import numberlettercountfetching.FetchApiImpl;
import numberlettercountfetching.FetchRequest;
import numberlettercountfetching.IntFetchRequest;
import numberlettercountfetching.ListFetchRequest;
import numberlettercountfetching.StringFetchRequest;

public class TestFetchApi {

    @Test
    public void testInsertRequest() {
        // Create implementation
        FetchApiImpl fetchApi = new FetchApiImpl();

        // Test IntFetchRequest
        FetchRequest intRequest = new IntFetchRequest(123);
        List<Integer> intResult = fetchApi.insertRequest(intRequest);
        assertEquals(List.of(123), intResult, "Should return the inserted integer");

        // Test ListFetchRequest
        FetchRequest listRequest = new ListFetchRequest(List.of(1, 2, 3));
        List<Integer> listResult = fetchApi.insertRequest(listRequest);
        assertEquals(List.of(1, 2, 3), listResult, "Should return the inserted list");

        // Test StringFetchRequest
        FetchRequest stringRequest = new StringFetchRequest("abc");
        List<Integer> stringResult = fetchApi.insertRequest(stringRequest);
        assertTrue(stringResult.size() > 0, "Should convert string to ASCII values");

        // Verify all data is stored
        List<Integer> allStored = fetchApi.getStoredData();
        assertTrue(allStored.size() >= 4, "Should have stored all inserted data");
    }
}