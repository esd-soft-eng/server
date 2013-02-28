/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import QuestionsAndAnswers.Answer;
import QuestionsAndAnswers.Question;
import QuestionsAndAnswers.QuestionSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author neil
 */
@WebServlet(name = "ModifyQuestionSet", urlPatterns = {"/modifyQuestionSet.do"})
public class ModifyQuestionSet extends HttpServlet {

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

        String questionSet = (String) request.getParameter("questionSet");
        QuestionSet[] questionSets = getFakeData();

        //set variables
        request.setAttribute("questionSets", questionSets);
        
        
        if (questionSet == null) {
        }


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModifyQuestionSet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModifyQuestionSet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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

    private QuestionSet[] getFakeData() {
        
        QuestionSet[] questionSet = new QuestionSet[2];
        // get first questionset
        ArrayList<Answer> a1 = new ArrayList();
        a1.add(new Answer("Answer 1", 5));
        a1.add(new Answer("Answer 2", 1));
        a1.add(new Answer("Answer 3", 3));
        Question question1 = new Question(1, "Question 1", a1);
        ArrayList<Question> q1 = new ArrayList();
        q1.add(question1);
        
        ArrayList<Answer> a2 = new ArrayList();
        a2.add(new Answer("Answer 1", 3));
        a2.add(new Answer("Answer 2", 2));
        a2.add(new Answer("Answer 3", 8));
        Question question2 = new Question(1, "Question 2", a2);
        ArrayList<Question> q2 = new ArrayList();
        q2.add(question2);
        
        questionSet[1] = new QuestionSet(1, "Testtype22!", q1);
        
        
        return questionSet;
    }
}
