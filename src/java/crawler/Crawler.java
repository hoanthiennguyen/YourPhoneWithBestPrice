/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import dto.Phone;
import dto.Phones;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBResult;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author thien
 */
public class Crawler {
    public static List<Phone> crawlPageWithSubpages(String website, List<String> subpages, String xslFilePath) throws TransformerException, TransformerConfigurationException, JAXBException, IOException{
        List<Phone> result = new ArrayList<>();
        List<Phone> eachSubpagePhones;
        for(String subpage: subpages){
            eachSubpagePhones = crawlPage(website + subpage, xslFilePath);
            if(eachSubpagePhones != null)
                result.addAll(eachSubpagePhones);
        }
        return result;
    }
    public static List<Phone> crawlPage(String urlString, String xslFilePath) throws TransformerException, TransformerConfigurationException, JAXBException, IOException{
        Phones phones = (Phones) applyXSLAndUnmarshall(urlString, xslFilePath, Phones.class);
        return phones.getList();
    }
    
    
    public static Object applyXSLAndUnmarshall(String urlString, String xslFilePath, Class dtoClass) throws TransformerConfigurationException, TransformerException, JAXBException, IOException{
        Transformer transformer = createTransfromerFactoryWithXSL(xslFilePath);
        Source xmlSource = createXMLSource(urlString);
        JAXBContext jAXBContext = JAXBContext.newInstance(dtoClass);
        JAXBResult jAXBResult = new JAXBResult(jAXBContext);
        transformer.transform(xmlSource, jAXBResult);
        return jAXBResult.getResult();
    }

    private static Source createXMLSource(String urlString) throws IOException {
        String xmlString = htmlutil.HTMLRefiner.getXMLString(urlString);
        Source xml = new StreamSource(new StringReader(xmlString));
        return xml;
    }

    private static Transformer createTransfromerFactoryWithXSL(String xslFilePath) throws TransformerConfigurationException, TransformerFactoryConfigurationError {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xsl = new StreamSource(new File(xslFilePath));
        Transformer transformer = factory.newTransformer(xsl);
        return transformer;
    }
    private static Transformer createTransformerFactoryWithXSLString(String xslStr) throws TransformerConfigurationException{
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xsl = new StreamSource(new StringReader(xslStr));
        Transformer transformer = factory.newTransformer(xsl);
        return transformer;
    }
    public static String applyXSL(String urlString, String xslStr) throws TransformerConfigurationException, IOException, TransformerException{
        Transformer transformer = createTransformerFactoryWithXSLString(xslStr);
        Source xmlSource = createXMLSource(urlString);
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        transformer.transform(xmlSource, result);
        return sw.toString();
    }
    public static void main(String[] args) {
        try {
            List<Phone> list =  crawlPage("https://bachlongmobile.com/dien-thoai.html", "src/java/crawler/bachlong.xsl");
            System.out.println(list.get(0).getName());
        } catch (TransformerException | JAXBException | IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
