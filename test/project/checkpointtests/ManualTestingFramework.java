package project.checkpointtests;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import conceptual.ComputeEngineAPI;
import conceptual.ComputeEngineImpl;
import process.StorageComputeAPI;
import process.StorageComputeImpl;
import network.UserComputeAPI;
import network.UserComputeImpl;
import network.JobRequest;
import network.JobRequestImpl;
import network.JobResponse;

public class ManualTestingFramework {

    public static final String INPUT = "numbers.txt";
    public static final String OUTPUT = "output.txt";

    public static void main(String[] args) throws Exception {
        Path inputPath = Paths.get(INPUT);
        if (inputPath.getParent() != null) {
            Files.createDirectories(inputPath.getParent());
        }
        Files.write(inputPath, "1\n2\n3".getBytes());

        StorageComputeAPI storage = new StorageComputeImpl();
        ComputeEngineAPI engine = new ComputeEngineImpl();
        UserComputeAPI userAPI = new UserComputeImpl(engine, storage);

        JobRequest job = new JobRequestImpl(INPUT, OUTPUT, ",");
        JobResponse response = userAPI.submitJob(job);

        System.out.println("Job success: " + response.isSuccess());
        System.out.println("Message: " + response.getMessage());
    }
}