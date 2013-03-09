package servlets;

import businessDomainObjects.Router;
import businessDomainObjects.RouterManager;
import businessDomainObjects.User;
import businessDomainObjects.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.Redirector;

/**
 *
 * @author Darkstar
 */
public class ModifyRouter extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        
        session.setAttribute("currentUser", user);
        request.setAttribute("user", user);
        
        RouterManager rm = (RouterManager) request.getServletContext().getAttribute("routerManager");
        
        // Get existing router object
        Router thisRouter = rm.getRouterByMAC(request.getParameter("selectedRouter"));
        
        String audioLocation = request.getParameter("audioLocation");
        String description = request.getParameter("description");
        String macAddress = request.getParameter("macAddress");
        
        if(audioLocation == null || audioLocation.equals("")
                || description == null || description.equals("")
                || macAddress == null || macAddress.equals("")){
            request.setAttribute("message", "<h2 style='color:red;'>Fail - Input error!  All fields must be filled in to modify a user.</h2>");
            Redirector.redirect(request, response, "/admin/manageRouters.jsp");
            return;
        }
                
        // Update existing router object
        thisRouter.setAudioLocation(audioLocation);
        thisRouter.setDescription(description);
        thisRouter.setMACAddress(macAddress);
        
        // Execute sql for update
        rm.modifyRouter(thisRouter);
        
        request.setAttribute("message", "<h2 style='color:green;'>Success!</h2>");
        Redirector.redirect(request, response, "/admin/manageRouters.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
