/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import java.util.List;
import org.w3c.dom.Document;
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
        this.document=XMLUtilities.parseFileToDOM(filePath);
    }

    public void setDocument(String filePath) throws Exception {
        this.document=XMLUtilities.parseFileToDOM(filePath);
    }

    public Document getDocument() {
        return document;
    }
    public String searchStudentLikeFirstName(String fName){
        NodeList list=this.document.getElementsByTagName("student");
        //students
        for(int index =0;index<list.getLength();index++){
            //children of student
            NodeList childNodes=list.item(index).getChildNodes();
            for(int i =0;i<childNodes.getLength();i++){
                Node item=childNodes.item(i);
                if(true/*item.getNodeType()!=Node.TEXT_NODE*/ ){
                    System.out.println(item.getNodeName()+"\n"+item.getTextContent()+"\n"+item.getNodeValue()+"------");
                    
                }
                else
                    System.out.println("-");
            }
        }
        return "";
    }
    
}
