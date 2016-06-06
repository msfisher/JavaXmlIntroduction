package xmlintroduction;

//-----------------------------------------------------------------------------
//data in some way
//Java provides multiple ways to parse XML documents, here are a few
//(a) DOM parser: parses the document by loading the complete contents of the
//    document and creating its complete hierarchical tree in memory
//(b) SAX parser: parses the document on event-based triggers, does not load
//    the complete document into memory

public class XmlIntroduction {

    public static void main(String[] args) {
        String fileString = "C:\\Users\\msfishe\\Documents\\NetBeansProjects\\XmlIntroduction\\xmlDemo.xml";
        
        myDomParser myXML = new myDomParser(fileString);
        myXML.showDomObject();
        
    }//end of main() method
}//end of class XmlIntroduction
