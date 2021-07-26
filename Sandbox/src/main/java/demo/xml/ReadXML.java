package demo.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ReadXML {

    // public static final String URL_STRING =
    // "http://services.explorecalifornia.org/rss/tours.php";
    public static final String URL_STRING = "./files/tours_rss.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL_STRING);

            NodeList list = doc.getElementsByTagName("title");
            System.out.println(list.getLength());

            for (int i = 0; i < list.getLength(); i++) {
                Element item = (Element) list.item(i);
                System.out.println(item.getFirstChild().getNodeValue());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
