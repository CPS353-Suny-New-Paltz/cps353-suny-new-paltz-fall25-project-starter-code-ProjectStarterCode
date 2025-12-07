package network;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;

import proto.compute.ComputeProto;
import proto.compute.ComputeServiceGrpc;

import proto.storage.StorageProto.ReadAllInputsRequest;
import proto.storage.StorageProto.MultiDataValueResponse;
import proto.storage.StorageProto.GenerateChartRequest;
import proto.storage.StorageProto.GenerateChartResponse;
import proto.storage.StorageServiceGrpc;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ComputeClient {

    private final ComputeServiceGrpc.ComputeServiceBlockingStub computeStub;
    private final StorageServiceGrpc.StorageServiceBlockingStub storageStub;

    public ComputeClient(ManagedChannel computeChannel, ManagedChannel storageChannel) {
        computeStub = ComputeServiceGrpc.newBlockingStub(computeChannel);
        storageStub = StorageServiceGrpc.newBlockingStub(storageChannel);
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

        // Submit compute job
        ComputeProto.JobRequest request = ComputeProto.JobRequest.newBuilder()
                .setInputFile(inputPath.isBlank() ? tempInput : inputPath)
                .setOutputFile(outputPath)
                .setDelimiter(delimiter.isBlank() ? "," : delimiter)
                .build();

        try {
            ComputeProto.JobResponse response = computeStub.submitJob(request);
            System.out.println("Job completed: " + response.getMessage());

            if (response.getSuccess()) {
                System.out.println("Output written to " + outputPath);
            }

            // Read numbers for chart
            String fileToRead = inputPath.isBlank() ? tempInput : inputPath;

            ReadAllInputsRequest req = ReadAllInputsRequest.newBuilder()
                    .setFilePath(fileToRead)
                    .build();

            MultiDataValueResponse readResp = storageStub.readAllInputs(req);
            List<Integer> nums = readResp.getValuesList();

            System.out.println("Numbers read: " + nums);

            // Generate prime vs non-prime chart in the same directory as output file
            String chartPath = Paths.get(outputPath).getParent() != null
                    ? Paths.get(outputPath).getParent().resolve("primeChart.png").toString()
                    : "primeChart.png"; // fallback if no parent directory

            GenerateChartRequest chartReq = GenerateChartRequest.newBuilder()
                    .addAllValues(nums)
                    .setOutputFilePath(chartPath)
                    .build();

            GenerateChartResponse chartResp = storageStub.generateChart(chartReq);

            System.out.println("Chart generation result: " + chartResp.getMessage());
            System.out.println("Chart saved at: " + chartPath);

        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    public static void main(String[] args) throws Exception {
        String computeTarget = "localhost:50051";
        String storageTarget = "localhost:50052";

        ManagedChannel computeChannel =
                Grpc.newChannelBuilder(computeTarget, InsecureChannelCredentials.create()).build();
        ManagedChannel storageChannel =
                Grpc.newChannelBuilder(storageTarget, InsecureChannelCredentials.create()).build();

        try {
            ComputeClient client = new ComputeClient(computeChannel, storageChannel);
            client.runJob();
        } finally {
            computeChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            storageChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
