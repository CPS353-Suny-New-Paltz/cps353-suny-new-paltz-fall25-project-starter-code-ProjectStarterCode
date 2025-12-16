package project.checkpoint4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import project.annotations.ProcessAPI;

@ProcessAPI
public class DataStorageComponent {

    public List<Integer> readInputFile(Path inputFile) {

        if (inputFile == null) {
            return new ArrayList<>();
        }

        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    numbers.add(Integer.parseInt(line));
                }
            }
        } catch (IOException | NumberFormatException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return numbers;
    }

    public boolean writeOutputFile(Path outputFile, List<Integer> results) {

        if (outputFile == null || results == null) {
            return false;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            for (int n : results) {
                writer.write(Integer.toString(n));
                writer.newLine();
            }
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}