package network;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.protobuf.services.ProtoReflectionService;
import proto.compute.ComputeServiceGrpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ComputeServer {
    private Server server;

    private void start() throws IOException {
        int port = 50051;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(ComputeServiceGrpc.bindService(new ComputeServiceImpl()))
                .addService(ProtoReflectionService.newInstance())
                .build()
                .start();

        System.out.println("ComputeServer started on port " + port);

        // graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                if (server != null) {
                    server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                }
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        final ComputeServer server = new ComputeServer();
        server.start();
        server.blockUntilShutdown();
    }
}
