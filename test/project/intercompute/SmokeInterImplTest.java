package project.intercompute;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import project.datacompute.DataComputeAPI;
public class SmokeInterImplTest {
    @Test void constructs() {
        DataComputeAPI data = mock(DataComputeAPI.class);
        new InterComputeAPIImpl(data);
    }
}
