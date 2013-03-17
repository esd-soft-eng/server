package servlets;

import QuestionsAndAnswers.Levels;
import QuestionsAndAnswers.QuestionSet;
import QuestionsAndAnswers.QuestionSetManager;
import businessDomainObjects.Tour;
import businessDomainObjects.TourManager;
import java.io.IOException;
import java.util.logging.Level;
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
            case 2:
                this.getTourAndNumberOfHandsets(request);
                this.getQuestionsForVisitorToAnswer(request, session);
            default:
                view = request.getRequestDispatcher("kiosk/tourAndHandsetSelect.jsp");
                break;
        }

        
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
        int currentVisitor = 0;
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

    private void getQuestionsForVisitorToAnswer(HttpServletRequest request, HttpSession session) {
        
        
        
        int tourId = (Integer) session.getAttribute("tourId");       
        int currentVisitor = (Integer) session.getAttribute("currentVisitor");
        Visitor[] visitors = (Visitor[]) session.getAttribute("visitors");
                               
        if (currentVisitor < visitors.length){
            
            String title = (String) request.getParameter("title");            
            String forename = (String) request.getParameter("forename");            
            String surname = (String) request.getParameter("surname");
            int age = Integer.parseInt((String) request.getParameter("age"));
            
            visitors[currentVisitor] = new Visitor(title, forename, surname, age, null);
            
            request.setAttribute("visitorName", title + ". " + surname);
            
            
            TourManager tm = (TourManager) getServletContext().getAttribute("tourManager");
            Tour tour = tm.getTourByID(tourId);
        
            QuestionSetManager qsm = (QuestionSetManager) getServletContext().getAttribute("questionSetManager");
            QuestionSet qs = qsm.getQuestionSetById(tour.getQuestionSetID());
        
            request.setAttribute("questions", qs.getQuestionsForDisplay());
            request.setAttribute("visitorSignupStatus", "2");
            
            currentVisitor++;
            return;
        }
        
        request.setAttribute("visitorSignupStatus", "4");
    }

 
}
