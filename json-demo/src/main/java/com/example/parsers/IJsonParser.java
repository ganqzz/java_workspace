package com.example.parsers;

import com.example.data.ResultData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IJsonParser {
    List<ResultData> parseJson(InputStream in) throws IOException;
}
