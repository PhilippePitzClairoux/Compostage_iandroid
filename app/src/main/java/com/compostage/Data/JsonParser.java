package com.compostage.Data;

import org.json.*;

public class JsonParser {

    private JSONObject toParse;

    public JsonParser(String toParse) throws JSONException {
        this.toParse = new JSONObject(toParse);
    }

    public String getField(String fieldName) throws JSONException {
        return toParse.getString(fieldName);
    }

}
