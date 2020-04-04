/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import dao.PhoneDAO;
import dao.SubpageDAO;
import dao.WebsiteDAO;
import dto.Phone;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.StringUtil;

/**
 *
 * @author thien
 */
public class DailyCrawler {

    public static void dailyCrawl(String xslPath) {
        
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               
                    try {
                        Thread.sleep(30*1000);
                        Logger.getLogger("myLog").info("Daily crawling starting...");
                        crawlAll(xslPath);

                    } catch (Exception ex) {
                        Logger.getLogger("myLog").severe(ex.toString());
                    }
                

            }
        });
        thread.start();

    }

    private static void crawlAll(String xslPath) throws SQLException, ClassNotFoundException {
        WebsiteDAO websiteDAO = new WebsiteDAO();
        SubpageDAO subpageDAO = new SubpageDAO();
        PhoneDAO phoneDAO = new PhoneDAO();

        List<String> websites = websiteDAO.getListWebsiteString();
        for (String website : websites) {
            crawlOneWebsite(website, xslPath, subpageDAO, phoneDAO);
        }
        int numOfPhoneDeleted = phoneDAO.deleteOutOfDatePhones();
        Logger.getLogger("myLog").log(Level.INFO, "{0} out of date phone are deleted", numOfPhoneDeleted);

    }

    private static void crawlOneWebsite(String website, String xslPath,
            SubpageDAO subpageDAO, PhoneDAO phoneDAO) {
        String xslFilePath = xslPath + StringUtil.getXslFileNameFromWebsite(website);
        Logger logger = Logger.getLogger("myLog");
        try {
            List<String> subpages = subpageDAO.getListSubpageFrom(website);
            List<Phone> phones = Crawler.crawlPageWithSubpages(website, subpages, xslFilePath);
            phoneDAO.savePhoneList(phones, website);
            int numberOfItemSaved = phoneDAO.savePhoneList(phones, website);
            int numberOfInvalid = phones.size() - numberOfItemSaved;
            String info = "From " + website + "\n Crawl " + phones.size() + " item(s) " + ", save to DB: " + numberOfItemSaved + " item(s), "
                    + numberOfInvalid + " invalid item(s)\n";
            logger.info(info);
        } catch (Exception e) {
            logger.severe(e.toString());
        }
    }
    

    public static void main(String[] args) {
        
    }
}
