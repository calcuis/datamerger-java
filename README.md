### Data Merger

This Java code defines a class named MergeData with a main method and two private helper methods: mergeData and processDataFile. The purpose of this program is to merge data from three input files (data_file_4.txt, data_file_5.txt, and data_file_6.txt) into a single output file (results.txt). The input files are expected to contain lines with a specific format, and any lines not adhering to this format are considered invalid.

Here's a breakdown of the code:

Import Statements:
```
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
```
These statements import classes from the java.io and java.util packages, which are necessary for file I/O and using Map data structures.

Class Declaration:
```
public class MergeData {
```
The class is declared as public and named MergeData.

`main` Method:
```
public static void main(String[] args) {
```
The main method is the entry point of the program. It initializes input and output file names, calls the `mergeData` method, and handles any `IOException` that might occur during the merging process.

File Names:
```
String inputFile4 = "data_file_4.txt";
String inputFile5 = "data_file_5.txt";
String inputFile6 = "data_file_6.txt";
String outputFile = "results.txt";
```
These variables store the names of the three input files and the output file.

`try-catch` Block in main Method:
```
try {
    mergeData(inputFile4, inputFile5, inputFile6, outputFile);
    System.out.println("Merging successful!");
} catch (IOException e) {
    System.err.println("Error merging data: " + e.getMessage());
}
```
The `mergeData` method is called within a try block. If an `IOException` occurs during the merging, an error message is printed.

`mergeData` Method:
```
private static void mergeData(String inputFile4, String inputFile5, String inputFile6, String outputFile)
        throws IOException {
```
This private method is responsible for merging data from the three input files into a Map named `resultMap`, which uses strings as keys and integer arrays of size 3 as values. The method then writes the results to the output file.

`resultMap` Initialization:
```
Map<String, int[]> resultMap = new HashMap<>();
```
A `HashMap` is used to store the merged data.

Processing Data Files:
```
processDataFile(inputFile4, resultMap, 0);
processDataFile(inputFile5, resultMap, 1);
processDataFile(inputFile6, resultMap, 2);
```
The method calls `processDataFile` for each input file, passing the input file name, the `resultMap`, and an index representing the file number.

Writing Results to Output File:
```
try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
    for (Map.Entry<String, int[]> entry : resultMap.entrySet()) {
        String key = entry.getKey();
        int[] values = entry.getValue();
        writer.println(key + ": " + values[0] + ", " + values[1] + ", " + values[2]);
    }
}
```
The results stored in `resultMap` are written to the output file. Each line in the output file contains a key followed by three values separated by commas.

`processDataFile` Method:
```
private static void processDataFile(String inputFile, Map<String, int[]> resultMap, int index) throws IOException {
```
This private method processes data from a given input file. It reads each line, splits it into parts based on a comma followed by optional whitespace, and checks if there are exactly two parts. If so, it extracts the key and value and updates the resultMap accordingly. If the input format is invalid, an error message is printed.
