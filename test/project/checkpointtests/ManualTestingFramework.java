package project.checkpointtests;

import project.datacompute.DataComputeAPIImpl;
import project.intercompute.InterComputeAPIImpl;
import project.usercompute.ComputeRequest;
import project.usercompute.ComputeResponse;
import project.usercompute.UserComputeAPI;
import project.usercompute.UserComputeAPIImpl;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        
        UserComputeAPI user = new UserComputeAPIImpl(
            new InterComputeAPIImpl(),
            new DataComputeAPIImpl()
        );

        ComputeRequest request = new ComputeRequest(INPUT, OUTPUT);
        ComputeResponse response = user.compute(request);

        System.out.println("Success: " + response.isSuccess() + ", result=" + response.getResult());
    }
}
