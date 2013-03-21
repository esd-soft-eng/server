package servlets;

import QuestionsAndAnswers.QuestionSetManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
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
public class PaymentProcessor extends HttpServlet {

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
        
        // Get card type and validate it
        String cardType = (String) request.getParameter("cardType");
        if (cardType != null) {
            cardType = InputValidator.clean(cardType);
        }
        if (cardType == null || cardType.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select a card type</h2>");
            Redirector.redirect(request, response, "/kiosk/enterPaymentDetails.jsp");
            return;
        }
        
        // Get card number and validate it
        String cardNumber = (String) request.getParameter("cardNumber");
        if (cardNumber != null) {
            cardNumber = InputValidator.clean(cardNumber);
        }
        if (cardNumber == null || cardNumber.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please enter a card number</h2>");
            Redirector.redirect(request, response, "/kiosk/enterPaymentDetails.jsp");
            return;
        }
        
        // Get expiry month and validate it
        String expiryMonth = (String) request.getParameter("expiryMonth");
        if (expiryMonth != null) {
            expiryMonth = InputValidator.clean(expiryMonth);
        }
        if (expiryMonth == null || expiryMonth.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select an expiry month</h2>");
            Redirector.redirect(request, response, "/kiosk/enterPaymentDetails.jsp");
            return;
        }
        
        // Get card type and validate it
        String expiryYear = (String) request.getParameter("expiryYear");
        if (expiryYear != null) {
            expiryYear = InputValidator.clean(expiryYear);
        }
        if (expiryYear == null || expiryYear.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select an expiry year</h2>");
            Redirector.redirect(request, response, "/kiosk/enterPaymentDetails.jsp");
            return;
        }
        
        String expiryDate = expiryMonth + "/" + expiryYear;
        
        // Get card type and validate it
        String securityNumber = (String) request.getParameter("securityNumber");
        if (securityNumber != null) {
            securityNumber = InputValidator.clean(securityNumber);
        }
        if (securityNumber == null || securityNumber.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please enter a security number</h2>");
            Redirector.redirect(request, response, "/kiosk/enterPaymentDetails.jsp");
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
