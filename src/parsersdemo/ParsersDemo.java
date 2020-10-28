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
import parsers.DOMParser;

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
            DOMParser dp=new DOMParser(path);
            System.out.println(dp.searchStudentLikeFirstName("asd"));
        } catch (Exception ex) {
            Logger.getLogger(ParsersDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
