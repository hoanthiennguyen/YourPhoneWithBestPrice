/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import crawler.Crawler;
import dao.PhoneDAO;
import dto.Phone;
import dto.Subpage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listener.DeployListener;
import util.StringUtil;

/**
 *
 * @author thien
 */
public class CrawlController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PhoneDAO dao = new PhoneDAO();
        String url = "error.jsp";
        try {
            String website = request.getParameter("website");
            String subpage = request.getParameter("subpage");
            String websiteCrawled = website;
            String xslPath = DeployListener.REAL_PATH + "assets/xsl/"
                    + StringUtil.getXslFileNameFromWebsite(website);
            List<Phone> phones;
            if (subpage.equals("*all*")) {
                List<Subpage> subpageDTOs = (List<Subpage>) request.getSession().getAttribute("SUBPAGE");
                List<String> subpageStrs = subpageDTOs.stream()
                        .filter(dto -> dto.getWebsite().equals(website))
                        .map(dto -> dto.getSubpage())
                        .collect(Collectors.toList());
                phones = Crawler.crawlPageWithSubpages(website, subpageStrs, xslPath);
            } else {
                websiteCrawled += subpage;
                phones = Crawler.crawlPage(websiteCrawled, xslPath);
            }
            String info;
            int numberOfItemSaved = dao.savePhoneList(phones, website);
            int numberOfInvalid = phones.size() - numberOfItemSaved;
            info = "From " + websiteCrawled + "<br/> Crawl " + phones.size() + " item(s) " + ", save to DB: " + numberOfItemSaved + " item(s), "
                    + numberOfInvalid + " invalid item(s)";

            request.setAttribute("INFO", info);
            url = "admin.jsp";
        } catch (Exception e) {
            request.setAttribute("ERROR", e.toString());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
