package demo.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class DOMParseDemo {
    public static void main(String[] args) {
        int i, j;
        try {
            // DOMパーサを構成
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Document doc = builder.parse(args[0]);
            Document doc = builder.parse("sample.xml");

            // addressエレメントのあるノードを取得
            NodeList nl = doc.getElementsByTagName("address");
            for (i = 0; i < nl.getLength(); i++) {
                NodeList nl2 = nl.item(i).getChildNodes();
                for (j = 0; j < nl2.getLength(); j++) {
                    // 各エレメントの中にあるテキストを表示
                    if (nl2.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        System.out.print(nl2.item(j).getTextContent() + " ");
                    }
                }
                System.out.println("");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

