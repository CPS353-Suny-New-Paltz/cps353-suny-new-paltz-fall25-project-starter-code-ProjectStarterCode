package project.usercompute;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;
public class SmokeUserImplTest {
    @Test void constructs() {
        InterComputeAPI inter = mock(InterComputeAPI.class);
        DataComputeAPI data = mock(DataComputeAPI.class);
        new UserComputeAPIImpl(inter, data);
    }
}
