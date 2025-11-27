package network;

import io.grpc.stub.StreamObserver;
import proto.storage.StorageProto;
import proto.storage.StorageServiceGrpc;
import process.DataValue;
import process.DataValueImpl;
import process.MultiDataValue;
import process.StorageComputeImpl;


import java.util.List;

public class StorageServiceImpl extends StorageServiceGrpc.StorageServiceImplBase {

    private final StorageComputeImpl storageImpl;

    public StorageServiceImpl() {
        this.storageImpl = new StorageComputeImpl();
    }

    @Override
    public void readAllInputs(StorageProto.ReadAllInputsRequest request,
                              StreamObserver<StorageProto.MultiDataValueResponse> responseObserver) {
        String path = request.getFilePath();
        if (path == null || path.isEmpty()) {
            responseObserver.onError(new IllegalArgumentException("File path cannot be null or empty"));
            return;
        }

        MultiDataValue mdv = storageImpl.readAllInputs(path);
        List<Integer> vals = mdv.getValues();

        StorageProto.MultiDataValueResponse.Builder resb =
                StorageProto.MultiDataValueResponse.newBuilder();
        if (vals != null && !vals.isEmpty()) {
            resb.addAllValues(vals);
        } else {
            resb.setError("No values found");
        }

        responseObserver.onNext(resb.build());
        responseObserver.onCompleted();
    }

    @Override
    public void writeAllOutputs(StorageProto.WriteAllOutputsRequest request,
                                StreamObserver<StorageProto.WriteResponse> responseObserver) {
        String path = request.getFilePath();
        List<Integer> vals = request.getValuesList();

        if (path == null || path.isEmpty() || vals == null) {
            responseObserver.onError(new IllegalArgumentException("Invalid path or values"));
            return;
        }

        boolean ok = storageImpl.writeAllOutputs(path, vals);
        StorageProto.WriteResponse res = StorageProto.WriteResponse.newBuilder()
                .setSuccess(ok)
                .setMessage(ok ? "written" : "failed")
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void readInput(StorageProto.ReadInputRequest request,
                          StreamObserver<StorageProto.DataValueResponse> responseObserver) {
        String path = request.getFilePath();
        DataValue dv = storageImpl.readInput(path);

        StorageProto.DataValueResponse res = StorageProto.DataValueResponse.newBuilder()
                .setValue(dv == null ? -1 : dv.getValue())
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void writeOutput(StorageProto.WriteOutputRequest request,
                            StreamObserver<StorageProto.WriteResponse> responseObserver) {
        String path = request.getFilePath();
        int value = request.getValue();

        boolean ok = storageImpl.writeOutput(path, new DataValueImpl(value));
        StorageProto.WriteResponse res = StorageProto.WriteResponse.newBuilder()
                .setSuccess(ok)
                .setMessage(ok ? "written" : "failed")
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
