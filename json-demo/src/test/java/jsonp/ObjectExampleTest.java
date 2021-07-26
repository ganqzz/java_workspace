package jsonp;

import org.junit.Test;

import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonValue;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @version 1.0
 */
public class ObjectExampleTest {

    @Test
    public void givenJsonDataInString_readsDataIntoJsonObject() throws Exception {

        // Arrange
        ObjectExample objectExample = new ObjectExample();

        // Act
        JsonObject jsonObject = objectExample.loadJsonString();

        // Assert
        assertThat(jsonObject.getString("title")).isEqualTo("JSON-Processing With Java EE");
        assertThat(jsonObject.getJsonArray("chapters").size()).isEqualTo(6);
        assertThat(jsonObject.getBoolean("released")).isTrue();
        assertThat(jsonObject.getInt("length")).isEqualTo(90);
        assertThat(jsonObject.getJsonObject("sourceCode").getString("repositoryName"))
                .isEqualTo("JSON-Processing-with-Java-EE");
        assertThat(jsonObject.getJsonObject("sourceCode").getString("url"))
                .isEqualTo("github.com/readlearncode");
        assertThat(
                jsonObject.getJsonArray("complementaryCourse").getJsonObject(0).getString("title"))
                .isEqualTo("RESTful Service with JAX-RS 2.0");
        assertThat(jsonObject.getJsonArray("complementaryCourse").getJsonObject(1).getInt("length"))
                .isEqualTo(130);
        assertThat(jsonObject.isNull("notes")).isTrue();
    }

    @Test
    public void givenJsonDateInFlatFile_readsDataIntoJsonObject() throws Exception {

        // Arrange
        ObjectExample objectExample = new ObjectExample();

        // Act
        JsonObject jsonObject = objectExample.loadJsonObject();

        // Assert
        assertThat(jsonObject.getValueType()).isEqualTo(JsonValue.ValueType.OBJECT);
    }

    @Test
    public void givenJsonArrayDataInFlatFile_readsDataIntoJsonStructure() throws Exception {

        // Arrange
        ObjectExample objectExample = new ObjectExample();

        // Act
        JsonStructure jsonStructure = objectExample.loadJsonStructure();

        // Assert
        assertThat(jsonStructure.getValueType()).isEqualTo(JsonValue.ValueType.ARRAY);
    }

    @Test
    public void givenJsonBuiltByBuilder_shouldBuildCorrectly() throws Exception {

        // Arrange
        ObjectExample objectExample = new ObjectExample();

        // Act
        JsonObject jsonObject = objectExample.buildJsonDocument();

        // Assert
        assertThat(jsonObject.getString("title")).isEqualTo("JSON-Processing With Java EE");
        assertThat(jsonObject.getJsonArray("chapters").size()).isEqualTo(6);
        assertThat(jsonObject.getBoolean("released")).isTrue();
        assertThat(jsonObject.getInt("length")).isEqualTo(90);
        assertThat(jsonObject.getJsonObject("sourceCode").getString("repositoryName"))
                .isEqualTo("JSON-Processing-with-Java-EE");
        assertThat(jsonObject.getJsonObject("sourceCode").getString("url"))
                .isEqualTo("github.com/readlearncode");
        assertThat(
                jsonObject.getJsonArray("complementaryCourse").getJsonObject(0).getString("title"))
                .isEqualTo("RESTful Service with JAX-RS 2.0");
        assertThat(jsonObject.getJsonArray("complementaryCourse").getJsonObject(1).getInt("length"))
                .isEqualTo(130);
        assertThat(jsonObject.isNull("notes")).isTrue();
    }

    @Test
    public void givenJsonObject_shouldWriteCorrectlyToString() throws Exception {

        // arrange
        ObjectExample objectExample = new ObjectExample();

        // act
        String jsonString = objectExample.writeJsonStructure();

        // assert
        assertThat(jsonString).isEqualTo(TestData.JSON_STR);
    }
}
