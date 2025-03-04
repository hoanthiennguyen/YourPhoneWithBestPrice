/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import crawler.CrawlUtil;
import dao.PhoneDAO;
import dto.Phone;
import dto.Subpage;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listener.DeployListener;
import textprocessor.MyTree;
import textprocessor.MyTreeUtil;
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
            if(!subpage.equals("*all*")){
                websiteCrawled += subpage;
            }
            else {
                websiteCrawled += "*";
            }
            String xslPath = DeployListener.XSL_PATH
                    + StringUtil.getXslFileNameFromWebsite(website);
            List<Subpage> subpageDTOs = (List<Subpage>) request.getSession().getAttribute("SUBPAGE");
            List<Phone> phones = CrawlUtil.crawlPhones(website, subpage, subpageDTOs, xslPath);
            int numOfSave = dao.savePhoneList(phones, website);
            int numOfCrawl = phones.size();
            MyTree myTree = (MyTree) request.getServletContext().getAttribute("SEARCH_TREE");
            MyTreeUtil.updateSearchTree(myTree);
            request.setAttribute("INFO", StringUtil.createCrawlingInfo(websiteCrawled, 
                    numOfCrawl, numOfSave));
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
