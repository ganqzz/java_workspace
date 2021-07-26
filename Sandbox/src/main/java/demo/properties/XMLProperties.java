package demo.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class XMLProperties {
    public static void main(String[] args) {
        try {
            Properties defaultProps = new Properties();
            try (InputStream inputStream =
                         XMLProperties.class.getResourceAsStream("/MyDefaultValues.xml")) {
                defaultProps.loadFromXML(inputStream);
            }
            Properties userProps = new Properties(defaultProps); // Default Property
            displayProperties(userProps);

            System.out.println();

            loadUserProps(userProps);
            displayProperties(userProps);

            if (userProps.getProperty("isFirstRun").equalsIgnoreCase("Y")) {
                userProps.setProperty("welcomeMessage", "Welcome back");
                userProps.setProperty("farewellMessage", "Things will be familiar now");
                userProps.setProperty("isFirstRun", "N");
                saveUserProps(userProps);
            }

        } catch (IOException e) {
            System.out.println(
                    "Exception <" + e.getClass().getSimpleName() + "> " + e.getMessage());
        }
    }

    private static void displayProperties(Properties userProps) {
        String welcomeMessage = userProps.getProperty("welcomeMessage");
        String farewellMessage = userProps.getProperty("farewellMessage");

        System.out.println(welcomeMessage);
        System.out.println(farewellMessage);
    }

    private static void loadUserProps(Properties userProps) throws IOException {
        Path userFile = Paths.get("temp/userValues.xml");
        if (Files.exists(userFile)) {
            try (InputStream inputStream = Files.newInputStream(userFile)) {
                userProps.loadFromXML(inputStream);
            }
        }
    }

    private static void saveUserProps(Properties userProps) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get("temp/userValues.xml"))) {
            userProps.storeToXML(outputStream, null);
        }
    }
}
