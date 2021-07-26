package com.example;

import com.example.data.ResultData;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class StackQueryTest {

    private StackQuery query;

    @Before
    public void setUp() throws Exception {
        query = new StackQuery();
    }

    @Test
    public void testUrl() throws MalformedURLException {
        query.setSearchTerm("Hello World");
        query.setSortBy(StackQuery.SortBy.RELEVANCE);
        query.setSortOrder(StackQuery.SortOrder.ASCENDING);

        URL url = query.buildUrl();
        String goodUrl = "https://api.stackexchange.com/2.2/search?" +
                         "site=stackoverflow&pagesize=5&order=asc&sortrelevance&" +
                         "tagged=java&intitle=Hello%20World";

        assertEquals(goodUrl, url.toString());
    }

    @Test
    public void testParsingWithJsonp() throws IOException {
        testParsing(StackQuery.ParserType.JSONP);
    }

    @Test
    public void testParsingWithJackson() throws IOException {
        testParsing(StackQuery.ParserType.JACKSON);
    }

    @Test
    public void testParsingWithGson() throws IOException {
        testParsing(StackQuery.ParserType.GSON);
    }

    private void testParsing(StackQuery.ParserType type) throws IOException {
        query.setParserType(type);
        query.setSearchTerm("Hello World");
        query.setSortBy(StackQuery.SortBy.RELEVANCE);
        query.setSortOrder(StackQuery.SortOrder.ASCENDING);

        List<ResultData> result = query.execute();
        result.stream().map(ResultData::getTitle).forEach(System.out::println);

        assertNotEquals(0, result.size());
    }
}
