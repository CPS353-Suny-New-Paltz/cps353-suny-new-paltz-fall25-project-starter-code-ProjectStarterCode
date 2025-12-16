package project.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import project.annotations.StorageAPI;
import project.StorageAPIImpl; 

public class DatastoreServerMain {

    public static void main(String[] args) throws Exception {

        StorageAPI storage = new StorageAPIImpl();

        Server server = ServerBuilder
                .forPort(50052)
                .addService(
                        new DatastoreServiceImpl(
                                storage,
                                "manualTestOutput.txt"))
                .build();

        server.start();

        System.out.println("Datastore gRPC server started on port 50052");

        server.awaitTermination();
    }
}