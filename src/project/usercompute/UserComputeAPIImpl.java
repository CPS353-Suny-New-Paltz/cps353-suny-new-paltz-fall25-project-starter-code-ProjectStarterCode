package project.usercompute;

import java.util.List;

import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

public class UserComputeAPIImpl implements UserComputeAPI {

    private final InterComputeAPI inter;
    private final DataComputeAPI data;

    public UserComputeAPIImpl(InterComputeAPI inter, DataComputeAPI data) {
        if (inter == null) {
            throw new IllegalArgumentException("InterComputeAPI cannot be null.");
        }
        if (data == null) {
            throw new IllegalArgumentException("DataComputeAPI cannot be null.");
        }
        this.inter = inter;
        this.data = data;
    }

    @Override
    public ComputeResponse compute(ComputeRequest request) {
        try {
            if (request == null) {
                return new ComputeResponse(0, ComputeResponse.Status.FAIL);
            }

            List<Integer> inputs = null;

            if (request.getNumbers() != null && !request.getNumbers().isEmpty()) {
                inputs = request.getNumbers();
            } else if (request.getInputPath() != null && !request.getInputPath().isBlank()) {
                inputs = data.readInput(request.getInputPath());
            }

            if (inputs == null || inputs.isEmpty()) {
                System.err.println("UserComputeAPIImpl: No valid input provided.");
                return new ComputeResponse(0, ComputeResponse.Status.FAIL);
            }

            List<Integer> outputs = new java.util.ArrayList<>(inputs.size());
            for (Integer n : inputs) {
                if (n == null) {
                    outputs.add(-1);
                    continue;
                }
                int result = inter.processRequest(new InterRequest(n));
                outputs.add(result);
            }

            if (request.getOutputPath() != null && !request.getOutputPath().isBlank()) {
                data.writeOutput(outputs, request.getOutputPath());
            }

            return new ComputeResponse(
                outputs.get(outputs.size() - 1),
                ComputeResponse.Status.SUCCESS
            );

        } catch (Exception e) {
            System.err.println("UserComputeAPIImpl.compute error: " + e.getMessage());
            return new ComputeResponse(0, ComputeResponse.Status.FAIL);
        }
    }
}
