package com.compostage.Data;

import org.json.*;

import java.io.Serializable;

public class JsonParser implements Serializable {

    private JSONObject toParse;

    public JsonParser(String toParse) throws JSONException {
        this.toParse = new JSONObject(toParse);
    }

    public String getField(String fieldName) throws JSONException {
        return toParse.getString(fieldName);
    }

    public boolean fieldNameExists(String fieldName) throws JSONException {

        JSONArray iterate = toParse.names();

        for (int i = 0; i < iterate.length(); i++) {

            if (iterate.get(i).equals(fieldName))
                return true;
        }

        return false;
    }

    public JSONObject getObject(String name) throws JSONException {
        return toParse.getJSONObject(name);
    }

    public JSONArray getArray(String name) throws JSONException {
        return toParse.getJSONArray(name);
    }

    public Integer length() {
        return toParse.length();
    }

}
