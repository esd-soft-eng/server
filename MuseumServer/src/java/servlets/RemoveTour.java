package servlets;

import businessDomainObjects.Tour;
import businessDomainObjects.TourManager;
import businessDomainObjects.User;
import businessDomainObjects.UserManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logging.Logger;
import utility.Redirector;

/**
 *
 * @author Darkstar
 */
public class RemoveTour extends HttpServlet {

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

        // Get a new HTTP session
        HttpSession session = request.getSession();

        UserManager um = (UserManager) request.getServletContext().getAttribute("userManager");

        // Username check and pass through
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = um.validateUser(username, password);


        int tourID = 0;

        if (request.getParameter("selectedTour") == null) {
            request.setAttribute("message", "<h2 style='color:red;'>Request to remove a tour failed!</h2>");
            Redirector.redirect(request, response, "/admin/removeTour.jsp");
            return;
        } else {
            tourID = Integer.parseInt(request.getParameter("selectedTour"));
        }

        TourManager tm = (TourManager) getServletContext().getAttribute("tourManager");

        Tour tourToRemove = tm.getTourByID(tourID);
        Logger.Log(Logger.LogType.TOURADD, new String[]{String.valueOf(tourToRemove.getTourID()), tourToRemove.getName(), (String) request.getSession().getAttribute("username")});

        tm.removeTour(tourID);

        request.setAttribute("message", "<h2 style='color:green;'>Tour was removed!  Success!</h2>");
        Redirector.redirect(request, response, "/admin/removeTour.jsp");

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
