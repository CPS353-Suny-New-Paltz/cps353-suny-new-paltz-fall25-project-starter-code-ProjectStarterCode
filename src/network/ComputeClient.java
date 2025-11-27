package network;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import proto.compute.ComputeProto;
import proto.compute.ComputeServiceGrpc;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ComputeClient {
    private final ComputeServiceGrpc.ComputeServiceBlockingStub blockingStub;

    public ComputeClient(ManagedChannel channel) {
        blockingStub = ComputeServiceGrpc.newBlockingStub(channel);
    }

    public void runJob() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input file path (or leave blank to type numbers): ");
        String inputPath = scanner.nextLine();

        String tempInput = null;
        if (inputPath.isBlank()) {
            System.out.print("Enter numbers separated by spaces: ");
            String numbers = scanner.nextLine();
            tempInput = "temp_input.txt";
            try (java.io.PrintWriter out = new java.io.PrintWriter(tempInput)) {
                out.println(numbers.replace(" ", "\n"));
            } catch (Exception e) {
                System.err.println("Error creating temporary input file: " + e.getMessage());
                return;
            }
        }

        System.out.print("Enter output file path: ");
        String outputPath = scanner.nextLine();

        System.out.print("Enter delimiter (or leave blank for default): ");
        String delimiter = scanner.nextLine();

        ComputeProto.JobRequest request = ComputeProto.JobRequest.newBuilder()
            .setInputFile(inputPath.isBlank() ? tempInput : inputPath)
            .setOutputFile(outputPath)
            .setDelimiter(delimiter.isBlank() ? "," : delimiter)
            .build();

        try {
            ComputeProto.JobResponse response = blockingStub.submitJob(request);
            System.out.println("Job completed: " + response.getMessage());
            if (response.getSuccess()) {
                System.out.println("Output written to " + outputPath);
            }
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    public static void main(String[] args) throws Exception {
        String target = "localhost:50051";
        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
        try {
            ComputeClient client = new ComputeClient(channel);
            client.runJob();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
    
}
