/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import QuestionsAndAnswers.Answer;
import QuestionsAndAnswers.Question;
import QuestionsAndAnswers.QuestionSet;
import QuestionsAndAnswers.QuestionSetManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.InputValidator;

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
        
        String questionSetAction = (String) request.getParameter("questionSetAction");
        String questionSetId = (String) request.getParameter("questionSetId");
        QuestionSetManager qsm = (QuestionSetManager) getServletContext().getAttribute("questionSetManager");
        
        if (!(questionSetAction.equals(""))) {
            switch (Integer.parseInt(questionSetAction)) {
                case 1:
                    this.addQuestionSet(request, qsm);
                    break;
                case 2:
                    this.displayQuestions(request);
                    break;
                case 3:
                    this.removeQuestionSet(questionSetId, qsm);
                    break;
                case 4:
                    this.addQuestionToQuestionSet(request, qsm);
                    break;
                case 5:
                    String questionId = (String) request.getParameter("questionId");
                    this.removeQuestionFromQuestionSet(request, qsm);
                    break;
                default:
                    break;
            }
        }

        QuestionSet[] questionSets = qsm.getAllQuestionSets();
        request.setAttribute("questionSets", questionSets);
        request.setAttribute("questionSetId", questionSetId);

        RequestDispatcher view = request.getRequestDispatcher("admin/modifyQuestionSet.jsp");
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

    private void addQuestionSet(HttpServletRequest request, QuestionSetManager qsm) {
        String setName = (String) request.getParameter("setName");
        setName = InputValidator.escape(setName);
        qsm.addQuestionSet(setName);
    }

    private void displayQuestions(HttpServletRequest request) {
        request.setAttribute("displayQuestions", true);
    }

    private void removeQuestionSet(String questionSetId, QuestionSetManager qsm) {
        qsm.deleteQuestionSet(Integer.parseInt(questionSetId));
    }

    private void addQuestionToQuestionSet(HttpServletRequest request, QuestionSetManager qsm) {

        int questionSetId = Integer.parseInt(request.getParameter("questionSetId"));
        String question = (String) request.getParameter("questionText");
        question = InputValidator.escape(question);
        ArrayList<Answer> answers = new ArrayList();

        for (int i = 0; i < 6; i++) {
            String answer = (String) request.getParameter("answer" + (i + 1));
            if (!(answer.equals(""))) {
                answer = InputValidator.escape(answer);
                System.out.println("in here");
                int value = Integer.parseInt(request.getParameter("value" + (i + 1)));
                answers.add(new Answer(answer, value));
            }
        }

        Question q = new Question(0, question, answers);

        qsm.addQuestion(q, questionSetId);
        this.displayQuestions(request);
    }

    private void removeQuestionFromQuestionSet(HttpServletRequest request, QuestionSetManager qsm) {

        int questionId = Integer.parseInt(request.getParameter("questionId"));
        int questionSetId = Integer.parseInt(request.getParameter("questionSetId"));
        qsm.removeQuestionFromQuestionSet(questionId, questionSetId);
        this.displayQuestions(request);
    }
}
