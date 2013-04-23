package servlets;

import QuestionsAndAnswers.Levels;
import QuestionsAndAnswers.QuestionSet;
import QuestionsAndAnswers.QuestionSetManager;
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
import utility.LevelCalculator;
import visitorsAndGroups.Visitor;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
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
                this.clearSignUpSessionData(session);
                break;
            case 1:
                this.getUserDetails(request, session);
                break;
            case 2:
                this.getQuestionsForVisitorToAnswer(request, session);
                break;
            case 3:
                this.getVisitorsPreferredAudioLevel(request, session);
                break;
            case 4:
                this.collectLevelAndVisitorDetails(request, session);
                break;
            case 5:
                view = request.getRequestDispatcher("kiosk/enterPaymentDetails.jsp");
                break;
            default:
                this.clearSignUpSessionData(session);
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

        //if these have already been set then we're golden.
        if (session.getAttribute("visitors") != null) {
            int currentVisitor = (Integer) session.getAttribute("currentVisitor");
            Visitor[] visitors = (Visitor[]) session.getAttribute("visitors");
            request.setAttribute("numberOfVisitors", visitors.length);
            request.setAttribute("displayCurrentVisitor", currentVisitor + 1);
            request.setAttribute("visitorSignupStatus", "1");


            return;
        }

        int tourId = Integer.parseInt((String) request.getParameter("tourId"));
        int numberOfPeople = Integer.parseInt((String) request.getParameter("numberOfPeople"));
        int multicastGroup = Integer.parseInt((String) request.getParameter("multicastGroup"));
        int currentVisitor = 0;
                
        Visitor[] visitors = new Visitor[numberOfPeople];

        session.setAttribute("tourId", tourId);
        session.setAttribute("currentVisitor", currentVisitor);
        session.setAttribute("multicastGroup", multicastGroup);
        request.setAttribute("numberOfVisitors", visitors.length);
        session.setAttribute("visitors", visitors);
        request.setAttribute("displayCurrentVisitor", currentVisitor + 1);
        request.setAttribute("visitorSignupStatus", "1");
    }

    private void clearSignUpSessionData(HttpSession session) {

        session.removeAttribute("questionSet");
        session.removeAttribute("tourId");
        session.removeAttribute("currentVisitor");
        session.removeAttribute("visitors");
        session.removeAttribute("multicastGroup");
    }

    private void getQuestionsForVisitorToAnswer(HttpServletRequest request, HttpSession session) {

        int tourId = (Integer) session.getAttribute("tourId");
        int currentVisitor = (Integer) session.getAttribute("currentVisitor");
        Visitor[] visitors = (Visitor[]) session.getAttribute("visitors");

        if (currentVisitor < visitors.length) {

            String title = InputValidator.clean((String) request.getParameter("title"));
            String forename = InputValidator.clean((String) request.getParameter("forename"));
            String surname = InputValidator.clean((String) request.getParameter("surname"));
            int age = Integer.parseInt((String) request.getParameter("age"));

            visitors[currentVisitor] = new Visitor(title, forename, surname, age, null);
            TourManager tm = (TourManager) getServletContext().getAttribute("tourManager");
            Tour tour = tm.getTourByID(tourId);

            QuestionSetManager qsm = (QuestionSetManager) getServletContext().getAttribute("questionSetManager");
            QuestionSet qs = qsm.getQuestionSetById(tour.getQuestionSetID());

            request.setAttribute("visitorName", title + ". " + surname);
            session.setAttribute("questionSet", qs);
            request.setAttribute("questions", qs.getQuestionsForDisplay());
            request.setAttribute("visitorSignupStatus", "2");
            return;
        }

        request.setAttribute("visitorSignupStatus", "4");
    }

    private void getVisitorsPreferredAudioLevel(HttpServletRequest request, HttpSession session) {
        
        int currentVisitor = (Integer) session.getAttribute("currentVisitor");
        Visitor[] visitors = (Visitor[]) session.getAttribute("visitors");

        QuestionSet qs = (QuestionSet) session.getAttribute("questionSet");

        int[] answers = this.extractAnswersFromRequest(request, qs.getQuestionsForDisplay().length);
        int score = qs.getScoreForAnswers(answers);
        Levels level = LevelCalculator.CalculateLevel(score);

        Visitor visitor = visitors[currentVisitor];
        request.setAttribute("visitorName", visitor.title + ". " + visitor.surname);
        request.setAttribute("score", "score: " + score);
        request.setAttribute("level", level.name());
        request.setAttribute("levels", Levels.values());
        request.setAttribute("questions", qs.getQuestionsForDisplay());
        request.setAttribute("visitorSignupStatus", "3");
    }

    private int[] extractAnswersFromRequest(HttpServletRequest request, int numberOfQuestions) {

        int[] answers = new int[numberOfQuestions];
        for (int i = 0; i < numberOfQuestions; i++) {
            answers[i] = Integer.parseInt((String) request.getParameter("question" + i));
        }
        return answers;
    }

    private void collectLevelAndVisitorDetails(HttpServletRequest request, HttpSession session) {

        int currentVisitor = (Integer) session.getAttribute("currentVisitor");
        Visitor[] visitors = (Visitor[]) session.getAttribute("visitors");
        Visitor visitor = visitors[currentVisitor];
        Levels level = (Levels) Levels.valueOf(request.getParameter("level"));
        visitor.level = level;
        
        if (currentVisitor < visitors.length -1) {
            session.setAttribute("currentVisitor", currentVisitor + 1);
            this.getUserDetails(request, session);
            return;
        }
        
        this.confirmVisitorDetails(request, session, visitors); 
    }

    private void confirmVisitorDetails(HttpServletRequest request, HttpSession session, Visitor[] visitors) {
        
        double price = 10.00;
        TourManager tm = (TourManager) getServletContext().getAttribute("tourManager");
        int tourId = (Integer) session.getAttribute("tourId");
        
        request.setAttribute("pricePerHandset", price);
        request.setAttribute("tourName", tm.getTourByID(tourId).getName());
        request.setAttribute("visitors", visitors);
        request.setAttribute("visitorSignupStatus", "4");
        
    }
}