package project.datacompute;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import project.datacompute.proto.DataComputeApi.ReadInputRequest;
import project.datacompute.proto.DataComputeApi.ReadInputResponse;
import project.datacompute.proto.DataComputeApi.WriteOutputRequest;
import project.datacompute.proto.DataComputeApi.WriteOutputResponse;
import project.datacompute.proto.DataComputeServiceGrpc;
import java.util.List;

public class DataComputeGrpcClient implements DataComputeAPI {

    private final ManagedChannel channel;
    private final DataComputeServiceGrpc.DataComputeServiceBlockingStub stub;

    public DataComputeGrpcClient(String host, int port) {
        this.channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        this.stub = DataComputeServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public List<Integer> readInput(String inputPath) {
        ReadInputRequest req = ReadInputRequest.newBuilder()
                .setInputFile(inputPath)
                .build();

        ReadInputResponse res = stub.readInput(req);
        return res.getNumbersList();
    }

    @Override
    public void writeOutput(List<Integer> nums, String outputPath) {
        WriteOutputRequest req = WriteOutputRequest.newBuilder()
                .addAllNumbers(nums)
                .setOutputFile(outputPath)
                .build();

        WriteOutputResponse res = stub.writeOutput(req);

        if (!res.getSuccess()) {
            throw new RuntimeException("Write failed: " + res.getMessage());
        }
    }

    public void shutdown() {
        channel.shutdown();
    }
}
