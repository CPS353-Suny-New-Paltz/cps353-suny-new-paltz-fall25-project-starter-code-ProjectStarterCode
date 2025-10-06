package project.usercompute;

import java.util.List;

import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

public class UserComputeAPIImpl implements UserComputeAPI {

    private final InterComputeAPI inter;
    private final DataComputeAPI data;

    public UserComputeAPIImpl(InterComputeAPI inter, DataComputeAPI data) {
        this.inter = inter;
        this.data = data;
    }

    @Override
    public boolean handle(String inputPath, String outputPath) {
        if (inter == null || data == null || inputPath == null || outputPath == null) {
            return false;
        }
        List<Integer> ns = data.readInput(inputPath);
        if (ns == null) {
            return false;
        }
        List<String> results = inter.computeAll(ns);
        data.writeOutput(results, outputPath);
        return true;
    }

    @Override
    public void handleRequest(UserRequest userRequest) {
        if (inter == null || userRequest == null) {
            return;
        }
        inter.processRequest(new InterRequest(userRequest.getBytes()));
    }
}