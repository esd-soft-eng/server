package servlets;

import businessDomainObjects.UserManager;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logging.Logger;
import utility.InputValidator;
import utility.MD5Hasher;
import utility.Redirector;

/**
 * Adds a new user to the system with details provided by the addNewUserForm
 * JSP. The user is then redirected back to this JSP to tell them whether the
 * new user was successfully added or not.
 * 
 * @author Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
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
            String hashedPassword = null;
            if (password != null) {
                password = InputValidator.clean(password);
                hashedPassword = MD5Hasher.hashMD5(password);
            }
            if (password == null || password.equals("") || userName.isEmpty()) {
                request.setAttribute("message", "<h2 style='color:red'>Please enter a password</h2>");
                Redirector.redirect(request, response, "/admin/addNewUserForm.jsp");
                return;
            }

            // Get userType and validate it
            int[] type = new int[5];

            String[] userType = request.getParameterValues("userType");
            if (userType != null) {
                for (int i = 0; i < userType.length; i++) {
                    userType[i] = InputValidator.clean(userType[i]);
                    type[i] = Integer.parseInt(userType[i]);
                }
            }
            if (userType == null || userName.isEmpty()) {
                request.setAttribute("message", "<h2 style='color:red'>Please select a user type</h2>");
                Redirector.redirect(request, response, "/admin/addNewUserForm.jsp");
                return;
            }

            // Add user to the database
            ServletContext ctx = request.getServletContext();
            UserManager um = (UserManager) ctx.getAttribute("userManager");

            /*
             * Call addUser in UserManager and pass the userName and
             * hashedPassword as provided by the JSP that triggers this
             * servlet. For loop is used to add multiple user types if the user
             * is associated with more than 1 user type
             */
            if (um.addUser(userName, hashedPassword)) {
                for (int i = 0; i < userType.length; i++) {
                    if (um.addUserType(type[i]) == false) {
                        request.setAttribute("message", "<h2 style='color:red'>Failed to add user <i>\"" + userName + "\"</i> to the database.</h2>");
                        Redirector.redirect(request, response, "/admin/addNewUserForm.jsp");
                        return;
                    }
                }
                Logger.Log(Logger.LogType.USERADD, new String[]{userName, (String)request.getSession().getAttribute("username")});
                request.setAttribute("message", "<h2>Successfully added user <i>\"" + userName + "\"</i> to the database.</h2>");
                Redirector.redirect(request, response, "/admin/addNewUserForm.jsp");
                return;
            } else {
                request.setAttribute("message", "<h2 style='color:red'>Failed to add user <i>\"" + userName + "\"</i> to the database.</h2>");
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