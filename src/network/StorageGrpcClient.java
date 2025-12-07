package network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import process.DataValue;
import process.DataValueImpl;
import process.MultiDataValue;
import process.MultiDataValueImpl;
import process.StorageComputeAPI;
import proto.storage.StorageProto;
import proto.storage.StorageServiceGrpc;

public class StorageGrpcClient implements StorageComputeAPI {

    private final ManagedChannel channel;
    private final StorageServiceGrpc.StorageServiceBlockingStub blockingStub;

    public StorageGrpcClient(String target) {
        this.channel = Grpc.newChannelBuilder(target, io.grpc.InsecureChannelCredentials.create()).build();
        this.blockingStub = StorageServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    @Override
    public DataValue readInput(String filePath) {
        try {
            StorageProto.ReadInputRequest req = StorageProto.ReadInputRequest.newBuilder()
                    .setFilePath(filePath == null ? "" : filePath)
                    .build();

            StorageProto.DataValueResponse res = blockingStub.readInput(req);
            if (res.hasError() && !res.getError().isEmpty()) {
                return new DataValueImpl(-1);
            }
            return new DataValueImpl(res.getValue());
        } catch (StatusRuntimeException e) {
            System.err.println("StorageGrpcClient.readInput RPC failed: " + e.getStatus());
            return new DataValueImpl(-1);
        } catch (Exception ex) {
            System.err.println("StorageGrpcClient.readInput error: " + ex.getMessage());
            return new DataValueImpl(-1);
        }
    }

    @Override
    public boolean writeOutput(String filePath, DataValue value) {
        try {
            StorageProto.WriteOutputRequest req = StorageProto.WriteOutputRequest.newBuilder()
                    .setFilePath(filePath == null ? "" : filePath)
                    .setValue(value == null ? -1 : value.getValue())
                    .build();

            StorageProto.WriteResponse res = blockingStub.writeOutput(req);
            return res.getSuccess();
        } catch (StatusRuntimeException e) {
            System.err.println("StorageGrpcClient.writeOutput RPC failed: " + e.getStatus());
            return false;
        } catch (Exception ex) {
            System.err.println("StorageGrpcClient.writeOutput error: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public MultiDataValue readAllInputs(String filePath) {
        try {
            StorageProto.ReadAllInputsRequest req = StorageProto.ReadAllInputsRequest.newBuilder()
                    .setFilePath(filePath == null ? "" : filePath)
                    .build();

            StorageProto.MultiDataValueResponse res = blockingStub.readAllInputs(req);

            List<Integer> values = new ArrayList<>(res.getValuesCount());
            values.addAll(res.getValuesList());

            return new MultiDataValueImpl(values);
        } catch (StatusRuntimeException e) {
            System.err.println("StorageGrpcClient.readAllInputs RPC failed: " + e.getStatus());
            return new MultiDataValueImpl(new ArrayList<>());
        } catch (Exception ex) {
            System.err.println("StorageGrpcClient.readAllInputs error: " + ex.getMessage());
            return new MultiDataValueImpl(new ArrayList<>());
        }
    }

    @Override
    public boolean writeAllOutputs(String outputDestination, List<Integer> values) {
        try {
            StorageProto.WriteAllOutputsRequest.Builder b = StorageProto.WriteAllOutputsRequest.newBuilder()
                    .setFilePath(outputDestination == null ? "" : outputDestination);
            if (values != null) {
                b.addAllValues(values);
            }
            // default delimiter
            StorageProto.WriteResponse res = blockingStub.writeAllOutputs(b.build());
            return res.getSuccess();
        } catch (StatusRuntimeException e) {
            System.err.println("StorageGrpcClient.writeAllOutputs RPC failed: " + e.getStatus());
            return false;
        } catch (Exception ex) {
            System.err.println("StorageGrpcClient.writeAllOutputs error: " + ex.getMessage());
            return false;
        }
    }
}