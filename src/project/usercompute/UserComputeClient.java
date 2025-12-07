package project.usercompute;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import project.usercompute.proto.UserComputeApi.UserComputeRequestProto;
import project.usercompute.proto.UserComputeApi.UserComputeResponseProto;
import project.usercompute.proto.UserComputeServiceGrpc;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserComputeClient {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("input type:");
        System.out.println("1) inline");
        System.out.println("2) file");

        int choice = Integer.parseInt(scanner.nextLine());
        UserComputeRequestProto.Builder reqBuilder = UserComputeRequestProto.newBuilder();

        // inline nums
        if (choice == 1) {
            System.out.println("nums:");
            String[] parts = scanner.nextLine().trim().split("\\s+");

            List<Double> nums = new ArrayList<>();
            for (String p : parts) {
                nums.add(Double.parseDouble(p));
            }

            reqBuilder.addAllInlineNumbers(nums);

        } else if (choice == 2) {
            // file input
            System.out.print("in file: ");
            reqBuilder.setInputFile(scanner.nextLine().trim());
        }

        // out file
        System.out.print("out file: ");
        reqBuilder.setOutputFile(scanner.nextLine().trim());

        // delimiter (optional)
        System.out.print("delim: ");
        String delimiter = scanner.nextLine().trim();
        if (!delimiter.isBlank()) {
            reqBuilder.setDelimiter(delimiter);
        }

        // connect
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        UserComputeServiceGrpc.UserComputeServiceBlockingStub stub =
                UserComputeServiceGrpc.newBlockingStub(channel);

        System.out.println("run...");
        UserComputeResponseProto res = stub.runComputation(reqBuilder.build());

        System.out.println("ok: " + res.getSuccess());
        System.out.println("msg: " + res.getMessage());
        if (res.hasOutputFile()) {
            System.out.println("out: " + res.getOutputFile());
        }

        channel.shutdown();
        System.out.println("done");
    }
}
