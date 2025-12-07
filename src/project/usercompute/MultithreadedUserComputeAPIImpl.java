package project.usercompute;

import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;
import project.datacompute.DataComputeAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadedUserComputeAPIImpl implements UserComputeAPI {

    private final InterComputeAPI inter;
    private final DataComputeAPI data;
    private final ExecutorService executor;

    public MultithreadedUserComputeAPIImpl(InterComputeAPI inter, DataComputeAPI data) {
        if (inter == null) {
            throw new IllegalArgumentException("InterComputeAPI cannot be null.");
        }
        if (data == null) {
            throw new IllegalArgumentException("DataComputeAPI cannot be null.");
        }

        this.inter = inter;
        this.data = data;
        this.executor = Executors.newFixedThreadPool(4);
    }

    @Override
    public ComputeResponse compute(ComputeRequest request) {
        try {
            List<Integer> inputs = request.getNumbers();

            if ((inputs == null || inputs.isEmpty()) && request.getInputPath() != null) {
                inputs = data.readInput(request.getInputPath());
            }

            if (inputs == null || inputs.isEmpty()) {
                System.err.println("MultithreadedUserComputeAPIImpl: No valid input provided.");
                return new ComputeResponse(0, ComputeResponse.Status.FAIL);
            }

            List<Future<Integer>> futures = new ArrayList<>();

            for (final Integer n : inputs) {
                final int value = (n == null) ? -1 : n;

                Callable<Integer> task = new Callable<Integer>() {
                    @Override
                    public Integer call() {
                        return inter.processRequest(new InterRequest(value));
                    }
                };

                futures.add(executor.submit(task));
            }

            List<Integer> outputs = new ArrayList<>();
            for (Future<Integer> future : futures) {
                outputs.add(future.get());
            }

            if (request.getOutputPath() != null) {
                data.writeOutput(outputs, request.getOutputPath());
            }

            return new ComputeResponse(
                    outputs.get(outputs.size() - 1),
                    ComputeResponse.Status.SUCCESS
            );

        } catch (Exception e) {
            System.err.println("MultithreadedUserComputeAPIImpl.compute error: " + e.getMessage());
            return new ComputeResponse(0, ComputeResponse.Status.FAIL);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
