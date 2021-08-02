package ru.aidarkhusainov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aidarkhusainov.httpclient.HttpClient;
import ru.aidarkhusainov.parser.Json;
import ru.aidarkhusainov.parser.model.ali.AliModel;

import java.io.FileNotFoundException;
import java.io.IOException;


class JsonTest {

//    private String jsonSrc;

    @BeforeEach
    void setUp() throws IOException {
//        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/goods_ali.json"))) {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//
//            while (line != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = br.readLine();
//            }
//
//            String resTemp = sb.toString();
//            int startInd = resTemp.indexOf("(");
//            int endInd = resTemp.lastIndexOf(")");
//
//            jsonSrc = resTemp.substring(startInd + 1, endInd);
//        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parseTo() throws FileNotFoundException {
        HttpClient httpClient = new HttpClient();
        Json json = new Json(httpClient.doGET());
        json.DOFormatToJson();
        AliModel aliModel = json.parseToObj();
    }
}