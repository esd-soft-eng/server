/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.InputValidator;
import utility.Redirector;

/**
 *
 * @author Alex
 */
public class AddNewUser extends HttpServlet {

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
            // Get userName and validate it
            String userName = request.getParameter("userName");
            if (userName != null) {
                userName = InputValidator.clean(userName);
            }
            if (userName == null || userName.isEmpty()) {
                request.setAttribute("message", "<h2 style='color:red'>Please enter a user name</h2>");
                Redirector.redirect(request, response, "/admin/addNewUserForm.jsp");
                return;
            }
            
            // Get password and validate it
            String password = request.getParameter("password");
            if (password != null) {
                password = InputValidator.clean(password);
            }
            if (password == null || userName.isEmpty()) {
                request.setAttribute("message", "<h2 style='color:red'>Please enter a password</h2>");
                Redirector.redirect(request, response, "/admin/addNewUserForm.jsp");
                return;
            }
            
            // Get userType and validate it
            String userType = request.getParameter("userType");
            if (userType != null) {
                userType = InputValidator.clean(userType);
            }
            if (userType == null || userName.isEmpty()) {
                request.setAttribute("message", "<h2 style='color:red'>Please select a user type</h2>");
                Redirector.redirect(request, response, "/admin/addNewUserForm.jsp");
                return;
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
