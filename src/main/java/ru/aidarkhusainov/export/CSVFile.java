package ru.aidarkhusainov.export;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class CSVFile {
    public void export(File exportPath, List<String> headers, List<List<String>> records) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(exportPath.toPath());
                CSVPrinter csvPrinter = new CSVPrinter(writer,
                        CSVFormat.DEFAULT.withHeader(Arrays.toString(headers.toArray())));
        ) {
            for (List<String> record : records) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
        }
    }

    private static final String SAMPLE_CSV_FILE = "./sample.csv";

//    public static void main(String[] args) throws IOException {
//
//        File csvOutputFile = new File(SAMPLE_CSV_FILE);
//        CsvMapper mapper = new CsvMapper();
//        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
//
//        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
//                .addColumn("productId")
//                .addColumn("sellerId")
//                .addColumn("productTitle")
//                .build();
//
//        ObjectWriter writer = mapper.writerFor(AliTemplate.class).with(schema);
//
//        writer.writeValues(csvOutputFile).writeAll(Arrays.asList("1", "11", "233"));
//
//        System.out.println("Users saved to csv file under path: ");
//        System.out.println(csvOutputFile);
//    }
}
