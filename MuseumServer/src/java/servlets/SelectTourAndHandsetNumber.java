package servlets;

import businessDomainObjects.Tour;
import businessDomainObjects.TourManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.InputValidator;
import utility.Redirector;
import visitorsAndGroups.Visitor;

/**
 *
 * @author Alex
 */
@WebServlet(name = "SelectTourAndHandsetNumber", urlPatterns = {"/SelectTourAndHandsetNumber.do"})
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
        String questionSetId = (String) request.getParameter("questionSetId");
        String visitorSignupStatus = (String) request.getParameter("visitorSignupStatus");

        RequestDispatcher view = request.getRequestDispatcher("kiosk/tourAndHandsetSelect.jsp");
        
        if (visitorSignupStatus == null) {
            visitorSignupStatus = "0";
        }

        switch (Integer.parseInt(visitorSignupStatus)) {
            case 0:
                this.getTourAndNumberOfHandsets(request);
                this.clearSessionData(session);
                break;
            case 1:
                this.getTourAndNumberOfHandsets(request);
                this.getUserDetails(request, session);
                break;
            default:
                view = request.getRequestDispatcher("kiosk/tourAndHandsetSelect.jsp");
                break;
        }


/*
        // Get tour selected and validate it
        String tourString = (String) request.getParameter("tour");
        int tourID = 0;
        if (tourString != null) {
            tourID = Integer.parseInt(InputValidator.clean(tourString));
        }
        if (tourString == null || tourString.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select a tour</h2>");
            Redirector.redirect(request, response, "/kiosk/tourAndHandsetSelect.jsp");
            return;
        }

        // Get number of handsets required and validate
        String handsetString = (String) request.getParameter("handset");
        int handsetNumber = 0;
        if (handsetString != null) {
            handsetNumber = Integer.parseInt(InputValidator.clean(handsetString));
        }
        if (handsetString == null || handsetString.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select the number of handsets you require</h2>");
            Redirector.redirect(request, response, "/kiosk/tourAndHandsetSelect.jsp");
            return;
        }

        session.setAttribute("tourID", tourID);
        session.setAttribute("handsetNo", handsetNumber);

        // Redirect so each user can enter their own details
        RequestDispatcher view =
                request.getRequestDispatcher("addUserDetails.jsp");

        // Use the request dispatcher to ask the Container to crank up the JSP,
        // sending it the request and response */
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

    private void getTourAndNumberOfHandsets(HttpServletRequest request) {
        
        TourManager tm = (TourManager) getServletContext().getAttribute("tourManager");
        Tour[] tours = tm.getListOfTours().toArray(new Tour[0]);
        request.setAttribute("tours", tours);   
        request.setAttribute("visitorSignupStatus", "0");
    }

    private void getUserDetails(HttpServletRequest request, HttpSession session) {
        
        String tourIdS = (String) request.getParameter("tourId");
        String numberOfPeopleS = (String) request.getParameter("numberOfPeople");
        
        int tourId = Integer.parseInt(tourIdS);
        int numberOfPeople = Integer.parseInt(numberOfPeopleS);
        int currentVisitor = 1;
        Visitor[] visitors = new Visitor[numberOfPeople];
        
        session.setAttribute("tourId", tourId);
        session.setAttribute("currentVisitor", currentVisitor);
        session.setAttribute("visitors", visitors);
        request.setAttribute("visitorSignupStatus", "1");
    }
    
    private void clearSessionData(HttpSession session){
        
        session.removeAttribute("tourId");
        session.removeAttribute("currentVisitor");
        session.removeAttribute("visitors");
    }

 
}
