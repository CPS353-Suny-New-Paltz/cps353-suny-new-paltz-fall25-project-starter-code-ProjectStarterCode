package project.intercompute;

import project.datacompute.DataComputeAPI;

public class InterComputeAPIImpl implements InterComputeAPI {

    private DataComputeAPI data;

    public void setData(DataComputeAPI data) {
        this.data = data;
    }

    @Override
    public void processRequest(InterRequest req) {
    }
}
