package project.usercompute;

import project.intercompute.InterComputeAPI;

public class UserComputeAPIImpl implements UserComputeAPI {

    private InterComputeAPI inter;

    public void setInter(InterComputeAPI inter) {
        this.inter = inter;
    }

    @Override
    public void handleRequest(UserRequest req) {
    }
}
