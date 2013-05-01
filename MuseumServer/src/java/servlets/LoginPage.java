package servlets;

import businessDomainObjects.User;
import businessDomainObjects.UserManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistance.DatabaseQueryExecutor;
import persistance.PersistanceRepositoryUser;
import utility.Redirector;

/**
 * Logs in a user to the system provided that their login details are correct
 * that were given on the index JSP
 * 
 * @author Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
 * @author Simon Edwins
 */
public class LoginPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        // Get a new HTTP session
        HttpSession session = request.getSession();

        UserManager um = (UserManager) request.getServletContext().getAttribute("userManager");
        DatabaseQueryExecutor db = (DatabaseQueryExecutor)request.getServletContext().getAttribute("databaseExecutor");
        um = new UserManager(new PersistanceRepositoryUser(db));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validates the username and password combination to see if the login
        // was successful
        User user = um.validateUser(username, password);
        if (user == null) {
            request.setAttribute("message", "Incorrect login.");
            Redirector.redirect(request, response, "index.jsp");
            return;
        }
        
        // Sets session attributes for the current user so that the system
        // remembers who they are
        session.setAttribute("currentUser", user);
        session.setAttribute("username",username);
        request.setAttribute("user", user);

        Redirector.redirect(request, response, "displayPortal.do");
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
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