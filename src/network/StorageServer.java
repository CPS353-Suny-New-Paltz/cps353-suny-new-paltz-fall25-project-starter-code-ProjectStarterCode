package network;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.protobuf.services.ProtoReflectionService;
import proto.storage.StorageServiceGrpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StorageServer {

    private Server server;

    private void start() throws IOException {
        int port = 50052;
        server = ServerBuilder.forPort(50052)
                .addService(StorageServiceGrpc.bindService(new StorageServiceImpl()))
                .build()
                .start();

        System.out.println("StorageServer started on port " + port);

        // Graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down storage server since JVM is shutting down");
            try {
                if (server != null) {
                    server.shutdown().awaitTermination(10, TimeUnit.SECONDS);
                }
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** storage server shut down");
        }));
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        final StorageServer ss = new StorageServer();
        ss.start();
        ss.blockUntilShutdown();
    }
}
