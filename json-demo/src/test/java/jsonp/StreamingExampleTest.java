package jsonp;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @version 1.0
 */
public class StreamingExampleTest {

    @Test
    public void givenKeyName_shouldReturnKeyStringValue() throws Exception {

        // arrange
        StreamingExample streamingExample = new StreamingExample();

        // act
        String value = streamingExample.retrieveValue("title", Utils.JSON);

        // assert
        assertThat(value).isEqualTo("JSON-Processing With Java EE");
    }

    @Test
    public void givenKeyName_shouldReturnKeyNumberValue() throws Exception {

        // arrange
        StreamingExample streamingExample = new StreamingExample();

        // act
        String value = streamingExample.retrieveValue("length", Utils.JSON);

        // assert
        assertThat(value).isEqualTo("90");
    }

    @Test
    public void givenKeyName_whenKeyIsArray_shouldReturnNullValue() throws Exception {

        // arrange
        StreamingExample streamingExample = new StreamingExample();

        // act
        String value = streamingExample.retrieveValue("chapters", Utils.JSON);

        // assert
        assertThat(value).isNull();
    }

    @Test
    public void givenJsonAsStream_shouldProduceCorrectJsonString() throws Exception {

        // arrange
        StreamingExample streamingExample = new StreamingExample();

        // act
        String jsonString = streamingExample.writeJsonStreamToString();

        // assert
        assertThat(jsonString).isEqualTo(TestData.JSON_STR);
    }
}
