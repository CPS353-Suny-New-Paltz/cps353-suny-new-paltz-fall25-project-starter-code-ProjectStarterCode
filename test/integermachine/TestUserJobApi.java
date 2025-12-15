package integermachine;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;





@ExtendWith(MockitoExtension.class)
public class TestUserJobApi {

    @Mock
    private Orchestrator orchestrator;

    @InjectMocks
    private UserJobApiImpl api;

    @Test
    void submitJob_callsOrchestratorRunJob() {
        JobConfig config = new JobConfig(
                new InputSourceRef("in"),
                new OutputSinkRef("out"),
                null
        );

        api.submitJob(config);

        String result = api.submitJob(config);
        assertNotNull(result);

    }
}
