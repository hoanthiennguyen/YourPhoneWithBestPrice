/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.SubpageDAO;
import dao.WebsiteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import listener.DeployListener;
import util.FileUtil;
import util.StringUtil;

/**
 *
 * @author thien
 */
public class AddNewWebsiteController extends HttpServlet {

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
        PrintWriter pw = response.getWriter();
        WebsiteDAO websiteDAO = new WebsiteDAO();
        SubpageDAO subpageDAO = new SubpageDAO();
        String website = request.getParameter("website");
        String[] subpages = request.getParameter("subpages").split("\n");
        String xsl = request.getParameter("xsl");
        String xslFilePath = DeployListener.XSL_PATH
                + StringUtil.getXslFileNameFromWebsite(website);
        try {
            websiteDAO.insertWebsite(website);
            subpageDAO.insertSubpages(website, subpages);
            FileUtil.writeToFile(xsl, xslFilePath);
            //update session attribute
            HttpSession session = request.getSession();
            session.setAttribute("WEBSITE", websiteDAO.getListWebsiteString());
            session.setAttribute("SUBPAGE", subpageDAO.getListSubpage());
            pw.print("Success");
        } catch (Exception e) {
            pw.print("Fail: " + e.toString());
        } finally {
            pw.flush();
            pw.close();
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
