/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author thien
 */
public class DeployListener implements ServletContextListener {

    public static String REAL_PATH;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        REAL_PATH = sce.getServletContext().getRealPath("/") + "assets/xsl/";
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
