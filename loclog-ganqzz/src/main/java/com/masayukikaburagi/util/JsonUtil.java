package com.masayukikaburagi.util;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Map;

public class JsonUtil {

    public static String makeOnePairJson(String key, Object value) {
        return new JSONStringer().object().key(key).value(value).endObject().toString();
    }

    public static String errorsToJson(Map<String, Object> errors) {
        return new JSONObject(errors).toString();
    }

    private JsonUtil() {} // no instantiation
}
