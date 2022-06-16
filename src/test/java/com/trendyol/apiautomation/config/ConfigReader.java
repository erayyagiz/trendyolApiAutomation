package com.trendyol.apiautomation.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ConfigReader {

    public String readJsonFile() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/user.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String json = jsonObject.toString();
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
