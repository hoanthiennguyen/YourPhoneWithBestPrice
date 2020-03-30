/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import crawler.DailyCrawler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import textprocessor.MyTree;
import textprocessor.NameProcesser;

/**
 * Web application lifecycle listener.
 *
 * @author thien
 */
public class DeployListener implements ServletContextListener {

    public static String REAL_PATH;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        REAL_PATH = sce.getServletContext().getRealPath("/");
        try {
            MyTree myTree = NameProcesser.createSearchTree();
            sce.getServletContext().setAttribute("SEARCH_TREE", myTree);
//            createLogger(REAL_PATH + "assets/log/crawl.log");
//            DailyCrawler.dailyCrawl(REAL_PATH);
        } catch (Exception e) {
            Logger.getLogger("myLog").severe(e.toString());
        }
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    private static void createLogger(String filePathHandler){
        Logger logger = Logger.getLogger("myLog");
        FileHandler fh;

        try {

            fh = new FileHandler(filePathHandler);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
