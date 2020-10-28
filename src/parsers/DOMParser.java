/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import java.io.File;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.XMLUtilities;

/**
 *
 * @author MSI
 */
public class DOMParser {

    private Document document;

    public DOMParser(String filePath) throws Exception {
        this.document = XMLUtilities.parseFileToDOM(filePath);
    }

    public void setDocument(String filePath) throws Exception {
        this.document = XMLUtilities.parseFileToDOM(filePath);
    }

    public Document getDocument() {
        return document;
    }

    public Node searchFirstStudentLikeFirstName(String fName) {
        NodeList list = this.document.getElementsByTagName("student");
        //students
        for (int index = 0; index < list.getLength(); index++) {
            //children of student
            NodeList childNodes = list.item(index).getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item.getNodeType() != Node.TEXT_NODE) {
                    if (item.getNodeName() == "name") {
                        String firstName = item.getAttributes().getNamedItem("firstname").getNodeValue();
                        if (firstName.contains(fName)) {
                            return list.item(index);
                        }
                    }
                }
            }
        }

        return null;
    }

    public Node findStudentById(int id) {
        NodeList list = this.document.getElementsByTagName("student");
        for (int index = 0; index < list.getLength(); index++) {
            //children of student
            NodeList childNodes = list.item(index).getChildNodes();
            Node idNode = list.item(index).getAttributes().getNamedItem("id");
            if (idNode == null) {
                continue;
            }
            if (idNode.getTextContent().trim().equals("" + id)) {
                return list.item(index);
            }
        }
        return null;
    }

    public boolean createStudent(int id, String fname, String lname, double mark, String password, String username) {
        if (findStudentById(id) != null) {
            return false;
        }
        Element newStudentNode = this.document.createElement("student");
        newStudentNode.setAttribute("id", "" + id);

        Element nameNode = this.document.createElement("name");
        nameNode.setAttribute("firstname", fname);
        nameNode.setAttribute("lastname", lname);
        Element markNode = this.document.createElement("mark");
        markNode.setTextContent("" + mark);
        Element passwordNode = this.document.createElement("password");
        passwordNode.setTextContent(password);
        Element usernameNode = this.document.createElement("username");
        usernameNode.setTextContent(username);

        newStudentNode.appendChild(nameNode);
        newStudentNode.appendChild(markNode);
        newStudentNode.appendChild(passwordNode);
        newStudentNode.appendChild(usernameNode);

        this.document.getFirstChild().appendChild(newStudentNode);

        return true;
    }

    public void SaveChanges(String filePath) throws TransformerConfigurationException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        DOMSource ds = new DOMSource(this.document);
        File file = new File(filePath);
        StreamResult sr = new StreamResult(file);
        trans.transform(ds, sr);
    }

    public boolean removeStudent(int id) {
        NodeList studentNodes = this.document.getElementsByTagName("student");
        for (int i = 0; i < studentNodes.getLength(); i++) {
            Node studentNode = studentNodes.item(i);
            Node nodeId = studentNode.getAttributes().getNamedItem("id");
            if (nodeId != null && nodeId.getNodeValue().trim().equals("" + id)) {
                //this.document.getChildNodes().item(0) class>student
                this.document.getChildNodes().item(0).removeChild(studentNode);
                return true;
            }
        }
        return false;
    }

}
