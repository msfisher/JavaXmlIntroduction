package xmlintroduction;

import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//DOM Parser:
// when you parse an XML document with DOM parser you get back a tree structure
// that contains all the elements of your document. The DOM provides a variety
// of methods to examine the contents and structure of the document
// DOM Interface:
//  - Node: the base datatype
//  - Element: the vast majority of the objects you deal with are Elements
//  - Attr: represents an attribute of an element
//  - Text: the actual content of an Element of Attr
//  - Document: represents the entire XML document, a Document object is
//              referred to as a DOM tree
//-----------------------------------------------------------------------------
//Steps to using a DOM parser:
//(1) Import XML-related packages
//(2) Create a DocumentBuilder
//(3) Create a Document from a file or a stream
//(4) Extract the root element
//(5) Examine attributes
//(6) Examine sub-elements

public class myDomParser {
    
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document doc;
    
    public myDomParser(String inputFile){
        //Create DocumentBuilder by using DocumentBuilderFactory
        if(!init(inputFile)){
            System.out.println("Could not create myDomParser object!");
        }
    }//end of constructor myDomParser()
    
    //creates DocumentBuilder used for parsing the XML documents and creates
    //the DOM document object by parsing the XML file passed
    private boolean init(String inputFile){
        boolean returnStatus = true;        //variable used to return status
        File xmlFile = new File(inputFile); //the XML file to be read
        
        try{
            //obtain new instance of a DocumentBuilderFactory
            factory = DocumentBuilderFactory.newInstance();
            
            //create new instance of a DocumentBuilder using the configured params
            //By default the factory returns a non-validating parser that knows
            //nothing about namespaces
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            builder = factory.newDocumentBuilder();
            
            //set the builder's error handler (what to do when parsing an XML
            //document experiences errors
            builder.setErrorHandler(new myErrorHandler());
            
            //create a Document from file or stream - parse the contents of the 
            //file as an XML document and return a new DOM Document object
            doc = builder.parse(xmlFile);
        }//end of try block
        catch(ParserConfigurationException ex){
            //creating new DocumentBuilder can throw this exception if a
            //DocumentBuilder cannot be created that satisfies the configuration requested
            returnStatus = false;
            System.out.println("Error factory.newDocumentBuilder(): " + ex.getMessage());
        }//end of catch(ParserConfigurationException ex)
        catch(FactoryConfigurationError ex){
            //Using the DocumentBuilderFactory.newInstance() can throw this 
            //exception.  It looks for the DocumentBuilderFactory 
            //implementation class to load using this order:
            //(1) use the javax.xml.parsers.DocumentBuilderFactory property
            //(2) use the properties file "lib/jaxp.properties" in JRE directory
            //(3) use the services API as detailed in JAR specification
            //(4) Platform default DocumentBuilderFactory instance
            //*** to test this you have to figure out how to change the rt.jar file
            returnStatus = false;
            System.out.println("Error DocumentBuilderFactory.newInstance(): " + ex.getMessage());
        }//end of catch(FactoryConfigurationError ex)
        catch(IllegalArgumentException ex){
            //exeption occurs if builder.parse(File f) if File F is null
            //*** tested by setting the argument equal to NULL
            returnStatus = false;
            System.out.println("Error builder.parse(xmlFile) - argument is null: " + ex.getMessage());
        }//end of catch(IllegalArgumentException ex)
        catch(IOException ex){
            //exception occurs in builder.parse(File f) if any I/O errors occur
            //*** tested via not having the file present
            returnStatus = false;
            System.out.println("Error builder.parse(xmlFile) - IO error: " + ex.getMessage());
        }//end of catch(IOException ex)
        catch(SAXException ex){
            //exception occurs in builder.parse(File f) if any parse errors occur
            //*** several Parsing errors can be tested by changing the XML file
            returnStatus = false;
            System.out.println("Error builder.parse(xmlFile) - Parsing error: " + ex.getMessage());
        }//end of catch(SAXException ex)
        
        return returnStatus;
    }//end of method init()
    
    public void showDomObject(){
        if(null != doc){
            //Process the DOM object and display its contents
            System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("---------------------------------------------");
            
            //loop through the list of elements
            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                System.out.println("Current Element: " + nNode.getNodeName());
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no : " + eElement.getAttribute("rollno"));
                    System.out.println("First Name : "      + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : "       + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : "       + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Marks : "           + eElement.getElementsByTagName("marks").item(0).getTextContent());
                    System.out.println();
                }//end of if(nNode.getNodeType() == Node.ELEMENT_NODE) block
            }//end of for loop - looping through the elements
        }//end of if(null != doc) block
        else{
            System.out.println("There was an error during initialization");
        }//end of else block
    }//end of showDomObject() method
}//end of class myDomParser
