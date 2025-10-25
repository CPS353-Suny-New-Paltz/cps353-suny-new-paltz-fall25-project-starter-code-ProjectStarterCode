package project.networkapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestUserComputeAPI {
    @Test
    public void testSubmissionSuccess() {
        // Instantiate the implementation
        UserComputeAPI api = new UserComputeAPIImplementation();

        UserSubmission sub = new UserSubmission(
                new InputSource("file", "input.txt"),
                new OutputSource("stdout"),
                new Delimiter(';')
        );

        // Call the method on the object
        UserSubResponse resp = api.submit(sub);

        // Assert the results
        assertEquals(SubmissionStatus.SUCCESS, resp.getStatus());
        assertEquals("sub-1", resp.getSubId());
    }
}