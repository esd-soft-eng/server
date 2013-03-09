// FIXME: Validation (string validation, using utility -> add).

package servlets;

import businessDomainObjects.User;
import businessDomainObjects.UserManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.Redirector;

/**
 *
 * @author Simon
 */
public class ModifyUser extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        // Get a new HTTP session
        HttpSession session = request.getSession();
        
        UserManager um = (UserManager) request.getServletContext().getAttribute("userManager");
        
        // Username check and pass through
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = um.validateUser(username, password);
        
        session.setAttribute("currentUser", user);
        request.setAttribute("user", user);
        
        String userName, userPassword;
        int userID = 0;
        
        try{
            userID = Integer.parseInt(request.getParameter("userToModify"));
        }
        catch(NumberFormatException ex){
           
        }
        // Get form parameters
        //Checkbox, retrieve parameter, update the appropriate SQL field
        
        //userName
        userName = request.getParameter("userName");
        
        //userPassword, remembering to md5 first
        userPassword = request.getParameter("password");
        
        // If nothing was entered into one of the required form elements
        if(userName == null || userName.equals("") || userPassword == null || userPassword.equals("")){
            request.setAttribute("message", "<h2 style='color:red;'>Fail - Username or Password was not entered!  All fields must be filled in to modify a user.</h2>");
            Redirector.redirect(request, response, "/admin/modifyUser.jsp");
        }
        else if(!um.modifyUser(userID, userName, userPassword, request.getParameterValues("userType"))){ // SQL update statement via user manager
            request.setAttribute("message", "<h2 style='color:red;'>Fail - Could not perform modify user/interact with database!</h2>");
            Redirector.redirect(request, response, "/admin/modifyUser.jsp");
        }
        else{
            request.setAttribute("message", "<h2 style='color:green;'>Success!  The user has been modified!</h2>");
            Redirector.redirect(request, response, "/admin/modifyUser.jsp");
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