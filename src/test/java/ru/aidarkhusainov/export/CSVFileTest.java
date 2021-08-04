package ru.aidarkhusainov.export;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aidarkhusainov.export.model.goods.AliTemplate;
import ru.aidarkhusainov.httpclient.HttpClient;
import ru.aidarkhusainov.parser.Json;
import ru.aidarkhusainov.parser.model.ali.AliModel;
import ru.aidarkhusainov.parser.model.ali.Result;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CSVFileTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void export() throws IOException {
        AliTemplate aliTemplate = new AliTemplate();
        HttpClient httpClient = new HttpClient();
        Json json = new Json(httpClient.doGET());
        json.DOFormatToJson();
        Object aliModel = json.parseToObj(AliModel.class);

        List<List<String>> aliRecords = new ArrayList<>();

        for (Result r : ((AliModel) aliModel).getResults()) {
            aliRecords.add(Arrays.asList(
                    r.getProductDetailUrl(),
                    r.getDiscount(),
                    r.getMaxPrice(),
                    r.getMinPrice(),
                    r.getOriginalPrice(),
                    r.getProductAvgStar(),
                    r.getProductId(),
                    r.getProductImage(),
                    r.getProductTitle(),
                    r.getSellerId()
            ));

        }

        CSVFile csvFile = new CSVFile(
                new File("./sample.csv"),
                aliTemplate.getFields(),
                aliRecords,
                "###");

        csvFile.export();
    }
}