package network;

import io.grpc.stub.StreamObserver;
import conceptual.ComputeEngineImpl;
import proto.compute.ComputeProto;
import proto.compute.ComputeServiceGrpc;

public class ComputeServiceImpl extends ComputeServiceGrpc.ComputeServiceImplBase {
    private final MultithreadedNetworkAPI networkAPI;

    public ComputeServiceImpl() {
        this.networkAPI = new MultithreadedNetworkAPI(
            new UserComputeImpl(new ComputeEngineImpl(), new StorageGrpcClient("localhost:50052"))
        );
    }

    @Override
    public void submitJob(ComputeProto.JobRequest request, StreamObserver<ComputeProto.JobResponse> responseObserver) {
        try {
            JobRequest internalReq = new JobRequestImpl(
                request.getInputFile(),
                request.getOutputFile(),
                request.getDelimiter()
            );

            JobResponse internalRes = networkAPI.submitJob(internalReq);

            String chartPath = internalReq.getOutputDestination().replace(".txt", "_chart.png");
            
            ComputeProto.JobResponse response = ComputeProto.JobResponse.newBuilder()
                .setSuccess(internalRes.isSuccess())
                .setMessage(internalRes.getMessage() + ". Chart saved at: " + chartPath)
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
