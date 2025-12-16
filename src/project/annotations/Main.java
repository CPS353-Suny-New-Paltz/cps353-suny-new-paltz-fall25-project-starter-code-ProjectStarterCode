package project.annotations;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StorageAPI storage = new StorageAPI() {
            
            @Override
            public List<Integer> readInput(Path source) {
                System.out.println("readInput: " + source);
                List<Integer> demo = new ArrayList<>();
                demo.add(5); 
                return demo;
            }

            @Override
            public int parseInput(List<Integer> rawData) {
                System.out.println("parseInput: " + rawData);
                return rawData.isEmpty() ? -1 : rawData.get(0);
            }

            @Override
            public String formatOutput(List<Integer> data) {
                System.out.println("formatOutput: " + data);
                return data.toString();
            }

            @Override
            public boolean writeOutput(Path destination, String formattedOutput) {
                System.out.println("writeOutput → " + destination + " : " + formattedOutput);
                return true;
            }
        };

        new StoragePrototype().prototype(storage);
    }
}
