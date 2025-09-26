package project.datacompute;

import org.junit.jupiter.api.Test;

public class TestDataComputeAPI {

    @Test
    void smokeInsertRequestRuns() {
        DataComputeAPI api = new DataComputeAPIImpl();
        DataRequest req = new DataRequest(new byte[]{1, 2, 3});
        api.insertRequest(req);
    }
}