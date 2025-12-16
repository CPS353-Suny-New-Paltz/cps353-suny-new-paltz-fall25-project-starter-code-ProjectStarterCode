package project.grpc;

import io.grpc.stub.StreamObserver;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import project.annotations.StorageAPI;

public class DatastoreServiceImpl
        extends DatastoreServiceGrpc.DatastoreServiceImplBase {

    private final StorageAPI storage;
    private final Path outputPath;

    public DatastoreServiceImpl(StorageAPI storage, String outputFile) {
        this.storage = storage;
        this.outputPath = Paths.get(outputFile);
    }

    @Override
    public void writeOutput(
            WriteOutputRequest request,
            StreamObserver<WriteOutputResponse> responseObserver) {

        try {
            List<String> outputs = request.getOutputsList();
            String joined = String.join(",", outputs);

            boolean ok = storage.writeOutput(outputPath, joined);

            WriteOutputResponse response = WriteOutputResponse.newBuilder()
                    .setSuccess(ok)
                    .setMessage(ok ? "OK" : "WRITE_FAILED")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            WriteOutputResponse response = WriteOutputResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("ERROR")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}