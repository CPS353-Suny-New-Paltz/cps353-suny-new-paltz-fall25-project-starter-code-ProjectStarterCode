package project.network.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import project.network.grpc.NetworkProto;
import project.network.grpc.NetworkServiceGrpc;

public class NetworkClient {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // --- Host & Port ---
    System.out.print("Enter server host (default localhost): ");
    String host = scanner.nextLine().trim();
    if (host.isEmpty())
      host = "localhost";

    System.out.print("Enter server port (default 50051): ");
    String portStr = scanner.nextLine().trim();
    int port = portStr.isEmpty() ? 50051 : Integer.parseInt(portStr);

    ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext().build();

    NetworkServiceGrpc.NetworkServiceBlockingStub stub = NetworkServiceGrpc
        .newBlockingStub(channel);

    // --- Input ---
    System.out.print("Input type? (1 = file, 2 = numbers): ");
    String choice = scanner.nextLine().trim();

    String inputUri = null;
    List<BigInteger> numbers = new ArrayList<>();

    if ("1".equals(choice)) {
      System.out.print("Enter input file path: ");
      inputUri = scanner.nextLine();
    } else {
      System.out.print("Enter numbers separated by spaces: ");
      String line = scanner.nextLine();
      for (String s : line.trim().split("\\s+")) {
        if (!s.isEmpty())
          numbers.add(new BigInteger(s));
      }
    }

    // --- Output ---
    System.out.print("Enter output file path: ");
    String outputFile = scanner.nextLine();

    System.out.print("Enter delimiter (optional, default ','): ");
    String delim = scanner.nextLine();
    if (delim.isEmpty())
      delim = ",";

    // --- Build input resource ---
    NetworkProto.Resource inputResource;
    if ("1".equals(choice)) {
      inputResource = NetworkProto.Resource.newBuilder()
          .setType(NetworkProto.ResourceType.FILE).setUri(inputUri).build();
    } else {
      NetworkProto.Resource.Builder inBuilder = NetworkProto.Resource
          .newBuilder().setType(NetworkProto.ResourceType.CUSTOM);
      for (BigInteger bi : numbers) {
        inBuilder.addData(bi.toString());
      }
      inputResource = inBuilder.build();
    }

    // --- Build output resource ---
    NetworkProto.Resource outputResource = NetworkProto.Resource.newBuilder()
        .setType(NetworkProto.ResourceType.FILE).setUri(outputFile).build();

    NetworkProto.ComputationRequest request = NetworkProto.ComputationRequest
        .newBuilder().setInputResource(inputResource)
        .setOutputResource(outputResource).setDelimiter(delim).build();

    System.out.println("Sending compute request...");
    NetworkProto.ComputationResponse response = stub.compute(request);

    System.out.println("Computation finished:");
    System.out.println("Status: " + response.getStatus());
    System.out.println("Message: " + response.getMessage());
    System.out.println("Results: " + response.getResultsList());

    channel.shutdown();
    scanner.close();
  }
}
