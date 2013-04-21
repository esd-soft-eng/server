package servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.DummyPaymentTransactor;
import utility.InputValidator;
import utility.Redirector;
import visitorsAndGroups.GroupManager;
import visitorsAndGroups.Visitor;

/**
 *
 * @author Alex
 */
public class TourFinaliser extends HttpServlet {

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

        if (DummyPaymentTransactor.transactPayment(cardType, cardNumber, expiryDate, securityNumber)) {
            request.setAttribute("message", "<h2 style='color:green'>Payment Successful</h2>");
            this.processTour(ctx, session, request);
            Redirector.redirect(request, response, "/kiosk/finaliseTour.jsp");
        } else {
            request.setAttribute("message", "<h2 style='color:red'>Payment failed.<br/>Please re-enter details or cancel.</h2>");
            Redirector.redirect(request, response, "/kiosk/enterPaymentDetails.jsp");
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

    private void processTour(ServletContext ctx, HttpSession session, HttpServletRequest request) {

        GroupManager gm = (GroupManager) ctx.getAttribute("groupManager");

        // get the parameters which we've acquired through the sign up process
        int tourId = (Integer) session.getAttribute("tourId");
        Visitor[] visitors = (Visitor[]) session.getAttribute("visitors");
        int multicastGroup = (Integer) session.getAttribute("multicastGroup");

        request.setAttribute("visitors", visitors);
        request.setAttribute("multicastGroup", multicastGroup);

        if (multicastGroup == 1) {
            this.createMulticastGroup(tourId, visitors, gm, session);
            return;
        }

        this.createVisitorTourGroups(tourId, visitors, gm);

    }

    private void createMulticastGroup(int tourId, Visitor[] visitors, GroupManager gm, HttpSession session) {

        int groupId = gm.createNewGroup(tourId);
        session.setAttribute("multiCastGroupId", groupId);

        for (Visitor v : visitors) {
            gm.addNewVisitorToGroup(groupId, v);
        }
    }

    private void createVisitorTourGroups(int tourId, Visitor[] visitors, GroupManager gm) {

        for (Visitor v : visitors) {
            int groupId = gm.createNewGroup(tourId);
            if (gm.addNewVisitorToGroup(groupId, v)) {
                gm.setGroupLeader(groupId, v.pin);
            }
        }
    }
}
