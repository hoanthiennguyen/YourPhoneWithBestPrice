/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import dto.Phone;
import dto.Subpage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author thien
 */
public class CrawlUtil {
    public static List<Phone> crawlPhones(String website, String subpage,
            List<Subpage> subpageDTOs, String xslPath) throws TransformerException, TransformerConfigurationException, JAXBException, IOException{
        List<Phone> phones;
        String websiteCrawled = website;
            if (subpage.equals("*all*")) {
                List<String> subpageStrs = subpageDTOs.stream()
                        .filter(dto -> dto.getWebsite().equals(website))
                        .map(dto -> dto.getSubpage())
                        .collect(Collectors.toList());
                phones = Crawler.crawlPageWithSubpages(website, subpageStrs, xslPath);
            } else {
                websiteCrawled += subpage;
                phones = Crawler.crawlPage(websiteCrawled, xslPath);
            }
        return phones;
    }
}
