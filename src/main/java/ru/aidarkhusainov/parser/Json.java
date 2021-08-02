package ru.aidarkhusainov.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.aidarkhusainov.parser.model.ali.AliModel;


public class Json {
    private String json;

    public Json(String json) {
        this.json = json;
    }

    public AliModel parseToObj() {

        System.out.println(json);

        Gson gson = new GsonBuilder().create();
        AliModel aliModel = gson.fromJson(json, AliModel.class);

        return aliModel;
    }

    public void DOFormatToJson(){
        int startInd = json.indexOf("(");
        int endInd = json.lastIndexOf(")");

        json = json.substring(startInd + 1, endInd);
    }
}
