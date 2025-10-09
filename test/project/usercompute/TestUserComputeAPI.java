package project.usercompute;

import org.junit.jupiter.api.Test;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;
import static org.mockito.ArgumentMatchers.any;

public class TestUserComputeAPI {

    @Test
    void smokeHandleRequestWithMockedInterLayer() {
        InterComputeAPI mockInter = mock(InterComputeAPI.class);

        // ctor injection instead of no-arg + setInter
        UserComputeAPIImpl user = new UserComputeAPIImpl(mockInter, null);

        UserRequest req = new UserRequest(new byte[]{9, 9});
        user.handleRequest(req);

        verify(mockInter, atLeast(0)).processRequest(any(InterRequest.class));
    }
}