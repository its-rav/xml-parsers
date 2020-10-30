/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsersdemo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;
import parsers.DOMParser;
import parsers.sAXParser;

/**
 *
 * @author MSI
 */
public class ParsersDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            String xmlFName="students.xml";
            
            Path p= Paths.get(xmlFName);
            String path=p.toAbsolutePath().toString();
//            DOMParser dp=new DOMParser(path);
//            
//            Node rs=dp.searchFirstStudentLikeFirstName("u");
//            System.out.println("DOM-Search:"+rs.getChildNodes().item(1).getAttributes().getNamedItem("firstname").getNodeValue()+".");
//            
//            boolean result=dp.createStudent(4,"fname","lname",12.0,"password","username");
//            dp.SaveChanges(path.replace(".xml", "1.xml"),true);
//            System.out.println("DOM-Create:"+result);
//            
//            boolean updateResult=dp.updateStudent(2,"fname","lname",12.0,"password","username");
//            dp.SaveChanges(path.replace(".xml", "2.xml"),true);
//            System.out.println("DOM-Update:"+updateResult);
//            
//            boolean delResult=dp.removeStudent(4);
//            dp.SaveChanges(path.replace(".xml", "3.xml"),true);
//            System.out.println("DOM-Delete:"+delResult);
            
            
            sAXParser sax=new sAXParser();
            boolean loginRs=sax.login(path, "nguyenhoa", "456");
            System.out.println(loginRs);
            
        } catch (Exception ex) {
            Logger.getLogger(ParsersDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
