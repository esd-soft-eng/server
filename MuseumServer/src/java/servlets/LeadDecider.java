package servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.Redirector;
import visitorsAndGroups.GroupManager;

/**
 *
 * @author Neil
 */
@WebServlet(name = "LeadDecider", urlPatterns = {"/LeadDecider.do"})
public class LeadDecider extends HttpServlet {

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

        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();

        int groupLeader = Integer.parseInt(request.getParameter("groupLeader"));
        int multicastGroup = (Integer) session.getAttribute("multicastGroup");
        int tourId = (Integer) session.getAttribute("tourId");
        GroupManager gm = (GroupManager) ctx.getAttribute("groupManager");

        if (multicastGroup == 1) {
            if (!gm.setGroupLeader(tourId, groupLeader)) {
                request.setAttribute("message", "<h2 style='color:red'>Something went wrong. Please contact"
                                        + "<br/>Please contact a staff member.</h2>");
                Redirector.redirect(request, response, "/kiosk/finaliseTour.jsp");
            }
        }
        Redirector.redirect(request, response, "/kiosk/tourAndHandsetSelect.jsp");
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
