package ru.aidarkhusainov.export;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aidarkhusainov.export.model.goods.AliTemplate;
import ru.aidarkhusainov.httpclient.HttpClient;
import ru.aidarkhusainov.parser.Json;
import ru.aidarkhusainov.parser.model.ali.AliModel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

class CSVFileTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void export() throws IOException {
        CSVFile csvFile = new CSVFile();
        AliTemplate aliTemplate = new AliTemplate();
        HttpClient httpClient = new HttpClient();
        Json json = new Json(httpClient.doGET());
        json.DOFormatToJson();
        AliModel aliModel = json.parseToObj();

        csvFile.export(new File(
                "src/main/resources/test.csv"),
                aliTemplate.getFields(),
                aliModel.getResults());
    }
}