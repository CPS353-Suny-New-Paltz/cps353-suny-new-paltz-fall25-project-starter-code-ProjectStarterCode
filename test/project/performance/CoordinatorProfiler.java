package project.performance;

import project.usercompute.ComputeRequest;
import project.usercompute.ComputeResponse;
import project.usercompute.UserComputeAPI;
import project.usercompute.UserComputeAPIImpl;

import project.datacompute.InMemoryDataComputeAPI;

import project.intercompute.InterComputeAPIImpl;

import project.integration.InMemoryInpCon;
import project.integration.InMemoryOutCon;

import java.util.List;

public class CoordinatorProfiler {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1000, 5000, 10000, 50000, 100000);

        InMemoryInpCon in = new InMemoryInpCon(numbers);
        InMemoryOutCon out = new InMemoryOutCon();

        InMemoryDataComputeAPI memData =
                new InMemoryDataComputeAPI(in, out);

        UserComputeAPI user = new UserComputeAPIImpl(
                new InterComputeAPIImpl(),
                memData
        );

        ComputeRequest req =
                new ComputeRequest(numbers, "ignoredOutput.txt");

        long start = System.nanoTime();
        ComputeResponse res = user.compute(req);
        long end = System.nanoTime();

        System.out.println("Coordinator total time = " +
                ((end - start) / 1_000_000.0) + " ms");

        System.out.println("Stored output = " + out.getInts());
    }
}
