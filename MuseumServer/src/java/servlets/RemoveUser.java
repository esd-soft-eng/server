/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businessDomainObjects.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Alex
 */
public class RemoveUser extends HttpServlet {

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
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            DatabaseQueryExecutor dbQuery = new DatabaseQueryExecutor("jdbc:mysql://localhost:3306/museum_server_database", "root", "");

            ServletContext ctx = request.getServletContext();
            
            UserManager um = (UserManager) ctx.getAttribute("userManager");
            
            if (um.removeUser(userID)){          
                // Instantiate a request dispatcher for the JSP
                RequestDispatcher view =
                        request.getRequestDispatcher("userRemoveSuccessful.jsp");

                // Use the request dispatcher to ask the Container to crank up the JSP,
                // sending it the request and response
                view.forward(request, response);
            } else {
                // Instantiate a request dispatcher for the JSP
                RequestDispatcher view =
                        request.getRequestDispatcher("userRemoveUnsuccessful.jsp");

                // Use the request dispatcher to ask the Container to crank up the JSP,
                // sending it the request and response
                view.forward(request, response);
            }
        } finally {
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
