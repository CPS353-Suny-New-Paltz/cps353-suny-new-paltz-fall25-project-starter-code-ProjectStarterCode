package project.datacompute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataComputeAPIImpl implements DataComputeAPI {

    @Override
    public List<Integer> readInput(String inputPath) {
        List<Integer> ns = new ArrayList<>();
        if (inputPath == null) {
            return ns;
        }
        Path p = Path.of(inputPath);
        try (BufferedReader r = Files.newBufferedReader(p)) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    ns.add(Integer.parseInt(line));
                }
            }
        } catch (Exception e) {
            return null;
        }
        return ns;
    }

    @Override
    public void writeOutput(List<String> out, String outputPath) {
        if (outputPath == null || out == null) {
            return;
        }
        Path p = Path.of(outputPath);
        try (BufferedWriter w = Files.newBufferedWriter(p)) {
            for (String s : out) {
                w.write(s == null ? "" : s);
                w.newLine();
            }
        } catch (Exception e) {
            // swallow or log in real code
        }
    }

    @Override
    public void insertRequest(DataRequest dataRequest) {
    }
}