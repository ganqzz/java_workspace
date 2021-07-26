package com.example.parsers;

import com.example.data.ResultArray;
import com.example.data.ResultData;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class GsonJsonParser implements IJsonParser {

    @Override
    public List<ResultData> parseJson(InputStream in) {
        ResultArray items = new Gson().fromJson(new InputStreamReader(in), ResultArray.class);
        return items.getItems();
    }
}
