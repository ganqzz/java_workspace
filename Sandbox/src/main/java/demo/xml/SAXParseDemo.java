package demo.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class SAXParseDemo {
    public static void main(String[] args) {
        try {
            // SAXパーサを構成
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser sxp = factory.newSAXParser();
            // MyHandlerをイベントハンドラとするパーサを実行
            // sxp.parse(args[0], new MyHandler());
            sxp.parse("sample.xml", new MyHandler());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyHandler extends DefaultHandler {
    // XMLイベントを処理するハンドラ
    String tag = ""; // XMLの開始エレメントを記録

    public void characters(char[] ch, int start, int length) throws SAXException {
        // 文字列イベント
        if (tag == "name" || tag == "tel" || tag == "email") {
            System.out.print(new String(ch, start, length) + " ");
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) throws
                                                                                          SAXException {
        // 開始エレメントイベント
        tag = qName;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 終了エレメントイベント
        tag = "";
        if (qName == "address") {
            System.out.println("");
        }
    }
}

