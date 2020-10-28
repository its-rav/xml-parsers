/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author MSI
 */
public class XMLUtilities {

    public static void parseFileWithSAX(String filePath, DefaultHandler handler) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sax = spf.newSAXParser();
        File file = new File(filePath);
        sax.parse(file, handler);
    }

    public static Document parseFileToDOM(String filePath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File file = new File(filePath);
        Document doc = db.parse(file);
        return doc;
    }

    public static XPath createXPath() throws Exception {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xPath = xpf.newXPath();
        return xPath;
    }

    public static XMLStreamReader createStAXCursorReaderFormFile(String filePath) throws Exception {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        File f = new File(filePath);
        InputStream is = new FileInputStream(f);
        XMLStreamReader reader = xif.createXMLStreamReader(is);
        return reader;
    }

    public static XMLEventReader createStAXXMLEventReaderFormFile(String filePath) throws Exception {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        File f = new File(filePath);
        InputStream is = new FileInputStream(f);
        XMLEventReader reader = xif.createXMLEventReader(is);
        return reader;
    }
}
