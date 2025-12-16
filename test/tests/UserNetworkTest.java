package tests;

import api.InputVerified;
import api.NetworkResponse;
import api.UserInput;
import api.UserNetwork;
import implementations.UserNetworkImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserNetworkTest {

    @Test
    public void inputVerifiedTest(){
        // Instantiate the actual implementation
        UserNetworkImpl api = new UserNetworkImpl();  // Changed from UserNetwork

        UserInput input = Mockito.mock(UserInput.class);

        InputVerified verified = api.check(input);

        assertNotNull(verified, "good input");
    }

    @Test
    public void networkResponseTest(){
        // Instantiate the actual implementation
        UserNetworkImpl api = new UserNetworkImpl();  // Changed from UserNetwork
        
        UserInput input = Mockito.mock(UserInput.class);
        InputVerified verified = Mockito.mock(InputVerified.class);

        NetworkResponse response = api.answer(verified, input);

        assertNotNull(response, "Start Calculating!!");
    }
}