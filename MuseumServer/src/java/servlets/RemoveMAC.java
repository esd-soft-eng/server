package servlets;

import businessDomainObjects.HandsetAccessManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logging.Logger;
import utility.InputValidator;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Servlet file which corresponds to the Remove Handset JSP
 */
@WebServlet(name = "RemoveMAC", urlPatterns = {"/removeDevice"})
public class RemoveMAC extends HttpServlet {

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

        //get the MAC from the paramter, and if it's empty then return an error
        String MAC = request.getParameter("mac");
        if (MAC != null) {
            MAC = InputValidator.clean(MAC);
        }

        if (MAC == null || MAC.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select a MAC address</h2>");
            Redirector.redirect(request, response, "/admin/removeMacAddress.jsp");
            return;
        }


        //get the handsetAccessManager from the servlet context
        HandsetAccessManager ham = (HandsetAccessManager) getServletContext().getAttribute("handsetAccessManager");

        if (ham.removeDevice(MAC)) {
            Logger.Log(Logger.LogType.HANDSETREMOVE, new String[]{MAC, (String) request.getSession().getAttribute("username")});
            request.setAttribute("message", "<h2>Successfully removed MAC address <i>\"" + MAC + "\"</i> from the database.</h2>");
            Redirector.redirect(request, response, "/admin/removeMacAddress.jsp");
            return;
        } else {
            request.setAttribute("message", "<h2 style='color:red'>Failed to remove MAC address <i>\"" + MAC + "\"</i> from the database.</h2>");
            Redirector.redirect(request, response, "/admin/removeMacAddress.jsp");
            return;
        }

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
