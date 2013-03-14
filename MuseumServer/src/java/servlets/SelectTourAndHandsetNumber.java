package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.InputValidator;
import utility.Redirector;

/**
 *
 * @author Alex
 */
public class SelectTourAndHandsetNumber extends HttpServlet {

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
        
        // Get tour selected and validate it
        String tour = request.getParameter("tour");
        if (tour != null) {
            tour = InputValidator.clean(tour);
        }
        if (tour == null || tour.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select a tour</h2>");
            Redirector.redirect(request, response, "/kiosk/tourAndHandsetSelect.jsp");
            return;
        }

        // Get number of handsets required and validate
        String handsetString = request.getParameter("handset");
        int handsetNumber = 0;
        if (handsetString != null) {
            handsetNumber = Integer.parseInt(InputValidator.clean(handsetString));
        }
        if (handsetString == null || handsetString.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select the number of handsets you require</h2>");
            Redirector.redirect(request, response, "/kiosk/tourAndHandsetSelect.jsp");
            return;
        }

        session.setAttribute("tour", tour);
        session.setAttribute("handsetNo", handsetNumber);
        
        // Redirect to next JSP
        // Instantiate a request dispatcher for the JSP
        RequestDispatcher view =
                request.getRequestDispatcher("addUserDetails.jsp");

        // Use the request dispatcher to ask the Container to crank up the JSP,
        // sending it the request and response
        view.forward(request, response);
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
