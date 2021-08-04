package ru.aidarkhusainov.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CSVFile<T> {
    StringBuilder exportData = new StringBuilder();
    private File exportPath;
    private List<String> headers;
    private List<List<T>> records;
    private String delimiter;

    public CSVFile(File exportPath, List<String> headers, List<List<T>> records, String delimiter) {
        this.exportPath = exportPath;
        this.headers = headers;
        this.records = records;
        this.delimiter = delimiter;
    }

    public void export() throws IOException {

        try (PrintWriter writer = new PrintWriter(exportPath, "Cp1251")) {
            buildHeader(headers);
            buildRecords(records);

            writer.write(exportData.toString());
            writer.flush();
        }
    }

    private void buildHeader(List<String> headers) {
        for (String header : headers) {
            exportData.append(header).append(delimiter);
        }
        exportData.append('\n');
    }

    private void buildRecords(List<List<T>> records) {
        for (List<T> line : records) {
            for (T record : line) {
                exportData.append(record).append(delimiter);
            }
            exportData.append('\n');
        }
    }

//    private static final String SAMPLE_CSV_FILE = "./sample.csv";

//    public static void main(String[] args) throws FileNotFoundException {
//
//        try (PrintWriter writer = new PrintWriter(new File(SAMPLE_CSV_FILE))) {
//
//            StringBuilder sb = new StringBuilder();
//            sb.append("id");
//            sb.append(',');
//            sb.append("Name");
//            sb.append('\n');
//
//            sb.append("1");
//            sb.append(',');
//            sb.append("Prashant Ghimire");
//            sb.append('\n');
//
//            writer.write(sb.toString());
//
//            System.out.println("done!");
//
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}
