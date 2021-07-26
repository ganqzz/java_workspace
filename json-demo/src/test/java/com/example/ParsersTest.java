package com.example;

import com.example.data.ResultData;
import com.example.parsers.GsonJsonParser;
import com.example.parsers.IJsonParser;
import com.example.parsers.JacksonJsonParser;
import com.example.parsers.JsonpJsonParser;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ParsersTest {

    @Test
    public void jsonpParserTest() throws IOException {
        testParserAgainstSample(new JsonpJsonParser());
    }

    @Test
    public void jacksonParserTest() throws IOException {
        testParserAgainstSample(new JacksonJsonParser());
    }

    @Test
    public void gsonParserTest() throws IOException {
        testParserAgainstSample(new GsonJsonParser());
    }

    private void testParserAgainstSample(IJsonParser parser) throws IOException {
        List<ResultData> results;
        try (FileInputStream in =
                     new FileInputStream("./src/main/resources/test_sample.json")) {
            results = parser.parseJson(in);
        }

        // test against local test_sample.json file
        assertNotNull(results);
        assertEquals(2, results.size());

        ResultData result1 = results.get(0);
        assertEquals("java", result1.getTags()[0]);
        assertEquals("urlconnection", result1.getTags()[1]);
        assertEquals(1234, result1.getOwner().getReputation());
        // ...etc.
    }
}
