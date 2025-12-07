package project.usercompute;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

public class UserComputeServer {

    private Server server;

    public void start(int port) throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(new UserComputeServiceImpl())  
                .addService(ProtoReflectionService.newInstance())
                .build()
                .start();

        System.out.println("UserComputeServer started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server...");
            try {
                UserComputeServer.this.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    public void stop() throws Exception {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws Exception {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        UserComputeServer server = new UserComputeServer();
        server.start(50051);
        server.blockUntilShutdown();
    }
}
