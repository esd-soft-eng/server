package servlets;

import businessDomainObjects.RouterManager;
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
 */
@WebServlet(name = "RemoveRouter", urlPatterns = {"/removeRouter.do"})
public class RemoveRouter extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String routerMAC = request.getParameter("routerMAC");
        if (routerMAC == null || routerMAC.equals("")) {
            request.setAttribute("message", "<h2 style='color:red'>Please select a MAC address.</h2>");
            Redirector.redirect(request, response, "/admin/removeRouter.jsp");
            return;
        }

        RouterManager rm = (RouterManager) getServletContext().getAttribute("routerManager");

        if (rm.getRouterByMAC(routerMAC) == null) {
            request.setAttribute("message", "<h2 style='color:red'>Invalid MAC entered.</h2>");
            Redirector.redirect(request, response, "/admin/removeRouter.jsp");
            return;
        }

        if (!rm.removeRouter(routerMAC)) {
            request.setAttribute("message", "<h2 style='color:red'>Failed to remove router from database.</h2>");
            Redirector.redirect(request, response, "/admin/removeRouter.jsp");
            return;
        } else {
            Logger.Log(Logger.LogType.ROUTERADD, new String[]{routerMAC,(String)request.getSession().getAttribute("username")});
            request.setAttribute("message", "<h2>Successfully removed router from the database.</h2>");
            Redirector.redirect(request, response, "/admin/removeRouter.jsp");
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
