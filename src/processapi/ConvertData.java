package processapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;




//Method to convert data within file back to hashmap
public class ConvertData {
    public static HashMap<Integer, String> convertData() {
        File file = new File("src/Output/output.txt");
        Scanner scanner = null;
        String delimiter = ","; // You can change this if needed

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>(); // Return empty map if file not found
        }

        HashMap<Integer, String> map = new HashMap<>();

        while (scanner.hasNext()) {
            int jobID=0; // Generate unique job ID
            String line = scanner.nextLine(); // Read the line
            String[] values = line.split(delimiter); // Split by delimiter
            // Optionally format the values into a single string
            //to be finished

            map.put(jobID, line);
        }

        scanner.close();
        return map;
    }

}
