package xmlintroduction;

//-----------------------------------------------------------------------------
//Error handling class for handling any erros when parsing the XML documents
//-----------------------------------------------------------------------------

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class myErrorHandler implements ErrorHandler{
    private String getParseExceptionInfo(SAXParseException spe){
        String SystemId = spe.getSystemId();
        String info;
        
        if(SystemId == null){
            SystemId = "null";
        }//end of if block
        
        info = "URI= " + SystemId + " Line= " + spe.getLineNumber() + " :: " + spe.getMessage();
        
        return info;
    }//end of method getParseExceptionInfo()
    
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("WARNING: " + getParseExceptionInfo(exception));
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("ERROR: " + getParseExceptionInfo(exception));
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("FATAL ERROR: " + getParseExceptionInfo(exception));
    }
}//end of class myErrorHandler
