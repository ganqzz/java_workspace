package com.example.parsers;

import com.example.data.ResultArray;
import com.example.data.ResultData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JacksonJsonParser implements IJsonParser {

    @Override
    public List<ResultData> parseJson(InputStream in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        ResultArray items = mapper.readValue(in, ResultArray.class);
        return items.getItems();
    }
}
