import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MergeData {

    public static void main(String[] args) {
        String inputFile4 = "data_file_4.txt";
        String inputFile5 = "data_file_5.txt";
        String inputFile6 = "data_file_6.txt";
        String outputFile = "results.txt";

        try {
            mergeData(inputFile4, inputFile5, inputFile6, outputFile);
            System.out.println("Merging successful!");
        } catch (IOException e) {
            System.err.println("Error merging data: " + e.getMessage());
        }
    }

    private static void mergeData(String inputFile4, String inputFile5, String inputFile6, String outputFile)
            throws IOException {
        Map<String, int[]> resultMap = new HashMap<>();

        // Process data from data_file_4
        processDataFile(inputFile4, resultMap, 0);

        // Process data from data_file_5
        processDataFile(inputFile5, resultMap, 1);

        // Process data from data_file_6
        processDataFile(inputFile6, resultMap, 2);

        // Write results to the output file
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            for (Map.Entry<String, int[]> entry : resultMap.entrySet()) {
                String key = entry.getKey();
                int[] values = entry.getValue();
                writer.println(key + ": " + values[0] + ", " + values[1] + ", " + values[2]);
            }
        }
    }

    private static void processDataFile(String inputFile, Map<String, int[]> resultMap, int index) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 2) {
                    String key = parts[0];
                    int value = Integer.parseInt(parts[1]);

                    // If the key is not in the resultMap, add it with an array of size 3
                    resultMap.computeIfAbsent(key, k -> new int[3])[index] = value;
                } else {
                    System.err.println("Invalid input format: " + line);
                }
            }
        }
    }
}
