/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import utils.XMLUtilities;

/**
 *
 * @author MSI
 */
public class sAXParser {
    
    public boolean login(String filePath,String username,String password) throws Exception{
        StudentHandler studentObj=new StudentHandler(username,password);
        XMLUtilities.parseFileWithSAX(filePath, studentObj);
        return studentObj.isFound();
    }
    
    public class StudentHandler extends DefaultHandler{
        private String username;
        private String password;
        private String currentTagName;
        private boolean isFound=false;
        
        private boolean foundUsernameTag=false,foundPasswordTag=false;
        public StudentHandler(String username, String password) {
            this.username = username;
            this.password = password;
        }
        @Override
        public void characters(char[] chars, int start, int length) throws SAXException {
            super.characters(chars, start, length); //To change body of generated methods, choose Tools | Templates.
            String str=new String(chars,start,length);
            if(!isFound){
                if(this.currentTagName!=""){
                    if (this.currentTagName.equals("username")) {
                        if (str.trim().equals(this.username)) {
                            this.foundUsernameTag = true;
                        }else{
                            this.foundUsernameTag = false;
                        }
                    } else if (this.currentTagName.equals("password")) {
                        if (this.foundUsernameTag==true&&str.trim().equals(password)) {
                            this.isFound=true;
                        }
                    }
                }
            }
        }
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes); 
            this.currentTagName=qName;
        }   
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
            this.currentTagName="";
        }
        
        private boolean isFound() {
            return this.isFound;
        }
        
    }
}
