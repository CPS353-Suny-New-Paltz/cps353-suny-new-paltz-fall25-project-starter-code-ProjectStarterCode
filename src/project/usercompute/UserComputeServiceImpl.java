package project.usercompute;

import java.util.List;

import io.grpc.stub.StreamObserver;
import project.usercompute.proto.UserComputeServiceGrpc.UserComputeServiceImplBase;
import project.usercompute.proto.UserComputeApi.UserComputeRequestProto;
import project.usercompute.proto.UserComputeApi.UserComputeResponseProto;

import project.intercompute.InterComputeAPIImpl;
import project.intercompute.InterComputeAPI;

import project.datacompute.DataComputeGrpcClient;
import project.datacompute.DataComputeAPI;

public class UserComputeServiceImpl extends UserComputeServiceImplBase {

    private final MultithreadedUserComputeAPIImpl engine;

    public UserComputeServiceImpl() {

        InterComputeAPI inter = new InterComputeAPIImpl();

        DataComputeAPI data = new DataComputeGrpcClient("localhost", 60051);

        this.engine = new MultithreadedUserComputeAPIImpl(inter, data);
    }

    @Override
    public void runComputation(
            UserComputeRequestProto request,
            StreamObserver<UserComputeResponseProto> responseObserver) {

        try {

            ComputeRequest internal = convertToInternal(request);


            ComputeResponse result = engine.compute(internal);


            UserComputeResponseProto.Builder builder = UserComputeResponseProto.newBuilder()
                    .setSuccess(result.getStatus() == ComputeResponse.Status.SUCCESS)
                    .setMessage(result.getStatus().toString());

            if (request.hasOutputFile()) {
                builder.setOutputFile(request.getOutputFile());
            }

            UserComputeResponseProto response = builder.build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onNext(
                    UserComputeResponseProto.newBuilder()
                            .setSuccess(false)
                            .setMessage(e.getMessage())
                            .build()
            );
            responseObserver.onCompleted();
        }
    }

    private ComputeRequest convertToInternal(UserComputeRequestProto req) {

        boolean hasInline = req.getInlineNumbersCount() > 0;
        boolean hasInputFile = req.hasInputFile();

        String output = req.hasOutputFile() ? req.getOutputFile() : null;

        if (hasInline) {

            List<Integer> ints =
                    req.getInlineNumbersList().stream()
                            .map(d -> (int) d.doubleValue())
                            .toList();

            return new ComputeRequest(ints, output);
        }

        if (hasInputFile) {
            return new ComputeRequest(req.getInputFile(), output);
        }

        throw new IllegalArgumentException("Request must contain either inline numbers or an input file.");
    }

}
