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
public class MyContextServletListener implements ServletContextListener {

    private final static String HOANGHA = "WEB-INF/hoangha.xsl";
    private final static String BACHLONG = "WEB-INF/bachlong.xsl";
    public static String HOANGHA_XSL_PATH;
    public static String BACHLONG_XSL_PATH;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //get real absolute path, combine with XML_File and parse the file

        HOANGHA_XSL_PATH = sce.getServletContext().getRealPath("/") + HOANGHA;
        BACHLONG_XSL_PATH = sce.getServletContext().getRealPath("/") + BACHLONG;

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
