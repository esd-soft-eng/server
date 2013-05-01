package servlets;

import businessDomainObjects.Exhibit;
import businessDomainObjects.ExhibitManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logging.Logger;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Servlet file which corresponds to the Remove Exhibit JSP
 */
@WebServlet(name = "RemoveExhibit", urlPatterns = {"/removeExhibit.do"})
public class RemoveExhibit extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String exhibitID = request.getParameter("exhibitID");
        if (exhibitID == null || exhibitID == "") {
            request.setAttribute("message", "<h2 style='color:red;'>No exhibit was selected</h2>");
            Redirector.redirect(request, response, "/admin/removeExhibit.jsp");
            return;
        }
        int exhibitIDInt = -1;
        try {
            exhibitIDInt = Integer.parseInt(exhibitID);
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "<h2 style='color:red;'>Exhibit ID was invalid</h2>");
            Redirector.redirect(request, response, "/admin/removeExhibit.jsp");
            return;
        }
        ExhibitManager manager = (ExhibitManager) getServletContext().getAttribute("exhibitManager");
        Exhibit exhibitToRemove = manager.getExhibitByID(exhibitIDInt);
        if (!manager.removeExhibit(exhibitIDInt)) {
            request.setAttribute("message", "<h2 style='color:red;'>Failed to remove exhibit.</h2>");
            Redirector.redirect(request, response, "/admin/removeExhibit.jsp");
            return;
        }
        
        Logger.Log(Logger.LogType.EXHIBITREMOVE, new String[]{String.valueOf(exhibitIDInt), exhibitToRemove.getName(), (String) request.getSession().getAttribute("username")});
        request.setAttribute("message", "<h2>Successfully removed exhibit.</h2>");
        Redirector.redirect(request, response, "/admin/removeExhibit.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
