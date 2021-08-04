package ru.aidarkhusainov.httpclient;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicStatusLine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализует отправку REST-запросов
 */
public class HttpClient implements CustomHttpReq {

    private String hostName;
    private Charset charsetBody;
    private Map<String, String> requestHeaders = new HashMap<>();
    private String requestBody;
    private HttpGet httpget;

    public HttpClient(String hostName, Charset charsetBody, Map<String, String> requestHeaders) {
        this.hostName = hostName;
        this.charsetBody = charsetBody;
        this.requestHeaders = requestHeaders;

        initGetRequest();
        initHeaders();
    }

    private void initGetRequest() {
        RequestConfig requestConfig = RequestConfig.custom().build();

        httpget = new HttpGet(hostName);
        httpget.setConfig(requestConfig);
    }

    private void initHeaders() {
        for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
            httpget.addHeader(header.getKey(), header.getValue());
        }
    }

    @Override
    public CustomHttpResponse sendGetRequest() {
        return sendReq(httpget);
    }

    private <T> CustomHttpResponse sendReq(T request) {
        CustomHttpResponse response;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse resp = httpClient.execute((HttpUriRequest) request);
             ByteArrayOutputStream byteOutput = new ByteArrayOutputStream()) {

            HttpEntity entity = resp.getEntity();

            response = new CustomHttpResponse(
                    getResponseHeaders(resp),
                    getResponseBody(entity, byteOutput),
                    getResponseStatusCode(resp),
                    getResponseStatusLine(resp));

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return response;
    }


    private Map<String, String> getResponseHeaders(HttpResponse response) {
        Map<String, String> resHeaders = new HashMap<>();
        for (Header header : response.getAllHeaders()) {
            resHeaders.put(header.getName(), header.getValue());
        }
        return resHeaders;
    }

    private byte[] getResponseBody(HttpEntity entity, ByteArrayOutputStream byteOutput) throws IOException {
        entity.writeTo(byteOutput);
        return byteOutput.toByteArray();
    }

    private int getResponseStatusCode(HttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }

    private StatusLine getResponseStatusLine(HttpResponse response) {
        StatusLine statusLine = response.getStatusLine();
        return new BasicStatusLine(statusLine.getProtocolVersion(), statusLine.getStatusCode(), statusLine.getReasonPhrase());
    }
}

//
//public class HttpClient {
//    public String doGET() {
//        return "/**/jQuery183040787687429232045_1627752070824({\n" +
//                "    \"contextId\": \"5547572_ru_RU_RU_RUB_pc_current\",\n" +
//                "    \"success\": true,\n" +
//                "    \"code\": 0,\n" +
//                "    \"results\": [\n" +
//                "        {\n" +
//                "            \"productId\": 32946137246,\n" +
//                "            \"sellerId\": 230089227,\n" +
//                "            \"oriMinPrice\": \"103,05 руб.\",\n" +
//                "            \"oriMaxPrice\": \"103,05 руб.\",\n" +
//                "            \"promotionId\": 1910318460,\n" +
//                "            \"startTime\": 1627282800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"Воздушный шар в виде пера 18 дюймов с бумажной звездой, гирлянда, украшения для свадьбы, дня рождения, вечеринки, подарки, товары для вечерние,...\",\n" +
//                "            \"minPrice\": \"66,20 руб.\",\n" +
//                "            \"maxPrice\": \"103,05 руб.\",\n" +
//                "            \"discount\": \"36\",\n" +
//                "            \"orders\": \"247\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/HTB1utmcXyDxK1RjSsD4q6z1DFXao.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/32946137246.html?pdp_ext_f=%7B%22sku_id%22:%2210000015721556511%22,%22ship_from%22:%22%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/3655045?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"247\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.8\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 33029190360,\n" +
//                "            \"sellerId\": 238767905,\n" +
//                "            \"oriMinPrice\": \"376,86 руб.\",\n" +
//                "            \"oriMaxPrice\": \"376,86 руб.\",\n" +
//                "            \"promotionId\": 1910318460,\n" +
//                "            \"startTime\": 1627282800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"Стоящий хлопковый Эластичный Напульсник для баскетбола, спортивная повязка на голову для женщин и мужчин, для тренажерного зала, фитнеса, п...\",\n" +
//                "            \"minPrice\": \"211,37 руб.\",\n" +
//                "            \"maxPrice\": \"376,86 руб.\",\n" +
//                "            \"discount\": \"44\",\n" +
//                "            \"orders\": \"113\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/Hb89d420d069347fbb62e987aa0e0cc29k.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/33029190360.html?pdp_ext_f=%7B%22sku_id%22:%2267212199415%22,%22ship_from%22:%22%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/5045266?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"113\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.5\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 4000323004858,\n" +
//                "            \"sellerId\": 234532934,\n" +
//                "            \"oriMinPrice\": \"273,81 руб.\",\n" +
//                "            \"oriMaxPrice\": \"273,81 руб.\",\n" +
//                "            \"promotionId\": 1910318460,\n" +
//                "            \"startTime\": 1627282800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"5 шт./лот, украшения из нержавеющей стали в виде листа для полировки, серьги ручной работы в богемном стиле для изготовления ювелирных украше...\",\n" +
//                "            \"minPrice\": \"183,54 руб.\",\n" +
//                "            \"maxPrice\": \"273,81 руб.\",\n" +
//                "            \"discount\": \"33\",\n" +
//                "            \"orders\": \"338\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/Hef05d6ee55d34237b807d0f7e1656a3dH.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/4000323004858.html?pdp_ext_f=%7B%22sku_id%22:%2210000001325404369%22,%22ship_from%22:%22%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/4461023?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"338\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.9\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 4001244724881,\n" +
//                "            \"sellerId\": 240320499,\n" +
//                "            \"oriMinPrice\": \"831,21 руб.\",\n" +
//                "            \"oriMaxPrice\": \"831,21 руб.\",\n" +
//                "            \"promotionId\": 1910318460,\n" +
//                "            \"startTime\": 1627282800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"Женские повседневные модные туфли на плоской подошве, на пуговицах, Размеры 35-42\",\n" +
//                "            \"minPrice\": \"640,14 руб.\",\n" +
//                "            \"maxPrice\": \"831,21 руб.\",\n" +
//                "            \"discount\": \"23\",\n" +
//                "            \"orders\": \"77\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/H25b89d5217a147699b3398aee8727337t.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/4001244724881.html?pdp_ext_f=%7B%22sku_id%22:%2212000018328026157%22,%22ship_from%22:%22CN%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/5615056?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"77\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.9\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 1005001673842405,\n" +
//                "            \"sellerId\": 234532934,\n" +
//                "            \"oriMinPrice\": \"182,79 руб.\",\n" +
//                "            \"oriMaxPrice\": \"182,79 руб.\",\n" +
//                "            \"promotionId\": 1910318460,\n" +
//                "            \"startTime\": 1627282800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"Круглые Подвески неправильной формы из нержавеющей стали, 10 шт./лот, для соединения сережек «сделай сам», богемные украшения\",\n" +
//                "            \"minPrice\": \"144,43 руб.\",\n" +
//                "            \"maxPrice\": \"182,79 руб.\",\n" +
//                "            \"discount\": \"21\",\n" +
//                "            \"orders\": \"53\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/Hb61078ba7a70406687a63cd756a70cf9W.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/1005001673842405.html?pdp_ext_f=%7B%22sku_id%22:%2212000017077560005%22,%22ship_from%22:%22%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/4461023?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"53\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.9\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 1005002002974318,\n" +
//                "            \"sellerId\": 248749998,\n" +
//                "            \"oriMinPrice\": \"452,84 руб.\",\n" +
//                "            \"oriMaxPrice\": \"452,84 руб.\",\n" +
//                "            \"promotionId\": 1910318460,\n" +
//                "            \"startTime\": 1627282800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"200 шт./кор. Disney наклейки съемный из мультфильма «Холодное сердце» с Микки-Маусом София наклейка принцесса дети девочки учитель награда игруш...\",\n" +
//                "            \"minPrice\": \"212,88 руб.\",\n" +
//                "            \"maxPrice\": \"452,84 руб.\",\n" +
//                "            \"discount\": \"53\",\n" +
//                "            \"orders\": \"512\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/H7c61287c250748b28a8ba3ab871873edK.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/1005002002974318.html?pdp_ext_f=%7B%22sku_id%22:%2212000018376668621%22,%22ship_from%22:%22CN%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/911305080?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"512\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.8\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 1005002273882001,\n" +
//                "            \"sellerId\": 234532934,\n" +
//                "            \"oriMinPrice\": \"303,15 руб.\",\n" +
//                "            \"oriMaxPrice\": \"303,15 руб.\",\n" +
//                "            \"promotionId\": 1910318460,\n" +
//                "            \"startTime\": 1627282800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"Круглые Серьги-капельки в форме подсолнуха из нержавеющей стали, 10 шт., гипоаллергенные серьги, фурнитура для изготовления ювелирных издели...\",\n" +
//                "            \"minPrice\": \"227,17 руб.\",\n" +
//                "            \"maxPrice\": \"303,15 руб.\",\n" +
//                "            \"discount\": \"25\",\n" +
//                "            \"orders\": \"60\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/Hb2775b1d93d146c2933954a6ece7b8660.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/1005002273882001.html?pdp_ext_f=%7B%22sku_id%22:%2212000019865264910%22,%22ship_from%22:%22%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/4461023?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"60\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.9\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 1005002701833347,\n" +
//                "            \"sellerId\": 240367829,\n" +
//                "            \"oriMinPrice\": \"183,54 руб.\",\n" +
//                "            \"oriMaxPrice\": \"183,54 руб.\",\n" +
//                "            \"promotionId\": 1910318460,\n" +
//                "            \"startTime\": 1627282800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"носки носки женские женские носки гольфы для девочек носки белые носки коротНоски женские короткие носки забавные носки женские невидимые ...\",\n" +
//                "            \"minPrice\": \"86,51 руб.\",\n" +
//                "            \"maxPrice\": \"183,54 руб.\",\n" +
//                "            \"discount\": \"53\",\n" +
//                "            \"orders\": \"154\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/Hf8261dbb73574f61a7b2757d8fc17fd7x.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/1005002701833347.html?pdp_ext_f=%7B%22sku_id%22:%2212000022884487848%22,%22ship_from%22:%22CN%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/5621259?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"154\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.9\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 4000539506338,\n" +
//                "            \"sellerId\": 232956230,\n" +
//                "            \"oriMinPrice\": \"1 471,35 руб.\",\n" +
//                "            \"oriMaxPrice\": \"1 471,35 руб.\",\n" +
//                "            \"promotionId\": 1910319663,\n" +
//                "            \"startTime\": 1627628400,\n" +
//                "            \"endTime\": 1627801199,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"TWS-стереонаушники с зарядным футляром и поддержкой Bluetooth 5,0, 2200 мА · ч\",\n" +
//                "            \"minPrice\": \"485,18 руб.\",\n" +
//                "            \"maxPrice\": \"1 471,35 руб.\",\n" +
//                "            \"discount\": \"67\",\n" +
//                "            \"orders\": \"120644\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/Ha9aac78e8d0549bb9c4d4fb805ba7ec49.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/4000539506338.html?pdp_ext_f=%7B%22sku_id%22:%2212000016406259996%22,%22ship_from%22:%22CN%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/3891048?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"120644\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.7\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 32931282121,\n" +
//                "            \"sellerId\": 232981631,\n" +
//                "            \"oriMinPrice\": \"9 855,64 руб.\",\n" +
//                "            \"oriMaxPrice\": \"9 855,64 руб.\",\n" +
//                "            \"promotionId\": 1910404181,\n" +
//                "            \"startTime\": 1627714800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"UHWOW650 Видеорегистратор 70mai A500S Pro Plus + 1944P GPS ADAS, Автомобильный видеорегистратор с двойным обзором, видеорегистратор 70mai Pro Plus + A500S, Автомобильны...\",\n" +
//                "            \"minPrice\": \"5 124,90 руб.\",\n" +
//                "            \"maxPrice\": \"9 855,64 руб.\",\n" +
//                "            \"discount\": \"48\",\n" +
//                "            \"orders\": \"15110\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/H1f6db415216c4642ad4e2fa66e27ebb2L.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/32931282121.html?pdp_ext_f=%7B%22sku_id%22:%2212000022669151018%22,%22ship_from%22:%22RU%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/4257014?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"15110\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.9\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 4001211668637,\n" +
//                "            \"sellerId\": 225634490,\n" +
//                "            \"oriMinPrice\": \"3 759,62 руб.\",\n" +
//                "            \"oriMaxPrice\": \"3 759,62 руб.\",\n" +
//                "            \"promotionId\": 1910115477,\n" +
//                "            \"startTime\": 1627542000,\n" +
//                "            \"endTime\": 1627973999,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"Устройство для ухода за кожей InFace, инструмент для ухода за лицом, массажер для лица, ионное средство для удаления морщин, мезотерапия для лиц...\",\n" +
//                "            \"minPrice\": \"2 030,25 руб.\",\n" +
//                "            \"maxPrice\": \"3 759,62 руб.\",\n" +
//                "            \"discount\": \"46\",\n" +
//                "            \"orders\": \"1297\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/Haa08a7860e5f492080122b7c2035d0ccJ.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/4001211668637.html?pdp_ext_f=%7B%22sku_id%22:%2212000022978902286%22,%22ship_from%22:%22PL%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/1924412?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"1297\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.8\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"productId\": 4000237954145,\n" +
//                "            \"sellerId\": 228486479,\n" +
//                "            \"oriMinPrice\": \"5 467,92 руб.\",\n" +
//                "            \"oriMaxPrice\": \"5 467,92 руб.\",\n" +
//                "            \"promotionId\": 1910314689,\n" +
//                "            \"startTime\": 1627714800,\n" +
//                "            \"endTime\": 1627887599,\n" +
//                "            \"phase\": 1,\n" +
//                "            \"productTitle\": \"Baseus 65 Вт Ган зарядное устройство Quick Charge 4,0 3,0 Тип C PD USB зарядное устройство с технологией QC 4,0 3,0 Портативный быстрое зарядное устройство для н...\",\n" +
//                "            \"minPrice\": \"1 530,78 руб.\",\n" +
//                "            \"maxPrice\": \"5 467,92 руб.\",\n" +
//                "            \"discount\": \"72\",\n" +
//                "            \"orders\": \"14900\",\n" +
//                "            \"productImage\": \"//ae01.alicdn.com/kf/H2e1d7df9309f4b3aa56225b4b9868f69V.jpg_350x350.jpg\",\n" +
//                "            \"productDetailUrl\": \"//aliexpress.ru/item/4000237954145.html?pdp_ext_f=%7B%22sku_id%22:%2212000019998521717%22,%22ship_from%22:%22CN%22%7D&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"shopUrl\": \"//aliexpress.ru/store/2343184?t_activity=1&t_cache=1&gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\",\n" +
//                "            \"trace\": \"{\\\"all\\\":{\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"},\\\"pvid\\\":\\\"d090dcff-4797-4e5a-a5a2-b34349502b88\\\",\\\"scm-cnt\\\":\\\"1007.19201.130907.0\\\",\\\"gps-id\\\":\\\"5547572\\\"}\",\n" +
//                "            \"totalTranpro3\": \"14900\",\n" +
//                "            \"productPositiveRate\": \"0.0\",\n" +
//                "            \"productAverageStar\": \"4.9\",\n" +
//                "            \"itemEvalTotalNum\": 0\n" +
//                "        }\n" +
//                "    ],\n" +
//                "    \"finished\": false,\n" +
//                "    \"page\": 1,\n" +
//                "    \"pageSize\": 12,\n" +
//                "    \"postback\": \"7e39e2d9-b5a3-4ec7-a00c-7a1be182a640\",\n" +
//                "    \"pin\": \"gps-id=5547572&scm=1007.19201.130907.0&scm_id=1007.19201.130907.0&scm-url=1007.19201.130907.0&pvid=d090dcff-4797-4e5a-a5a2-b34349502b88\"\n" +
//                "});";
//    }
//}
