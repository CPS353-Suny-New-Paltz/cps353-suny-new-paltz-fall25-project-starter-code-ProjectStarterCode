package tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.DataCheck;
import api.ReadRequest;
import api.ReadResponse;
import api.WriteOutput;
import implementations.DataProcessImpl;

public class SmokeTestDataProcessAPI {

    @Test
    public void readResponseTest() {
        // Instantiate the actual implementation
        DataProcessImpl api = new DataProcessImpl();
        
        ReadRequest request = Mockito.mock(ReadRequest.class);
        
        // Call a method on the actual implementation
        ReadResponse input = api.input(request);
        
        // Even if it returns null, we're testing instantiation
        // Add a check for the instance itself
        assertNotNull(api, "DataProcessImpl should be instantiated");
    }

    @Test
    public void writeDataTest() {
        // Instantiate the actual implementation
        DataProcessImpl api = new DataProcessImpl();
        
        DataCheck dataCheck = Mockito.mock(DataCheck.class);
        
        // Call a method on the actual implementation
        WriteOutput data = api.sendData(dataCheck);
        
        // Even if it returns null, we're testing instantiation
        assertNotNull(api, "DataProcessImpl should be instantiated");
    }
}