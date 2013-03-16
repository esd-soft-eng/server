package servlets;

import QuestionsAndAnswers.DisplayQuestion;
import QuestionsAndAnswers.Question;
import QuestionsAndAnswers.QuestionSet;
import QuestionsAndAnswers.QuestionSetManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.InputValidator;
import utility.Redirector;

/**
 *
 * @author Alex
 */
public class ValidateUserDetails extends HttpServlet {

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
        ServletContext ctx = request.getServletContext();
        QuestionSetManager qsm = (QuestionSetManager) ctx.getAttribute("questionSetManger");
        
        // Get title selected and validate it
        String title = (String) request.getParameter("title");
        if (title != null) {
            title = InputValidator.clean(title);
        }
        if (title == null || title.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select a title</h2>");
            Redirector.redirect(request, response, "/kiosk/addUserDetails.jsp");
            return;
        }
        
        // Get first name entered and validate it
        String firstName = (String) request.getParameter("firstName");
        if (firstName != null) {
            firstName = InputValidator.clean(firstName);
        }
        if (firstName == null || firstName.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please enter a first name</h2>");
            Redirector.redirect(request, response, "/kiosk/addUserDetails.jsp");
            return;
        }
        
        // Get last name entered and validate it
        String lastName = (String) request.getParameter("lastName");
        if (lastName != null) {
            lastName = InputValidator.clean(lastName);
        }
        if (lastName == null || lastName.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please enter a last name</h2>");
            Redirector.redirect(request, response, "/kiosk/addUserDetails.jsp");
            return;
        }
        
        // Get age selected and validate it
        String age = (String) request.getParameter("age");
        if (age != null) {
            age = InputValidator.clean(age);
        }
        if (age == null || age.isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>Please select an age</h2>");
            Redirector.redirect(request, response, "/kiosk/addUserDetails.jsp");
            return;
        }
        
        // Get the questions we're going to ask the user and store them to be sent
        // to the next JSP to be displayed
        
        // TODO work out the id using Simon's stuff to find corresponding question
        // set id to the tour we're passed in
        QuestionSet qs = qsm.getQuestionSetById(1);
        
        DisplayQuestion[] questions = qs.getQuestionsForDisplay();
        
        request.setAttribute("questions", questions);
        
        // Redirect so user who has just entered their details can answer questions
        // to determine their level
        RequestDispatcher view =
                request.getRequestDispatcher("answerQuestions.jsp");

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
