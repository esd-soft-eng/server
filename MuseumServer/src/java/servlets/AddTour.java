/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import QuestionsAndAnswers.QuestionSetManager;
import businessDomainObjects.TourManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * + Darkstar <1337@313373>
 */
@WebServlet(name = "AddTour", urlPatterns = {"/addTour.do"})
public class AddTour extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String questionSetSelection = (String) request.getParameter("questionSetSelection");
        
        if(questionSetSelection.equals("new")){
            questionSetSelection = ""; // A tour doesn't have to have a question set, so we leave a blank field.
        }
        
        String tourName = request.getParameter("tourName");
        String tourDescription = request.getParameter("tourDescription");
        String[] exhibitIDs = request.getParameterValues("exhibitID");

        if (tourName == null || tourName.equals("")) {
            request.setAttribute("message", "<h2 style='color:red;'> Name cannot be empty. </h2>");
            Redirector.redirect(request, response, "/admin/addNewTour.jsp");
            return;
        } else if (tourDescription == null || tourDescription.equals("")) {
            request.setAttribute("message", "<h2 style='color:red;'> Description cannot be empty. </h2>");
            Redirector.redirect(request, response, "/admin/addNewTour.jsp");
            return;
        } else if (exhibitIDs == null || exhibitIDs.length == 0) {
            request.setAttribute("message", "<h2 style='color:red;'> Please select at least one exhibit. </h2>");
            Redirector.redirect(request, response, "/admin/addNewTour.jsp");
            return;
        }
        TourManager tm = (TourManager) getServletContext().getAttribute("tourManager");
        ArrayList exhibits = new ArrayList<String>();
        for(String exhibit : exhibitIDs) {
            exhibits.add(exhibit);
        }

                
                
        if (!tm.addTour(tourName, tourDescription, exhibits, questionSetSelection)) {
            request.setAttribute("message", "<h2 style='color:red;'> Failed to create tour. </h2>");
            Redirector.redirect(request, response, "/admin/addNewTour.jsp");
            return;
        } else {
            request.setAttribute("message", "<h2> Successfully created tour. </h2>");
            Redirector.redirect(request, response, "/admin/addNewTour.jsp");
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
